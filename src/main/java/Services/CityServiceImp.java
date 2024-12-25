package Services;

import java.util.List;
import java.util.Optional;

import Model.CityMaster;
import Model.HotelMaster;
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

	@Override
	public Optional<List<HotelMaster>> CityWiseHotel(String stateName, String districtName, String cityName) {
		// TODO Auto-generated method stub
		return cityRepo.CityWiseHotel(stateName, districtName, cityName);
	}

	

}
