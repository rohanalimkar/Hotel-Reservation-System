package Repository;

import java.util.List;
import java.util.Optional;

import Model.HotelAmenitiesMaster;

public class HotelAmenitiesRepositoryImp extends DBState implements HotelAmenitiesRepository {

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
	public Optional<List<HotelAmenitiesMaster>> getAllAmenities() {
		
		return Optional.empty();
	}

}
