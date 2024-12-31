package Services;

import java.util.List;
import java.util.Optional;

import Model.DistrictMaster;
import Model.HotelMaster;
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

		@Override
	public Optional<List<HotelMaster>> DistrictWiseHotel(String stateName, String districtName) {
		// TODO Auto-generated method stub
		return districtRepo.DistrictWiseHotel(stateName, districtName);
	}

		@Override
		public boolean isDistrictUpdate(int districtId, String districtName) {
			// TODO Auto-generated method stub
			return districtRepo.isDistrictUpdate(districtId, districtName);
		}

		@Override
		public boolean isDistrictDelete(int districtId) {
			// TODO Auto-generated method stub
			return districtRepo.isDistrictDelete(districtId);
		}

}
