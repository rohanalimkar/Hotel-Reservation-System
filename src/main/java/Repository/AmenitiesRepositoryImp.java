package Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.AmenitiesMaster;

public class AmenitiesRepositoryImp extends DBState implements AmenitiesRepository {
	List<AmenitiesMaster>  list=new ArrayList<AmenitiesMaster>();
	@Override
	public boolean addAmenities(AmenitiesMaster amenities) {
		try {
			
			ps=con.prepareStatement("insert into amenities(amenityName,description) values(?,?)");
			ps.setString(1, amenities.getAmenityName());
			ps.setString(2, amenities.getDescription());
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
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
			return result>0?true:false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public boolean deleteAmenities(String amenityName) {
		try {
			ps=con.prepareStatement("delete amenities  where amenityName=?");
			ps.setString(1, amenityName);
			int result=ps.executeUpdate();
			return result>0?true:false;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		
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
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public Optional<List<AmenitiesMaster>> getAllAmenities() {
		try {
			ps=con.prepareStatement("select * from amenities");
			rs=ps.executeQuery();
			list.clear();
			while(rs.next())
			{
				String amenityName=rs.getString("amenityName");
				 //stateName=rs.getString("stateName");
				list.add(new AmenitiesMaster(amenityName));
			}
			return list.isEmpty() ? Optional.empty():Optional.of(list);
		} catch (Exception e) {
			System.out.println("Error is:"+e);
			return Optional.empty();
		}
	}

}
