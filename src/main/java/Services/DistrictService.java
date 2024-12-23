package Services;

import Model.DistrictMaster;

public interface DistrictService {

	 public abstract boolean addDistrict(DistrictMaster district);
		public abstract int isDistrictPresent(String districtName);
}
