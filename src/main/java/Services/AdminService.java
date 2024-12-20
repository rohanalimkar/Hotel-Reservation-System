package Services;

import Model.AdminMaster;

public interface AdminService {
	public abstract boolean isaddNewCustomer(AdminMaster admin);
	public abstract boolean validateLogin(String username, String password);
}
