package Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class HotelAmenitiesMaster {
private int hotelId;
private int amenityId;
private float price;
private String amenityName;
public HotelAmenitiesMaster(){}
public HotelAmenitiesMaster(int hotelId,int amenityId,float price){
	this.hotelId=hotelId;
	this.amenityId=amenityId;
	this.price=price;
}
}
