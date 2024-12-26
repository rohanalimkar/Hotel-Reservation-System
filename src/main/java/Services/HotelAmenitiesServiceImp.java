package Services;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Optional<List<HotelAmenitiesMaster>> getAllAmenities(int hotelId) {
		// TODO Auto-generated method stub
		return hotelAmenityRepo.getAllAmenities(hotelId);
	}

	@Override
	public float getHotelAmenityPrice(int hotelId, int amenityId) {
		// TODO Auto-generated method stub
		return hotelAmenityRepo.getHotelAmenityPrice(hotelId, amenityId);
	}
	

}
