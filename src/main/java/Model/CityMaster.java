package Model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class CityMaster {
private int cityId;
private String cityName;
public CityMaster(){}
public CityMaster(int cityId,String cityName){
	this.cityId=cityId;
	this.cityName=cityName;
}
}
