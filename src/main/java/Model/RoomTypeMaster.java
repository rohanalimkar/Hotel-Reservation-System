package Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class RoomTypeMaster {
private String typeName;
private String description;
public RoomTypeMaster(){}
public RoomTypeMaster(String typeName,String description){
	this.typeName=typeName;
	this.description=description;
}
}
