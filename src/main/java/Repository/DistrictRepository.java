package Repository;

import Model.DistrictMaster;

public interface DistrictRepository {

	 public abstract boolean addDistrict(DistrictMaster district);
	public abstract int isDistrictPresent(String districtName);
	 
}
