package Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.AdminMaster;
import Model.CustomerMaster;

public class AdminRepositoryImp extends DBState implements AdminRepository {
	List<AdminMaster>  list=new ArrayList<AdminMaster>();

	//---------------Add New Admin-----------------------
	@Override
	public boolean isaddNewAdmin(AdminMaster admin) {
		
		
		 try {
		 
			ps=con.prepareStatement("insert into admin(adminId,username,password,hotelId,hotelEmail) values('0',?,?,?,?)");
			ps.setString(1, admin.getUsername());
			ps.setString(2, admin.getPassword());
			ps.setInt(3, admin.getHotelId());
			ps.setString(4, admin.getHotelEmail());
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	} 
	
//-------------Validating the Sub Admin----------------------	
	@Override
	public boolean validateLogin(String hotelEmail, String password) {
		try {
			ps=con.prepareStatement("SELECT * FROM admin WHERE password = ? and hotelEmail= ?");
			ps.setString(1, password);
			ps.setString(2, hotelEmail);
			rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}else {
			return false;
			}
		} catch (Exception e) {
			System.out.println("Database connection error: " + e.getMessage());
			return false;
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
	public int getHotelId(String hotelEmail) {
		try {
			ps=con.prepareStatement("select hotelId from admin where hotelEmail=?");
			ps.setString(1,hotelEmail);
			rs=ps.executeQuery();
			if(rs.next())
			{
				int hotelId=rs.getInt(1);
				return hotelId;

			}else {
	            return 0;
			}
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public boolean isUpdatedAdmin(String username, String hotelEmail, String password) {
		try {
			ps=con.prepareStatement("update admin set username=?,password=? where hotelEmail=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, hotelEmail);
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}