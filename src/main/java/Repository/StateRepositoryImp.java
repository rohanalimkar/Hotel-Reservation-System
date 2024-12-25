package Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import Client_Application_Main.HotelReservationSystem;
import Model.HotelMaster;
import Model.StateMaster;
import model.StateModel;

public class StateRepositoryImp extends DBState implements StateRepository { //main class
List<HotelMaster>  list=new ArrayList<HotelMaster>();
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
			ps=con.prepareStatement("SELECT h.hotelName FROM hotel h JOIN districtStateCityJoin dscj ON h.hotelLocation = dscj.districtStateCityJoinId JOIN state s ON dscj.stateId = s.stateId JOIN district d ON dscj.districtId = d.districtId JOIN city c ON dscj.cityId = c.cityId WHERE s.stateName ='Maharashtra';");
			rs=ps.executeQuery();
			list.clear();
			while(rs.next())
			{
				String hotelName=rs.getString("hotelName");
				 //stateName=rs.getString("stateName");
				list.add(new HotelMaster(hotelName));
			}
			return list.isEmpty() ? Optional.empty():Optional.of(list);
		} catch (Exception e) {
			System.out.println("Error is:"+e);
			return Optional.empty();
		}
	}

	
}
