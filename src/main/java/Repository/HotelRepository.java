package Repository;

import Model.HotelMaster;

public interface HotelRepository {

	
	 public abstract boolean addNewHotel(HotelMaster hotel);
	public abstract int isHotelPresent(String stateName);
	//public abstract String getHotelName(String customerEmail); 
}
