package Services;

import Model.MainAdminMaster;
import Repository.MainAdminRepository;
import Repository.MainAdminRepositoryImp;

public class MainAdminServiceImp implements MainAdminService{

	MainAdminRepository mainAdminRepo=new MainAdminRepositoryImp();
	
	@Override
	public boolean addNewCustomer(MainAdminMaster mainAdmin) {
		
		return mainAdminRepo.addNewCustomer(mainAdmin);
	}

	@Override
	public boolean validateLogin(String username, String password) {
		
		return mainAdminRepo.validateLogin(username, password);
	}


}
