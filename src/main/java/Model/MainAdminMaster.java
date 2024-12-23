package Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class MainAdminMaster {

	private int mainAdminId;
	private String adminName;
	private String password;

	public MainAdminMaster() {
	}

	public MainAdminMaster(int mainAdminId, String adminName, String password) {
		this.mainAdminId = mainAdminId;
		this.adminName = adminName;
		this.password = password;
	}
}
