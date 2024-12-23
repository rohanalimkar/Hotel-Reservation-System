package Repository;

import java.sql.SQLException;

import Model.CityMaster;

public class CityRepositoryImp extends DBState implements CityRepository {

	@Override
	public boolean addCity(CityMaster city) {

		 try {
			ps=con.prepareStatement("insert into city values('0',?)");
			ps.setString(1,city.getCityName());
			int result=ps.executeUpdate();
			return result>0?true:false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int isCityPresent(String cityName) {
		
		try {
			ps=con.prepareStatement("select cityId from city where cityName=?");
			ps.setString(1,cityName);
			rs=ps.executeQuery();
			if(rs.next())
			{
				int cityId=rs.getInt(1);
				return cityId;

			}else {
	            // Return null if city fetching failed (city not found)
	            return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}

}
