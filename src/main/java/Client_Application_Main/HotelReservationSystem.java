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
					sc.nextLine();
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
						 
				            System.out.println("You are 18 years or older.");
				        } else {
				            System.out.println("You are under 18 years old.");
				            System.out.println("Thank You for Visting our hotel...");
				            
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
