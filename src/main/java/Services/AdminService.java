package Services;

import Model.AdminMaster;

public interface AdminService {
	public abstract boolean isaddNewAdmin(AdminMaster admin);
	public abstract boolean validateLogin(String username, String password);
	public abstract int getHotelId(String hotelEmail);
	public abstract boolean isUpdatedAdmin(String username,String hotelEmail,String password);
}
