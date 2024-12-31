package Services;

import java.util.List;
import java.util.Optional;

import Model.CityMaster;
import Model.HotelMaster;

public interface CityService {
	public abstract boolean addCity(CityMaster city);
	public abstract int isCityPresent(String cityName);
	public abstract Optional<List<HotelMaster>> CityWiseHotel(String stateName,String districtName,String cityName);
	public abstract boolean isUpdateCity(int cityId,String UpdatedName);
	public abstract boolean isDeleteCity(int cityId);
}
