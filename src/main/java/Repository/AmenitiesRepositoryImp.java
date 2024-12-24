package Repository;

import Model.AmenitiesMaster;

public class AmenitiesRepositoryImp extends DBState implements AmenitiesRepository {

	@Override
	public boolean addAmenities(AmenitiesMaster amenities) {
		try {
			
			ps=con.prepareStatement("insert into amenities(amenityName,description) values(?,?)");
			ps.setString(1, amenities.getAmenityName());
			ps.setString(2, amenities.getDescription());
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			
			return false;
		}
		
	}

	@Override
	public boolean updateAmenities(String amenityName,String description,int amenityId) {
		try {
			ps=con.prepareStatement("update amenities set amenityName=?,description=? where amenityId=?");
			ps.setString(1, amenityName);
			ps.setString(2, description);
			ps.setInt(3, amenityId);
			int result=ps.executeUpdate();
			while(rs.next())
			{
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}

	@Override
	public boolean deleteAmenities(String amenityName) {
		try {
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	@Override
	public boolean allAmenities(AmenitiesMaster amenities) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getAmenityId(String amenityName) {
		try {
			ps=con.prepareStatement("select amenityId from amenities where amenityName=?");
			ps.setString(1, amenityName);
			rs=ps.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1);
				
			}else {
				return 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

}
