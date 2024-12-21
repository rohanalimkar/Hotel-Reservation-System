package Model;

import java.security.PublicKey;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter	
@EqualsAndHashCode
public class DistrictMaster {
private int districtId;
private String districtName;
public DistrictMaster() {}
public DistrictMaster(int districtId,String districtName) {
	this.districtId=districtId;
	this.districtName= districtName;
}
}
