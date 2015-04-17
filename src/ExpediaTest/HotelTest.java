package ExpediaTest;

import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Expedia.Hotel;
import Expedia.IDatabase;

public class HotelTest {
	private Hotel targetHotel;

	@Before
	public void TestInitialize() {
		targetHotel = new Hotel(1);
	}

	@Test 
	public void TestThatHotelInitializes() {
		Assert.assertNotNull(targetHotel);
	}
	
	@Test
	public void TestGetMiles() {
		Hotel h = new Hotel(3);
		Assert.assertEquals(0, h.getMiles());
	}
	
	@Test (expected = RuntimeException.class)
	public void TestThatHotelThrowsOnZeroNights() {
		new Hotel(0);
	}
	
	@Test
	public void TestThatHotelDoesGetAvailableRooms() {
		IDatabase db = createMock(IDatabase.class);
		
		List<String> values = new ArrayList<String>();
		for (int i = 0; i < 50; i++)
			values.add("Bob");
		
		replay(db);
		Hotel h = new Hotel(3);
		db.Rooms = values;
		h.Database = db;
		Assert.assertEquals(50, h.AvailableRooms());
		verify(db);
	}
	
	@Test
	public void TestThatHotelDoesGetRoomOccupant() {
		IDatabase db = createMock(IDatabase.class);
		
		expect(db.getRoomOccupant(1)).andReturn("Greg");
		replay(db);
		Hotel h = new Hotel(1);
		h.Database = db;
		Assert.assertEquals("Greg", h.getRoomOccupant(1));
		verify(db);
	}
	
	@Test
	public void TestThatHotelHasCorrectBasePriceForOneNightStay() {
		Hotel h = new Hotel(1);
		Assert.assertEquals(45, h.getBasePrice(), 0.001);
	}
	
	@Test
	public void TestThatHotelHasCorrectBasePriceForTwoNightStay() {
		Hotel h = new Hotel(2);
		Assert.assertEquals(90, h.getBasePrice(), 0.001);
	}
	
	@Test
	public void TestThatHotelHasCorrectBasePriceForFiveNightStay() {
		Hotel h = new Hotel(5);
		Assert.assertEquals(225, h.getBasePrice(), 0.001);
	}
}
