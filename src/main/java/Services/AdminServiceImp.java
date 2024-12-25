package Services;

import Model.AdminMaster;
import Repository.AdminRepositoryImp;
public class AdminServiceImp implements AdminService {

	AdminRepositoryImp adminRepo=new AdminRepositoryImp();
	@Override
	public boolean isaddNewAdmin(AdminMaster admin) {
		
		return adminRepo.isaddNewAdmin(admin);
	}

	@Override
	public boolean validateLogin(String username, String password) {
		
		return adminRepo.validateLogin(username, password);
	}

	@Override
	public int getHotelId(String hotelEmail) {
		// TODO Auto-generated method stub
		return adminRepo.getHotelId(hotelEmail);
	}

	@Override
	public boolean isUpdatedAdmin(String username, String hotelEmail, String password) {
		
		return adminRepo.isUpdatedAdmin(username, hotelEmail, password);
	}

}
