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
	public int isHotelPresent(String hotelName,String stateName,String districtName,String cityName) {
		
	
		 try {
			ps=con.prepareStatement("SELECT h.hotelId FROM hotel h JOIN districtStateCityJoin dscj ON h.hotelLocation = dscj.districtStateCityJoinId JOIN state s ON dscj.stateId = s.stateId\r\n JOIN district d ON dscj.districtId = d.districtId JOIN city c ON dscj.cityId = c.cityId WHERE s.stateName =? And d.districtName=? And c.cityName=? and h.hotelName=?");
			ps.setString(1,stateName);
			ps.setString(2,districtName);
			ps.setString(3,cityName);
			ps.setString(4,hotelName);
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
	
	
	//-----------Fetch User name From Database--------------------
		@Override
		public String getHotelName(String hotelEmail) {
			HotelMaster hotel = null;
			try {
				ps=con.prepareStatement("SELECT a.adminId, a.username, h.hotelName FROM admin a JOIN hotel h ON a.hotelId = h.hotelId WHERE a.hotelEmail =?");
				ps.setString(1, hotelEmail);
				rs = ps.executeQuery();
				if (rs.next()) {
		            String hotelName = rs.getString("hotelName");

		            hotel = new HotelMaster();
		            hotel.setHotelName(hotelName);
		            return "Welcome to "+ hotel.getHotelName() +" Admin Pannel";
		        } else {
		            
		            return null;
		        }
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}

		@Override
		public String getHotelName(int hotelId) {
			HotelMaster hotel = null;
			try {
				ps=con.prepareStatement("SELECT h.hotelName FROM admin a JOIN hotel h ON a.hotelId = h.hotelId WHERE a.hotelId =?");
				ps.setInt(1, hotelId);
				rs = ps.executeQuery();
				if (rs.next()) {
		            
		            String hotelName = rs.getString("hotelName");

		            hotel = new HotelMaster();
		            hotel.setHotelName(hotelName);
		            return hotel.getHotelName();
		        } else {
		            
		            return null;
		        }
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

}
