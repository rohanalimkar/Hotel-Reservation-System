package Repository;

import java.sql.SQLException;

import Model.HotelMaster;

public class HotelRepositoryImp extends DBState implements HotelRepository {

	@Override
	public boolean addNewHotel(HotelMaster hotel) {
		
		 try {
			ps=con.prepareStatement("insert into hotel(hotelName,hotelLocation) values(?,?)");
			ps.setString(1,hotel.getHotelName());
			ps.setInt(2,hotel.getHotelLocation());
			int result=ps.executeUpdate();
			return result>0?true:false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int isHotelPresent(String hotelName) {
		
	
		 try {
			ps=con.prepareStatement("select hotelId from hotel where hotelLocation=?");
			ps.setString(1,hotelName);
			rs=ps.executeQuery();
			if(rs.next())
			{
				int hotelId=rs.getInt(1);
				return hotelId;

			}else {
	            //hotel not found)
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
