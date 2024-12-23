package Repository;

import java.sql.SQLException;

import Model.StateDistrictCityJoinMaster;

public class StateDistrictCityJoinRepositoryImp extends DBState implements StateDistrictCityJoinRepository {

	
//-----------------Add New StateDistrictCityJoin---------------------
	@Override
	public boolean isaddNewStateDistrictCityJoin(StateDistrictCityJoinMaster stateDistrictCity) {
		
		
		 try {
			ps=con.prepareStatement("insert into districtstatecityjoin values('0',?,?,?)");
			ps.setInt(1,stateDistrictCity.getStateId());
			ps.setInt(2,stateDistrictCity.getDistrictId());
			ps.setInt(3,stateDistrictCity.getCityId());
			int result=ps.executeUpdate();
			if(result>0){
				return true;
				}
			else{
				return false;
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
//-------------------Check StateDistrictCityJoin status---------------
	@Override
	public int validateStateDistrictCity(int stateId, int districtId, int cityId) {
		
		
		
		 try {
			ps=con.prepareStatement("select districtStateCityJoinId from districtstatecityjoin where stateId=? and districtId=? and cityId=?");
			ps.setInt(1,stateId);
			ps.setInt(2,districtId);
			ps.setInt(3,cityId);
			rs=ps.executeQuery();
			if(rs.next())
			{
				int districtStateCityJoinId=rs.getInt(1);
				return districtStateCityJoinId;

			}else {
	            //record not found)
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

}
