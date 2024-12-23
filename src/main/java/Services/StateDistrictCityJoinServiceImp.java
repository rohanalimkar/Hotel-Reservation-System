package Services;

import Model.StateDistrictCityJoinMaster;
import Repository.StateDistrictCityJoinRepository;
import Repository.StateDistrictCityJoinRepositoryImp;

public class StateDistrictCityJoinServiceImp implements StateDistrictCityJoinService {

	StateDistrictCityJoinRepository sdcRepo=new StateDistrictCityJoinRepositoryImp(); 
	
	@Override
	public boolean isaddNewStateDistrictCityJoin(StateDistrictCityJoinMaster stateDistrictCity) {
		
		return sdcRepo.isaddNewStateDistrictCityJoin(stateDistrictCity);
	}

	@Override
	public int validateStateDistrictCity(int stateId, int districtId, int cityId) {
		
		return sdcRepo.validateStateDistrictCity(stateId, districtId, cityId);
	}

}
