package Repository;

import Model.CustomerMaster;

public interface CustomerRepository {
	public abstract boolean isaddNewCustomer(CustomerMaster customer);
	public abstract boolean validateLogin(String email, String password);
	public abstract String getFirstName(String customerEmail);
	public abstract int getCustomerId(String customerEmail);
}
