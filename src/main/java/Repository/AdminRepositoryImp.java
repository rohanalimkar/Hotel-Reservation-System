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
	public boolean isaddNewCustomer(AdminMaster admin) {
		
		
		 try {
		 
			ps=con.prepareStatement("insert into admin(adminId,username,password,hotelId) values('0',?,?,?,)");
			ps.setString(1, admin.getUsername());
			ps.setString(2, admin.getPassword());
			ps.setString(3, admin.getHotelId());
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	} 
	
//-------------Validating the Customer----------------------	
	@Override
	public boolean validateLogin(String userName, String password) {
		try {
			ps=con.prepareStatement("SELECT * FROM admin WHERE userName = ? AND password = ?");
			ps.setString(1, userName);
			ps.setString(2, password);
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

}