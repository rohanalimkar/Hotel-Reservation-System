package Repository;

import java.sql.SQLException;

import Model.DistrictMaster;
public class DistrictRepositoryImp extends DBState implements DistrictRepository {

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
		DistrictMaster district=null;
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

}
