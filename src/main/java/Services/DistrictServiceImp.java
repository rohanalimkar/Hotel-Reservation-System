package Services;

import Model.DistrictMaster;
import Repository.DistrictRepository;
import Repository.DistrictRepositoryImp;

public class DistrictServiceImp implements DistrictService {

	DistrictRepository districtRepo=new DistrictRepositoryImp(); 
	
	@Override
	public boolean addDistrict(DistrictMaster district) {
		
		return districtRepo.addDistrict(district);
	}

	@Override
	public int isDistrictPresent(String districtName) {
		
		return districtRepo.isDistrictPresent(districtName);
	}

}
