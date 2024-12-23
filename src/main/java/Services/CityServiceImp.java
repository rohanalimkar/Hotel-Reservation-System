package Services;

import Model.CityMaster;
import Repository.CityRepository;
import Repository.CityRepositoryImp;

public class CityServiceImp implements CityService {

	CityRepository cityRepo=new CityRepositoryImp();
	@Override
	public boolean addCity(CityMaster city) {
		
		return cityRepo.addCity(city);
	}

	@Override
	public int isCityPresent(String cityName) {
		
		return cityRepo.isCityPresent(cityName);
	}

}
