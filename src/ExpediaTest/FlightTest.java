package ExpediaTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Expedia.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;

public class FlightTest {
	private Flight targetFlight;
	private final Date StartDate = new Date(2009, 11, 1);
	private final Date EndDate = new Date(2009, 11, 30);

	@Before
	public void TestInitialize() {
		targetFlight = new Flight(StartDate, EndDate, 0);
	}

	@Test
	public void TestThatFlightInitializes() {
		Assert.assertNotNull(targetFlight);
	}

	@Test
	public void TestThatFlightHasCorrectMilesAfterConstruction() {
		Flight target = new Flight(StartDate, EndDate, 500);
		Assert.assertEquals(500, target.Miles);
	}

	@Test(expected = RuntimeException.class)
	// [ExpectedException(typeof(InvalidOperationException))]
	public void TestThatFlightThrowsOnInvertedDates() {
		new Flight(EndDate, StartDate, 500);
	}

	@Test(expected = RuntimeException.class)
	// [ExpectedException(typeof(ArgumentOutOfRangeException))]
	public void TestThatFlightThrowsOnBadMiles() {
		new Flight(StartDate, EndDate, -500);
	}

	@Test
	public void TestThatFlightHasCorrectBasePriceForSameDayFlight() {
		Flight target = new Flight(new Date(), new Date(), 0);
		Assert.assertEquals(200, target.getBasePrice(), 0.001);
	}

	@Test
	public void TestThatFlightHasCorrectBasePriceForNextDayFlight() {
		Flight target = new Flight(new Date(2015, 1, 1), new Date(2015, 1, 2),
				0);
		Assert.assertEquals(220, target.getBasePrice(), 0.001);
	}

	@Test
	public void TestThatFlightHasCorrectBasePriceForFiveDayFlight() {
		Flight target = new Flight(new Date(2015, 1, 1), new Date(2015, 1, 6),
				0);
		Assert.assertEquals(300, target.getBasePrice(), 0.0001);
	}

	@Test
	public void TestThatFlightDoesGetNumberOfPassengers() {

		IDatabase mockDatabase = createMock(IDatabase.class);
		List<String> values = new ArrayList<String>();
		for (int i = 0; i < 50; i++)
			values.add("Bob");
		
		replay(mockDatabase);
		Date date = new Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(),
				ZoneId.systemDefault()).plusDays(1);
		Flight target = new Flight(date, Date.from(ldt.atZone(
				ZoneId.systemDefault()).toInstant()), 0);
		
		mockDatabase.Passengers = values;
		target.Database = mockDatabase;
		Assert.assertEquals(50, target.NumberOfPassengers());
		verify(mockDatabase);
	}

	@After
	public void TearDown() {
		// mocks.VerifyAll();
	}
}
