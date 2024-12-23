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
	public int isHotelPresent(String stateName) {
		
		return hotelRepo.isHotelPresent(stateName);
	}

}
