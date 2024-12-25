package Services;

import Model.HotelAmenitiesMaster;

public interface HotelAmenitiesService {
	public abstract boolean addHotelAmenities(HotelAmenitiesMaster hotelAmenities);
	public abstract boolean updateHotelAmenities(float price,int hotelId,int amenityId);
	public abstract boolean deleteHotelAmenities(int hotelId,int amenityId);
}
