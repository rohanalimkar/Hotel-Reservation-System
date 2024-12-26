package Services;

import java.util.List;
import java.util.Optional;

import Model.HotelAmenitiesMaster;

public interface HotelAmenitiesService {
	public abstract boolean addHotelAmenities(HotelAmenitiesMaster hotelAmenities);
	public abstract boolean updateHotelAmenities(float price,int hotelId,int amenityId);
	public abstract boolean deleteHotelAmenities(int hotelId,int amenityId);
	public abstract Optional<List<HotelAmenitiesMaster>> getAllAmenities(int hotelId);
	public abstract float getHotelAmenityPrice(int hotelId,int amenityId);
}
