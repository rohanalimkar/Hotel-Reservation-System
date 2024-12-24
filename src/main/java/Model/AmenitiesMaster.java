package Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AmenitiesMaster {
private int amenityId;
private String amenityName;
private String description;
public AmenitiesMaster(){}
public AmenitiesMaster(int amenityId,String amenityName,String description){
this.amenityId=amenityId;
this.amenityName=amenityName;
this.description=description;
}
}
