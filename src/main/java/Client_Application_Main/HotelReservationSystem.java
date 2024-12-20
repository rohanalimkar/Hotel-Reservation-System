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

import Model.AdminMaster;
import Model.CityMaster;
import Model.CustomerMaster;
import Model.DistrictMaster;
import Model.HotelMaster;
import Model.StateDistrictCityJoinMaster;
import Model.StateMaster;
import Services.AdminService;
import Services.AdminServiceImp;
import Services.CityService;
import Services.CityServiceImp;
import Services.CustomerService;
import Services.CustomerServiceImp;
import Services.DistrictService;
import Services.DistrictServiceImp;
import Services.HotelService;
import Services.HotelServiceImp;
import Services.StateDistrictCityJoinService;
import Services.StateDistrictCityJoinServiceImp;
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
		boolean flag=true;
		StateService stateService=new StateServiceImp();
		AdminService adminService=new AdminServiceImp();
		CustomerService customerService=new CustomerServiceImp();
		DistrictService districtService=new DistrictServiceImp();
		CityService cityService=new CityServiceImp();
		StateDistrictCityJoinService sdcService=new StateDistrictCityJoinServiceImp();
		HotelService hotelService=new HotelServiceImp();
		
		StateMaster state=null;
		DistrictMaster district=null;
		CityMaster city=null;
		AdminMaster admin=null;
		StateDistrictCityJoinMaster sdc=null;
		HotelMaster hotel=null;
		try {
			do {
			log.info("******Welcom To Hotel Reservation System*****");
			log.info("\nLogin in Application\n1.Customer.\n2.Admin.\n3.Close System.");
			System.out.println("Enter Your Choice");
			int login=sc.nextInt();
			switch(login)
			{
			case 1:{
					//-----------------Customer Login---------------------------------
					do {
						
						log.info("\n1.Customer Login\n2.Create New Account.\n3.Close");
						System.out.println("Enter Your Choice");
					int Ac=sc.nextInt();
					sc.nextLine();
					switch(Ac)
					{
					case 1:
					{
						//---------------------------Customer Login with Credential---------------------
						System.out.println("Enter You email for login");
						String customerEmail=sc.nextLine();
						System.out.println("Enter Your Password");
						String password=sc.nextLine();
						if (customerService.validateLogin(customerEmail, password)) {
							String customerName=customerService.getFirstName(customerEmail);
							System.out.println("**********************************************************");
							System.out.println(customerName+"\n");
							System.out.println("**********************************************************");
							System.out.println("Enter State Name:");
							String stateName=sc.nextLine();
							System.out.println(stateName);
							int stateId=stateService.isStatePresent(stateName);
							System.out.println(stateId);
							if(stateId>0) {
								System.out.println("Enter District Name:");
								String districtName=sc.nextLine();
								int districtId=districtService.isDistrictPresent(districtName);
								if(districtId>0)
								{
									System.out.println("Enter City Name:");
									String cityName=sc.nextLine();
									int cityId=cityService.isCityPresent(cityName);
									if(cityId>0)
									{
										System.out.println("City Found");
									}else {
										System.out.println("City not Found");
									}
								}else {
									System.out.println("State Not Found");
								}
							}else {
								System.out.println("State Not Found");
							}
							
				        } else {
				            System.out.println("\nYou Enter Invalid email or password.\nPlease Login again\n");
				            System.out.println("**********************************************************");
				        }
					}
							break;
					case 2:
					{
						//--------------------------Create customer account------------------------------
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
						//------------------------------close the system-------------------------------
						System.out.println("Thank You for visiting");
						flag=false;
						break;
						
					default:System.out.println("You enter wrong choice.");
					}
					}while(flag!=false);	
			}
			break;
			case 2:
					{
				//-------------------------------Admin Login----------------------------------- 
				
				do {
					System.out.println("\n1.Admin Login\n2.Create New Admin\n");
					System.out.println("Enter Your Choice:");
					int ch=sc.nextInt();
							sc.nextLine();
					switch(ch) {
					case 1:{
				//-------------------------------Admin Login-----------------------------------
					
					 System.out.println("Enter You email for login");
						String username=sc.nextLine();
						System.out.println("Enter Your Password");
						String password=sc.nextLine();
						if (adminService.validateLogin(username, password)) {
							System.out.println("Welcome to Admine Pannel");
							System.out.println("**********************************************************");
							System.out.println(username+"\n");
							System.out.println("**********************************************************");
						}
					 
					}
					break;
					case 2:
					{
						//-------------------------------Create Admin-----------------------------------
						System.out.println("\nFor Creating your admin account Provide Hotel details \n");
						System.out.println("Enter State Name:");
						String stateName=sc.nextLine();
						int stateId=stateService.isStatePresent(stateName);
						if(stateId>0) {
							System.out.println("Enter District Name:");
							String districtName=sc.nextLine();
							int districtId=districtService.isDistrictPresent(districtName);
							if(districtId>0)
							{
								System.out.println("Enter City Name:");
								String cityName=sc.nextLine();
								int cityId=cityService.isCityPresent(cityName);
								if(cityId>0)
								{
									int sdcId=sdcService.validateStateDistrictCity(stateId, districtId, cityId);
									System.out.println(sdcId);
									if(sdcId>0)
									{
										System.out.println("Enter Your hotel name:");
										String hotelName=sc.nextLine();
										hotel=new HotelMaster();
										hotel.setHotelName(hotelName);
										hotel.setHotelLocation(sdcId);
										System.out.println(hotelService.addNewHotel(hotel)?"Hotel Added Successfully":"Hotel Not added");	
										//write logic for create admin
									}else{
										sdc=new StateDistrictCityJoinMaster();
										sdc.setStateId(stateId);
										sdc.setDistrictId(districtId);
										sdc.setCityId(cityId);
										System.out.println(sdcService.isaddNewStateDistrictCityJoin(sdc)?"Location added successfully":"Location not added successfully");
									}
								}else {
									city=new CityMaster();
									city.setCityName(cityName);
									//write logic for add new City
									System.out.println(cityService.addCity(city)?"City added Successfully":"City not added Successfully");
									
								}
							}else {
								district=new DistrictMaster();
								district.setDistrictName(districtName);
								//write logic for add new District
								System.out.println(districtService.addDistrict(district)?"District added Successfully":"District not added Successfully");
								
							}
							
						}else {
							state=new StateMaster();
							state.setStateName(stateName);
							//if state not found then it state add by using below method
							System.out.println(stateService.addState(state)?"State added Successfully":"State not added successfully");
							
						}
					}
					break;
					case 3:
						//------------------------------close the system-------------------------------
						System.out.println("Thank You for visiting");
						flag=false;
						break;
						
					default:System.out.println("You enter wrong choice.");

					}
					
				}while(true);

				}
			case 3:
				//------------------------------close the system-------------------------------
				System.out.println("Thank You for visiting");
				flag=false;
				break;
			default:System.out.println("You Enetr Wrong Choice.");
			}
			}while(flag!=false);
		} catch (Exception e) {
			log.fatal(e);
		}
	}

}
