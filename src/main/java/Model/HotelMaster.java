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
private String state;
private String districtName;
private String cityName;
public HotelMaster(){}
public HotelMaster(String hotelName){
	this.hotelName=hotelName;
}
public HotelMaster(int hotelId,String hotelName,int hotelLocation,String status){
	this.hotelId=hotelId;
	this.hotelName=hotelName;
	this.hotelLocation=hotelLocation;
	this.status=status;	
}
}
