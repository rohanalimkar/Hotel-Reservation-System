package Repository;

import Model.CityMaster;

public interface CityRepository {

 public abstract boolean addCity(CityMaster city);
	public abstract int isCityPresent(String cityName);

}
