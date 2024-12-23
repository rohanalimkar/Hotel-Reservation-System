package Model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class StateDistrictCityJoinMaster {
	private int districtStateCityJoinId;
	private int stateId;
	private int districtId;
	private int cityId;
public StateDistrictCityJoinMaster(){}
public StateDistrictCityJoinMaster(int districtStateCityJoinId,int stateId,int districtId,int cityId){
	this.districtStateCityJoinId=districtStateCityJoinId;
	this.stateId=stateId;
	this.districtId=districtId;
	this.cityId=cityId;
}
}
