package Client_Application_Main;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import Model.CustomerMaster;
import Model.StateMaster;
import Services.CustomerService;
import Services.CustomerServiceImp;
import Services.StateService;
import Services.StateServiceImp;
import model.StateModel;

public class HotelReservationSystem {

		public static Logger log = Logger.getLogger(HotelReservationSystem.class);
		static
		{
			
			SimpleLayout layout = new SimpleLayout();
			ConsoleAppender console=new ConsoleAppender(layout);
			log.addAppender(console);
			log.setLevel(Level.ALL);
		}
	
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		StateService stateService=new StateServiceImp();
		CustomerService customerService=new CustomerServiceImp();
		try {
			do {
			log.info("******Welcom To Hotel Reservation System*****");
			log.info("Login in Application\n1.Customer.\n2.Admin.\n3.Close System.");
			System.out.println("Enter Your Choice");
			int login=sc.nextInt();
			switch(login)
			{
			case 1:{
					do {
						
						log.info("1.Already have Customer Account\n2.Create New Account.\n3.Close");
						System.out.println("Enter Your Choice");
					int Ac=sc.nextInt();
					sc.nextLine();
					switch(Ac)
					{
					case 1:
					{
						
						System.out.println("Enter You email for login");
						sc.nextLine();
						String email=sc.nextLine();
						System.out.println("Enter Your Password");
						String pass=sc.nextLine();
					}
							break;
					case 2:
					{
					System.out.println("Enter Your Details.");
					System.out.println("Enter Your First name:");
					String firstName=sc.nextLine();
					System.out.println("Enter Your Last Name:");
					String lastName=sc.nextLine();
					System.out.println("Enter Your email:");
					String customerEmail=sc.nextLine();
					System.out.println("Enter Pan Number");
					String idProof=sc.nextLine();
					System.out.println("Enter Your Mobile Number");
					String customerPhoneNumber=sc.nextLine();
					System.out.println("Enter Your Address:");
					String CustomerAddress=sc.nextLine();
					System.out.println("Enter Your Birthdate: YYYY-MM-DD:");
					String birthdateInput =sc.nextLine();
					LocalDate birthdate = LocalDate.parse(birthdateInput);
					LocalDate currentDate = LocalDate.now();
					 Period age = Period.between(birthdate, currentDate);
					 
					 if (age.getYears() >= 18) {
						 System.out.println("Enter Your Pasword:");
						 String password =sc.nextLine();
						 CustomerMaster c=new CustomerMaster();
						 c.setFirstName(firstName);
						 c.setLastName(lastName);
						 c.setCustomerEmail(customerEmail);
						 c.setIdProof(idProof);
						 c.setCustomerPhoneNumber(customerPhoneNumber);
						 c.setCustomerAddress(CustomerAddress);
						 c.setDateOfBirth(birthdateInput);
						 c.setPassword(password);
						 System.out.println(customerService.isaddNewCustomer(c)?"****Customer Added Successfully Now You Can Login****":"Customer not Added.");
						 
						 System.out.println("*******************************************************************");   
					 } else {
				            System.out.println("You are under 18 years old.");
				            System.out.println("Thank You for Visting our hotel...");
				            System.out.println("*******************************************************************");
				        }
					}
						break;
					case 3:
						System.out.println("Thank You for visiting");
						System.exit(0);
						default:System.out.println("You enter wrong choice.");
					}
					}while(true);	
			}
			case 2:{
				System.out.println("Enter State");
				System.out.println(stateService.addState(new StateMaster(1,sc.nextLine()))?"State Add Successfully....":"State not Added.");
				}
				break;
			case 3:
				System.out.println("Thank You for visiting");
				System.exit(0);
			default:System.out.println("You Enetr Wrong Choice.");
			}
			}while(true);
		} catch (Exception e) {
			log.fatal(e);
		}
	}

}
