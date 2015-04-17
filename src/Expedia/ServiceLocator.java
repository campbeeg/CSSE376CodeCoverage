package Expedia;

import java.util.LinkedList;
import java.util.List;

public class ServiceLocator {
	private static ServiceLocator _instance = null;
	public static ServiceLocator Instance()
	{
		if(_instance == null)
				_instance = new ServiceLocator();
			
		return _instance;
	}
	
	private List<Discount> discounts;
	private List<Flight> flights;
	private List<Car> cars;
	
	public ServiceLocator ()
	{
		discounts = new LinkedList<Discount>();
		flights = new LinkedList<Flight>();
		cars = new LinkedList<Car>();
	}
	
	public List<Flight> AvailableFlights()
	{
		return flights;
	}
	
	public List<Discount> AvailableDiscounts()
	{
		return discounts;
	}
	
	public List<Car> AvailableCars()
	{
		return cars;	
	}
	
	public void AddDiscount(Discount aDiscount)
	{
		discounts.add(aDiscount);	
	}
	
	public void ClearDiscount() 
	{
		discounts.clear();
	}
	
	public void AddFlight(Flight aFlight)
	{
		flights.add(aFlight);	
	}
	
	public void RemoveFlight(Flight aFlight)
	{
		flights.remove(aFlight);	
	}
	
	public void AddCar(Car aCar)
	{
		cars.add(aCar);	
	}
	
	public void RemoveCar(Car aCar)
	{
		cars.remove(aCar);	
	}
}
