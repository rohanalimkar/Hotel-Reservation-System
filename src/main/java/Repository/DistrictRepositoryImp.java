package Repository;

import java.sql.SQLException;
<<<<<<< HEAD
import java.util.*;
=======
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
>>>>>>> branch 'main' of https://github.com/rohanalimkar/Hotel-Reservation-System.git

import Model.DistrictMaster;
import Model.HotelMaster;
public class DistrictRepositoryImp extends DBState implements DistrictRepository {
	List<HotelMaster>  list=new ArrayList<HotelMaster>();
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
<<<<<<< HEAD
			ps=con.prepareStatement("SELECT h.hotelName FROM hotel h JOIN districtStateCityJoin dscj ON h.hotelLocation = dscj.districtStateCityJoinId JOIN state s ON dscj.stateId = s.stateId JOIN district d ON dscj.districtId = d.districtId JOIN city c ON dscj.cityId = c.cityId WHERE s.stateName =? and districtName=?");
			ps.setString(1, stateName);
			ps.setString(2, districtName);
			rs=ps.executeQuery();
			list.clear();
			while(rs.next())
			{
				String hotelName=rs.getString("hotelName");
				 //stateName=rs.getString("stateName");
=======
			ps=con.prepareStatement("SELECT h.hotelName, s.stateName FROM hotel h JOIN districtStateCityJoin dscj ON h.hotelLocation = dscj.districtStateCityJoinId JOIN state s ON dscj.stateId = s.stateId JOIN district d ON dscj.districtId = d.districtId JOIN city c ON dscj.cityId = c.cityId WHERE s.stateName =? and d.districtName=?");
			ps.setString(1, stateName);
			ps.setString(2, districtName);
			rs=ps.executeQuery();
			list.clear();
			while(rs.next())
			{
				String hotelName=rs.getString("hotelName");
>>>>>>> branch 'main' of https://github.com/rohanalimkar/Hotel-Reservation-System.git
				list.add(new HotelMaster(hotelName));
			}
			return list.isEmpty() ? Optional.empty():Optional.of(list);
		} catch (Exception e) {
			System.out.println("Error is:"+e);
			return Optional.empty();
		}
	}

}
