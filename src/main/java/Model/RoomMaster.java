package Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RoomMaster {
private int hotelId;
private String roomNumber;
private String roomStatus;
private int roomTypeId;
private float roomPrice;
private String roomType;
public RoomMaster() {}
public RoomMaster(String roomNumber,String roomType,float roomPrice) {
	this.roomNumber=roomNumber;
	this.roomType=roomType;
	this.roomPrice=roomPrice;
}
public RoomMaster(int hotelId,String roomNumber,int roomTypeId,String roomStatus,float roomPrice) {
	this.hotelId=hotelId;
	this.roomNumber=roomNumber;
	this.roomTypeId=roomTypeId;
	this.roomStatus=roomStatus;
	this.roomPrice=roomPrice;
}
}
