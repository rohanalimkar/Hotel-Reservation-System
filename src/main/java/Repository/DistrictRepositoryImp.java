package Repository;

import java.sql.SQLException;
import java.util.*;

import Model.DistrictMaster;
import Model.HotelMaster;
public class DistrictRepositoryImp extends DBState implements DistrictRepository {
	List<HotelMaster>  list=new ArrayList<HotelMaster>();
	HotelMaster hotel=null;
	@Override
	public boolean addDistrict(DistrictMaster district) {
		try {
			ps=con.prepareStatement("insert into district values('0',?)");
			ps.setString(1,district.getDistrictName());
			int result=ps.executeUpdate();
			return result>0?true:false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int isDistrictPresent(String districtName) {
		try {
			ps=con.prepareStatement("select districtId from district where districtName=?");
			ps.setString(1,districtName);
			rs=ps.executeQuery();
			if(rs.next())
			{
				int districtId=rs.getInt(1);
				return districtId;
			}else {
	            // Return null if district fetching failed (district not found)
	            return 0;
			}
		} catch (Exception e) {
			return 0;
		}
		finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
			}

	@Override
	public Optional<List<HotelMaster>> DistrictWiseHotel(String stateName, String districtName) {
		try {
			ps=con.prepareStatement("select h.hotelId, h.hotelName, s.stateName, d.districtName, c.cityName from hotel h join districtStateCityJoin dscj on h.hotelLocation = dscj.districtStateCityJoinId join state s on dscj.stateId = s.stateId join district d on dscj.districtId = d.districtId join city c on dscj.cityId = c.cityId where s.stateName = ? and d.districtName=? and h.status = 'Active'");
			ps.setString(1, stateName);
			ps.setString(2, districtName);
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
	public boolean isDistrictUpdate(int districtId, String districtName) {
		try {
			ps=con.prepareStatement("update district set districtName=? where districtId=?");
			ps.setString(1,districtName);
			ps.setInt(2, districtId);
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	}

	@Override
	public boolean isDistrictDelete(int districtId) {
		try {
			ps=con.prepareStatement("delete from district where districtId=?");
			ps.setInt(1, districtId);
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		}

}
