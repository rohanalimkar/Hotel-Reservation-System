package Repository;

import Model.StateDistrictCityJoinMaster;

public interface StateDistrictCityJoinRepository{

	
	public abstract boolean isaddNewStateDistrictCityJoin(StateDistrictCityJoinMaster stateDistrictCity);
	public abstract int validateStateDistrictCity(int stateId, int districtId,int cityId);
	 
}
