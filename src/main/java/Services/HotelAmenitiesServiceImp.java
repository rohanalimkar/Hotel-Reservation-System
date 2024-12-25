package Services;

import Model.HotelAmenitiesMaster;
import Repository.HotelAmenitiesRepository;
import Repository.HotelAmenitiesRepositoryImp;

public class HotelAmenitiesServiceImp implements HotelAmenitiesService {
	HotelAmenitiesRepository hotelAmenityRepo=new HotelAmenitiesRepositoryImp();

	@Override
	public boolean addHotelAmenities(HotelAmenitiesMaster hotelAmenities) {
		
		return hotelAmenityRepo.addHotelAmenities(hotelAmenities);
	}

	@Override
	public boolean updateHotelAmenities(float price, int hotelId, int amenityId) {
		
		return hotelAmenityRepo.updateHotelAmenities(price, hotelId, amenityId);
	}

	@Override
	public boolean deleteHotelAmenities(int hotelId, int amenityId) {
		
		return hotelAmenityRepo.deleteHotelAmenities(hotelId, amenityId);
	}
	

}
