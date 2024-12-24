package Services;

import Model.HotelMaster;
import Repository.HotelRepository;
import Repository.HotelRepositoryImp;

public class HotelServiceImp implements HotelService {

	HotelRepository hotelRepo=new HotelRepositoryImp();
	@Override
	public boolean addNewHotel(HotelMaster hotel) {
		
		return hotelRepo.addNewHotel(hotel);
	}

	@Override
	public int isHotelPresent(String hotelName,String stateName,String districtName,String cityName) {
		
		return hotelRepo.isHotelPresent( hotelName, stateName, districtName, cityName);
	}

	@Override
	public String getHotelName(String hotelEmail) {
		
		return hotelRepo.getHotelName(hotelEmail);
	}

}
