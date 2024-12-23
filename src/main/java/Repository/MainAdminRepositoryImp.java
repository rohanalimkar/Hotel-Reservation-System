package Repository;

import java.sql.SQLException;

import Model.AdminMaster;
import Model.MainAdminMaster;

public class MainAdminRepositoryImp extends DBState implements MainAdminRepository {

	@Override
	public boolean addNewCustomer(MainAdminMaster mainAdmin) {
		try {
			 
			ps=con.prepareStatement("insert into mainadmin(mainAdminId,adminName,password) values('0',?,?)");
			ps.setString(1, mainAdmin.getAdminName());
			ps.setString(2, mainAdmin.getPassword());
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean validateLogin(String adminName, String password) {
		try {
			ps=con.prepareStatement("SELECT * FROM mainadmin WHERE adminName = ? AND password = ?");
			ps.setString(1, adminName);
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
