package Client_Application_Main;

import java.util.Scanner;

public class HotelReservationSystem {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try {
			do {
			System.out.println("******Welcom To Hotel Reservation System*****");
			System.out.println("1.Customer.\n2.Admin.\n3.Close System.");
			System.out.println("Enter Your Choice");
			int login=sc.nextInt();
			switch(login)
			{
			case 1:{
				
					}	
					break;
			case 2:{
				
				}
				break;
			case 3:
				System.exit(0);
			default:System.out.println("You Enetr Wrong Choice.");
			}
			}while(true);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
