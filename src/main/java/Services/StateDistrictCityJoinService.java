package Services;

import Model.StateDistrictCityJoinMaster;

public interface StateDistrictCityJoinService {
	public abstract boolean isaddNewStateDistrictCityJoin(StateDistrictCityJoinMaster sdcmaster);
	public abstract int validateStateDistrictCity(int stateId, int districtId,int cityId);
}
