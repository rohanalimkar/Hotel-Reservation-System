package Services;

import java.util.List;
import java.util.Optional;

import Model.AmenitiesMaster;

public interface AmenitiesService {
public abstract boolean addAmenities(AmenitiesMaster amenities);
public abstract int getAmenityId(String amenityName);
	public abstract boolean updateAmenities(String amenityName,String description,int amenityId);
	public abstract boolean deleteAmenities(String amenityName);
	public abstract Optional<List<AmenitiesMaster>> getAllAmenities();
}
