package Services;

import java.util.List;
import java.util.Optional;

import Model.AmenitiesMaster;
import Repository.AmenitiesRepository;
import Repository.AmenitiesRepositoryImp;
public class AmenitiesServiceImp implements AmenitiesService {

	AmenitiesRepository amenityRepo=new AmenitiesRepositoryImp();
	@Override
	public boolean addAmenities(AmenitiesMaster amenities) {
		
		return amenityRepo.addAmenities(amenities);
	}

	@Override
	public boolean updateAmenities(String amenityName,String description,int amenityId) {
		
		return amenityRepo.updateAmenities(amenityName, description, amenityId);
	}

	@Override
	public int getAmenityId(String amenityName) {
		
		return amenityRepo.getAmenityId(amenityName);
	}

	@Override
	public boolean deleteAmenities(String amenityName) {
		
		return amenityRepo.deleteAmenities(amenityName);
	}

	@Override
	public Optional<List<AmenitiesMaster>> getAllAmenities() {
		
		return amenityRepo.getAllAmenities();
	}

}
