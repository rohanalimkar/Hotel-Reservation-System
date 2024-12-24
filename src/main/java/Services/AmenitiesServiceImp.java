package Services;

import Model.AmenitiesMaster;
import Repository.AmenitiesRepository;
import Repository.AmenitiesRepositoryImp;
public class AmenitiesServiceImp implements AmenitiesService {

	AmenitiesRepository amenityRepo=new AmenitiesRepositoryImp();
	@Override
	public boolean addAmenities(AmenitiesMaster amenities) {
		// TODO Auto-generated method stub
		return amenityRepo.addAmenities(amenities);
	}

	@Override
	public boolean updateAmenities(String amenityName,String description,int amenityId) {
		// TODO Auto-generated method stub
		return amenityRepo.updateAmenities(amenityName, description, amenityId);
	}

	@Override
	public int getAmenityId(String amenityName) {
		// TODO Auto-generated method stub
		return amenityRepo.getAmenityId(amenityName);
	}

}
