package Model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class AdminMaster {
private String username;
private String password;
private String hotelId;
public AdminMaster() {
	
}
}
