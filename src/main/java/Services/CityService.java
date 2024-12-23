package Services;

import Model.CityMaster;

public interface CityService {
	public abstract boolean addCity(CityMaster city);
	public abstract int isCityPresent(String cityName);
}
