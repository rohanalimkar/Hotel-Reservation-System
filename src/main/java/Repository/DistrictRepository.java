package Repository;

import java.util.List;
import java.util.Optional;

import Model.DistrictMaster;
import Model.HotelMaster;

public interface DistrictRepository {

	 public abstract boolean addDistrict(DistrictMaster district);
	public abstract int isDistrictPresent(String districtName);
<<<<<<< HEAD
	public abstract Optional<List<HotelMaster>> DistrictWiseHotel(String stateName,String districtName); 
=======
	public abstract Optional<List<HotelMaster>> DistrictWiseHotel(String stateName,String districtName);
>>>>>>> branch 'main' of https://github.com/rohanalimkar/Hotel-Reservation-System.git
}
