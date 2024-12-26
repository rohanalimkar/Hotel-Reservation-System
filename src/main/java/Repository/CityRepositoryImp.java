package Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.CityMaster;
import Model.HotelMaster;

public class CityRepositoryImp extends DBState implements CityRepository {
	List<HotelMaster>  list=new ArrayList<HotelMaster>();
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

	@Override
	public Optional<List<HotelMaster>> CityWiseHotel(String stateName, String districtName, String CityName) {
		try {
			ps=con.prepareStatement("SELECT h.hotelName, s.stateName FROM hotel h JOIN districtStateCityJoin dscj ON h.hotelLocation = dscj.districtStateCityJoinId JOIN state s ON dscj.stateId = s.stateId JOIN district d ON dscj.districtId = d.districtId JOIN city c ON dscj.cityId = c.cityId WHERE s.stateName ='?' and d.districtName=?  and h.status=Active");
			ps.setString(1, stateName);
			ps.setString(2, districtName);
			rs=ps.executeQuery();
			list.clear();
			while(rs.next())
			{
				String hotelName=rs.getString("hotelName");
				list.add(new HotelMaster(hotelName));
			}
			return list.isEmpty() ? Optional.empty():Optional.of(list);
		} catch (Exception e) {
			System.out.println("Error is:"+e);
			return Optional.empty();
		}
	}

}
