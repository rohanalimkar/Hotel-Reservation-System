package Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.AmenitiesMaster;
import Model.HotelAmenitiesMaster;

public class HotelAmenitiesRepositoryImp extends DBState implements HotelAmenitiesRepository {
	List<HotelAmenitiesMaster>  list=new ArrayList<HotelAmenitiesMaster>();
	@Override
	public boolean addHotelAmenities(HotelAmenitiesMaster hotelAmenities) {
		try {
			ps=con.prepareStatement("insert into hotelAmenities(hotelId,amenityId,price) value(?,?,?)");
			ps.setInt(1, hotelAmenities.getHotelId());
			ps.setInt(2,hotelAmenities.getAmenityId());
			ps.setFloat(3,hotelAmenities.getPrice());
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateHotelAmenities(float price,int hotelId, int amenityId) {
		try {
			ps=con.prepareStatement("update hotelAmenities set price=? where hotelId=? and amenityId=?");
			ps.setFloat(1,price);
			ps.setInt(2, hotelId);
			ps.setInt(3,amenityId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public int getHotelAmenityId(int amenityId) {
		
		return 0;
	}

	@Override
	public boolean deleteHotelAmenities(int hotelId,int amenityId) {
		try {
			ps=con.prepareStatement("delete from hotelAmenities where hotelId=?,amenityId=?");
			ps.setInt(1,hotelId);
			ps.setInt(2,amenityId);
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Optional<List<HotelAmenitiesMaster>> getAllAmenities(int hotelId) {
	    try {
	        // Prepare the SQL query to fetch amenity details for the given hotel
	        String query = "SELECT a.amenityId, a.amenityName, ha.price " +
	                       "FROM hotelAmenities ha " +
	                       "JOIN amenities a ON ha.amenityId = a.amenityId " +
	                       "WHERE ha.hotelId = ?";

	        ps = con.prepareStatement(query);
	        ps.setInt(1, hotelId); 
	        rs = ps.executeQuery();
	        list.clear();
	        while (rs.next()) {
	            int amenityId = rs.getInt("amenityId");
	            String amenityName = rs.getString("amenityName");
	            float price = rs.getFloat("price");
	            HotelAmenitiesMaster amenity = new HotelAmenitiesMaster();
	            amenity.setAmenityId(amenityId);
	            amenity.setAmenityName(amenityName);
	            amenity.setPrice(price);
	            list.add(amenity);
	        }
	        return list.isEmpty() ? Optional.empty() : Optional.of(list);
	    } catch (Exception e) {
	        System.out.println("Error: " + e);
	        return Optional.empty();
	    }
	}

	@Override
	public float getHotelAmenityPrice(int hotelId,int amenityId) {
		try {
			ps=con.prepareStatement("SELECT ha.price  FROM hotelAmenities ha JOIN amenities a ON ha.amenityId = a.amenityId  WHERE ha.hotelId = ? and a.amenityId=?;");
			ps.setInt(1, hotelId);
			ps.setInt(2, amenityId);
			rs=ps.executeQuery();
			if(rs.next())
			{
				return rs.getFloat("price");
			}else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}



}
