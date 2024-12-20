package Model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class CustomerMaster {
private int customerId;
private String firstName;
private String lastName;
private String customerEmail;
private String idProof;
private String customerPhoneNumber;
private String CustomerAddress;
private String dateOfBirth;
private String registrationDate;
private String password;

public CustomerMaster() {
	
}
public CustomerMaster(int customerId,String firstName,String lastName,String customerEmail,String idProof,String customerPhoneNumber,String dateOfBirth,String password) {
	this.customerId=customerId;
	this.firstName=firstName;
	this.lastName=lastName;
	this.customerEmail=customerEmail;
	this.idProof=idProof;
	this.customerPhoneNumber=customerPhoneNumber;
	this.dateOfBirth=dateOfBirth;
	this.password=password;
}

}
