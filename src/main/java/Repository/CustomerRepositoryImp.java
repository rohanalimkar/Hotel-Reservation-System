package Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.CustomerMaster;

public class CustomerRepositoryImp extends DBState implements CustomerRepository {
	List<CustomerMaster>  list=new ArrayList<CustomerMaster>();

//---------------Add New Customer-----------------------
	@Override
	public boolean isaddNewCustomer(CustomerMaster customer) {
		try {
			ps=con.prepareStatement("insert into customer(customerId,firstName,lastName,customerEmail,idProof,customerPhoneNumber,CustomerAddress,dateOfBirth,password) values('0',?,?,?,?,?,?,?,?)");
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getCustomerEmail());
			ps.setString(4, customer.getIdProof());
			ps.setString(5, customer.getCustomerPhoneNumber());
			ps.setString(6, customer.getCustomerAddress());
			ps.setString(7, customer.getDateOfBirth());
			ps.setString(8, customer.getPassword());
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
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

//-------------Validating the Customer----------------------
	@Override
	public boolean validateLogin(String CustomerEmail, String password) {
		
		try {
			ps=con.prepareStatement("SELECT * FROM customer WHERE customerEmail = ? AND password = ?");
			ps.setString(1, CustomerEmail);
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
		
	}

//-----------Fetch User name From Database--------------------
	@Override
	public String getFirstName(String customerEmail) {
		CustomerMaster customer = null;
		try {
			ps=con.prepareStatement("SELECT firstName,lastName FROM customer WHERE customerEmail = ?");
			ps.setString(1, customerEmail);
			rs = ps.executeQuery();
			if (rs.next()) {
	            // Retrieve customer details from database
	            String firstName = rs.getString("firstName");
	            String lastName = rs.getString("lastName");

	            // Create and return a CustomerMaster object with the customer details
	            customer = new CustomerMaster();
	            customer.setFirstName(firstName);  // Set first name
	            customer.setLastName(lastName);    // Set last name
	            return "Welcome " + customer.getFirstName() + " " + customer.getLastName();
	        } else {
	            // Return null if login failed (customer not found)
	            return null;
	        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
