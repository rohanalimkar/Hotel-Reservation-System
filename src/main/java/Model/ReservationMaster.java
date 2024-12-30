package Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class ReservationMaster {

private int hotelId;
private int customerId;
private int roomId;
private String reservationDate;
private String checkInDate;
private String checkOutDate;
private int numberOfGuests;
private float totalAmount;
private float advanceAmount;
private float remainingAmount;
private String status;

public ReservationMaster(){}
public ReservationMaster(int hotelId,
						 int customerId,
						 int roomId,
						 String reservationDate,
						 String checkInDate,
						 int numberOfGuests,
						 float totalAmount,
						 float advanceAmount,
						 float remainingAmount,
						 String status){
	this.hotelId=hotelId;
	this.customerId=customerId;
	this.roomId=roomId;
	this.reservationDate=reservationDate;
	this.checkInDate=checkInDate;
	this.numberOfGuests=numberOfGuests;
	this.totalAmount=totalAmount;
	this.advanceAmount=advanceAmount;
	this.remainingAmount=remainingAmount;
	this.status=status;
	
}
}
