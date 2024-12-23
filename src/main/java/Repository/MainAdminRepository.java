package Repository;

import Model.MainAdminMaster;

public interface MainAdminRepository {
 public abstract boolean addNewCustomer(MainAdminMaster mainAdmin);
	public abstract boolean validateLogin(String username, String password);
}
