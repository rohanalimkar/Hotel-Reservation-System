package Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.HotelMaster;
import Model.StateMaster;

public class StateRepositoryImp extends DBState implements StateRepository { //main class
List<HotelMaster>  list=new ArrayList<HotelMaster>();
HotelMaster hotel=null;
	@Override
	public boolean addState(StateMaster state) {

		try {
			ps=con.prepareStatement("insert into state values('0',?)");
			ps.setString(1,state.getStateName());
			int result=ps.executeUpdate();
			return result>0?true:false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	//----------------------check State is present or not
	@Override
	public int isStatePresent(String stateName) {
		
		try {
			ps=con.prepareStatement("select stateId from state where stateName=?");
			ps.setString(1,stateName);
			rs=ps.executeQuery();
			if(rs.next())
			{
				int stateId=rs.getInt(1);
				return stateId;

			}else {
	            //state not found)
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
	public Optional<List<HotelMaster>> StateWiseHotel(String stateName) {

		
		 try {
			ps=con.prepareStatement("select h.hotelId, h.hotelName, s.stateName, d.districtName, c.cityName from hotel h join districtStateCityJoin dscj on h.hotelLocation = dscj.districtStateCityJoinId join state s on dscj.stateId = s.stateId join district d on dscj.districtId = d.districtId join city c on dscj.cityId = c.cityId where s.stateName = ? and h.status = 'Active'");
			ps.setString(1, stateName);
			rs=ps.executeQuery();
			list.clear();
			while(rs.next())
			{
				int hotelId=rs.getInt("hotelId");
				String hotelName=rs.getString("hotelName");
				String stateName1=rs.getString("stateName");
				String districtName=rs.getString("districtName");
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
	public boolean isUpdateState(int StateId, String UpdatedName) {
		try {
			ps=con.prepareStatement("update state set stateName=? where StateId=?");
			ps.setString(1,UpdatedName);
			ps.setInt(2, StateId);
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean isDeleteState(int Stateid) {
		try {
			ps=con.prepareStatement("delete from state where Stateid=?");
			ps.setInt(1, Stateid);
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	
}
