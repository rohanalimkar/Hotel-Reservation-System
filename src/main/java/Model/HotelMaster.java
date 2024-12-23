package Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class HotelMaster {
private int hotelId;
private String hotelName;
private int hotelLocation;
private String status;
public HotelMaster(){}
public HotelMaster(int hotelId,String hotelName,int hotelLocation,String status){
	this.hotelId=hotelId;
	this.hotelName=hotelName;
	this.hotelLocation=hotelLocation;
	this.status=status;	
}
}
