package Services;

import Model.CustomerMaster;

public interface CustomerService {

	public abstract boolean isaddNewCustomer(CustomerMaster customer);
	public abstract boolean validateLogin(String email, String password);
	public abstract String getFirstName(String customerEmail);
}
