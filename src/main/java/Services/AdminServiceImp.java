package Services;

import Model.AdminMaster;
import Repository.AdminRepositoryImp;
public class AdminServiceImp implements AdminService {

	AdminRepositoryImp adminRepo=new AdminRepositoryImp();
	@Override
	public boolean isaddNewCustomer(AdminMaster admin) {
		
		return adminRepo.isaddNewCustomer(admin);
	}

	@Override
	public boolean validateLogin(String username, String password) {
		
		return adminRepo.validateLogin(username, password);
	}

	
}
