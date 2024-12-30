package Repository;

import Model.HotelMaster;

public interface HotelRepository {

	
	 public abstract boolean addNewHotel(HotelMaster hotel);
	public abstract int isHotelPresent(String hotelName,String stateName,String districtName,String cityName);
	public String getHotelName(String hotelEmail);
	public String getHotelName(int hotelId);
}
