package Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.CityMaster;
import Model.HotelMaster;

public class CityRepositoryImp extends DBState implements CityRepository {
	List<HotelMaster>  list=new ArrayList<HotelMaster>();
	HotelMaster hotel=null;
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
			ps=con.prepareStatement("select h.hotelId, h.hotelName, s.stateName, d.districtName, c.cityName from hotel h join districtStateCityJoin dscj on h.hotelLocation = dscj.districtStateCityJoinId join state s on dscj.stateId = s.stateId join district d on dscj.districtId = d.districtId join city c on dscj.cityId = c.cityId where s.stateName = ? and districtName=? and cityName=? and h.status = 'Active'");
			ps.setString(1, stateName);
			ps.setString(2, districtName);
			ps.setString(3, CityName);
			rs=ps.executeQuery();
			list.clear();
			while(rs.next())
			{
				int hotelId=rs.getInt("hotelId");
				String hotelName=rs.getString("hotelName");
				String stateName1=rs.getString("stateName");
				 districtName=rs.getString("districtName");
				String cityName=rs.getString("cityName");
				 hotel=new HotelMaster();
				hotel.setHotelId(hotelId);
				hotel.setHotelName(hotelName);
				hotel.setDistrictName(districtName);
				hotel.setCityName(cityName);
				hotel.setState(stateName1);
				list.add(hotel);
			}
			return list.isEmpty() ? Optional.empty():Optional.of(list);
		} catch (Exception e) {
			System.out.println("Error is:"+e);
			return Optional.empty();
		}
	}

	@Override
	public boolean isUpdateCity(int cityId, String UpdatedName) {
		try {
			ps=con.prepareStatement("update city set cityName=? where cityId=?");
			ps.setString(1,UpdatedName);
			ps.setInt(2, cityId);
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isDeleteCity(int cityId) {
		try {
			ps=con.prepareStatement("delete from city where cityId=?");
			ps.setInt(1, cityId);
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
