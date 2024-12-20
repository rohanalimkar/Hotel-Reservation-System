package Repository;

import Model.AdminMaster;

public interface AdminRepository {
	public abstract boolean isaddNewCustomer(AdminMaster admin);
	public abstract boolean validateLogin(String username, String password);
}
