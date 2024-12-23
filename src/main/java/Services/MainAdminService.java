package Services;

import Model.MainAdminMaster;

public interface MainAdminService {
	public abstract boolean addNewCustomer(MainAdminMaster mainAdmin);
		public abstract boolean validateLogin(String username, String password);
}
