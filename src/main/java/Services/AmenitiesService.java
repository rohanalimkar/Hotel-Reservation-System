package Services;

import Model.AmenitiesMaster;

public interface AmenitiesService {
public abstract boolean addAmenities(AmenitiesMaster amenities);
public abstract int getAmenityId(String amenityName);
	public abstract boolean updateAmenities(String amenityName,String description,int amenityId);
}
