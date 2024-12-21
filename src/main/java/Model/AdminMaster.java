package Model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@EqualsAndHashCode
public class AdminMaster {
private int adminId;
private String username;
private String password;
private String hotelId;
public AdminMaster() {}
public AdminMaster(int adminId,String username,String password,String hotelId) {
	this.adminId=adminId;
	this.username=username;
	this.password=password;
	this.hotelId=hotelId;
}
}
