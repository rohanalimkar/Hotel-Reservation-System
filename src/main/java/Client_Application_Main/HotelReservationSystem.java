package Client_Application_Main;

import java.util.Scanner;

public class HotelReservationSystem {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try {
			do {
			System.out.println("******Welcom To Hotel Reservation System*****");
			System.out.println("Login in Application\n1.Customer.\n2.Admin.\n3.Close System.");
			System.out.println("Enter Your Choice");
			int login=sc.nextInt();
			switch(login)
			{
			case 1:{
					do {
					System.out.println("1.Already have Customer Account\n2.Create New Account.\n3.Close");
					System.out.println("Enter Your Choice");
					int Ac=sc.nextInt();
					switch(Ac)
					{
					case 1:
					{
						
					}
							break;
					case 2:
					{
						
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
			System.out.println(e);
		}
	}

}
