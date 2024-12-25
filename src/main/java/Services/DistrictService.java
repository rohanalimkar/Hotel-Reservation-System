package Services;

import java.util.List;
import java.util.Optional;

import Model.DistrictMaster;
import Model.HotelMaster;

public interface DistrictService {

	 public abstract boolean addDistrict(DistrictMaster district);
		public abstract int isDistrictPresent(String districtName);
		public abstract Optional<List<HotelMaster>> districtWiseHotel(String districtName);
}
