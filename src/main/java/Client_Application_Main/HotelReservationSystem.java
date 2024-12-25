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
import Model.AmenitiesMaster;
import Model.CityMaster;
import Model.CustomerMaster;
import Model.DistrictMaster;
import Model.EmailMaster;
import Model.HotelMaster;
import Model.MainAdminMaster;
import Model.StateDistrictCityJoinMaster;
import Model.StateMaster;
import Services.AdminService;
import Services.AdminServiceImp;
import Services.AmenitiesService;
import Services.AmenitiesServiceImp;
import Services.CityService;
import Services.CityServiceImp;
import Services.CustomerService;
import Services.CustomerServiceImp;
import Services.DistrictService;
import Services.DistrictServiceImp;
import Services.HotelService;
import Services.HotelServiceImp;
import Services.MainAdminService;
import Services.MainAdminServiceImp;
import Services.SendMailService;
import Services.SendMailServiceImp;
import Services.StateDistrictCityJoinService;
import Services.StateDistrictCityJoinServiceImp;
import Services.StateService;
import Services.StateServiceImp;
import model.StateModel;

public class HotelReservationSystem {  //main class

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
		boolean exitFlag=false;
		StateService stateService=new StateServiceImp();
		AdminService adminService=new AdminServiceImp();
		CustomerService customerService=new CustomerServiceImp();
		DistrictService districtService=new DistrictServiceImp();
		CityService cityService=new CityServiceImp();
		StateDistrictCityJoinService sdcService=new StateDistrictCityJoinServiceImp();
		HotelService hotelService=new HotelServiceImp();
		MainAdminService mainAdminService=new MainAdminServiceImp();
		SendMailService mailService=new SendMailServiceImp();
		AmenitiesService amenitiesService=new AmenitiesServiceImp();
		
		StateMaster state=null;
		DistrictMaster district=null;
		CityMaster city=null;
		AdminMaster admin=null;
		StateDistrictCityJoinMaster sdc=null;
		HotelMaster hotel=null;
		MainAdminMaster mainAdmin=null;
		EmailMaster email=null;
		AmenitiesMaster amenities=null;
		
		try {
			do {
				flag=true;
			log.info("******Welcom To Hotel Reservation System*****");
			log.info("\nLogin in Application\n1.Customer.\n2.Admin.\n3.Exit from current menu\n");
			System.out.println("Enter Your Choice");
			int login=sc.nextInt();
			switch(login)
			{
			case 1:{
					//-----------------Customer Login---------------------------------
					do {
						
						log.info("\n1.Customer Login\n2.Create New Account.\n3.Exit from current menu\n");
						System.out.println("Enter Your Choice");
					int Ac=sc.nextInt();
					sc.nextLine();
					switch(Ac)
					{
					case 1:
					{
						//---------------------------Customer Login with Credential---------------------
						System.out.println("Enter You email for login");
						String customerEmail=sc.nextLine().trim();
						System.out.println("Enter Your Password");
						String password=sc.nextLine().trim();
						if (customerService.validateLogin(customerEmail, password)) {
							String customerName=customerService.getFirstName(customerEmail);
							System.out.println("_________________________________________________________________");
							System.out.println("\n\t\t"+customerName);
							System.out.println("_________________________________________________________________");
							System.out.println("Enter State Name:");
							String stateName=sc.nextLine().trim();
							System.out.println(stateName);
							int stateId=stateService.isStatePresent(stateName);
							System.out.println(stateId);
							if(stateId>0) {
								System.out.println("Enter District Name:");
								String districtName=sc.nextLine().trim();
								
								
								System.out.println("Do you want state wise hotel...");
								String choice=sc.nextLine();	
								 
								int districtId=districtService.isDistrictPresent(districtName);
								if(districtId>0)
								{
									String confirm="yes";
									while(choice==confirm){
										
										
									}
									System.out.println("Enter City Name:");
									String cityName=sc.nextLine().trim();
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
					String firstName=sc.nextLine().trim();
					System.out.println("Enter Your Last Name:");
					String lastName=sc.nextLine().trim();
					System.out.println("Enter Your email:");
					String customerEmail=sc.nextLine().trim();
					System.out.println("Enter Pan Number");
					String idProof=sc.nextLine().trim();
					System.out.println("Enter Your Mobile Number");
					String customerPhoneNumber=sc.nextLine().trim();
					System.out.println("Enter Your Address:");
					String CustomerAddress=sc.nextLine().trim();
					System.out.println("Enter Your Birthdate: YYYY-MM-DD:");
					String birthdateInput =sc.nextLine().trim();
					LocalDate birthdate = LocalDate.parse(birthdateInput);
					LocalDate currentDate = LocalDate.now();
					 Period age = Period.between(birthdate, currentDate);
					 
					 if (age.getYears() >= 18) {
						 System.out.println("Enter Your Pasword:");
						 String password =sc.nextLine().trim();
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
						System.out.println("\nThank You for visiting");
						exitFlag=true;
						break;
						
					default:System.out.println("\nYou enter wrong choice.");
					}
					}while(!exitFlag);	
					 exitFlag = false;			}
			break;
			case 2:
					{
				//-------------------------------Admin Login----------------------------------- 
				
				do {
					System.out.println("\n1.Sub Admin Login\n2.Main Admin\n3.Exit from current menu\n");
					System.out.println("Enter Your Choice:");
					int ch=sc.nextInt();
							sc.nextLine();
					switch(ch) {
					case 1:{
				//-------------------------------Sub Admin Login-----------------------------------
					
						
					 System.out.println("Enter Your email for login");
						String hotelEmail=sc.nextLine().trim();
						System.out.println("Enter Your Password");
						String password=sc.nextLine().trim();
						if (adminService.validateLogin(hotelEmail, password)) {
							System.out.println("\n_________________________________________________________________");
							System.out.println("\n\t\t"+hotelService.getHotelName(hotelEmail));
							System.out.println("_________________________________________________________________");
						
							do {
								System.out.println("\n1.Add amenities\n2.Delete amenities\n3.Update amenities Price.\n4.Exit from current menu.");
								System.out.println("Enter Your Choice:");
								int ch3=sc.nextInt();
								sc.nextLine();
								switch(ch3)
								{
								case 1://1.Add amenities
									break;
								case 2://2.Delete amenities
									break;
								case 3://Update amenities Price.
									break;
								case 4://------------------------------close the system-------------------------------
									System.out.println("\nThank You for visiting\n");
									exitFlag=true;
									break;
									//------------------------------close the system End-------------------------------
								default:System.out.println("\nYou enter wrong choice.");
									break;
									
								}
							}while(!exitFlag);	
							 exitFlag = false;  // Reset flag
							
						}else {
							System.out.println("Passwords do not match. Please try again.");
						}
					 
					}
					break;
					case 2:
					{	//-------------------------Main Admin-------------------------------------------------
						do {
							System.out.println("\n1.Main Admin Login.\n2.Create new Admin login.\n3.Exit from current menu\n");
							int ch1=sc.nextInt();
							sc.nextLine();
							switch(ch1) {
							case 1:{
								//--------------------------Login in Main Admin----------------------------------
									System.out.println("\nEnter You Email for Login:");
									String adminName=sc.nextLine().trim();
									System.out.println("\nEnter Your Password:");
									String password=sc.nextLine().trim();
									if(mainAdminService.validateLogin(adminName, password)) {
										
										do {
											System.out.println("\n1.Create sub Admin.\n2.Add amenities\n3.Delete amenities\n4.Update State\n5.Delete State\n6.Update District.\n7.Delete District.\n8.Update City.\n9.Delete City.\n10.Update Sub Admin.\n11.Exit from current menu.");
											System.out.println("\nEnter Your Choice:");
											int ch2=sc.nextInt();
													sc.nextLine();
											switch(ch2) {
											
											case 1:{
												//-------------------------------Create Sub Admin-----------------------------------
												System.out.println("\nFor Creating your admin account Provide Hotel details \n");
												System.out.println("Enter State Name:");
												String stateName=sc.nextLine().trim();
												int stateId=stateService.isStatePresent(stateName);
												if(stateId>0) {
													System.out.println("Enter District Name:");
													String districtName=sc.nextLine().trim();
													int districtId=districtService.isDistrictPresent(districtName);
													if(districtId>0)
													{
														System.out.println("Enter City Name:");
														String cityName=sc.nextLine().trim();
														int cityId=cityService.isCityPresent(cityName);
														if(cityId>0)
														{
															int sdcId=sdcService.validateStateDistrictCity(stateId, districtId, cityId);
															System.out.println(sdcId);
															if(sdcId>0)
															{
																System.out.println("Enter Your hotel name:");
																String hotelName=sc.nextLine().trim();
																int hotelId=hotelService.isHotelPresent(hotelName,stateName,districtName,cityName);
																if(hotelId>0)
																{
																System.out.println("Enter Admin Name:");
																String adminName1=sc.nextLine().trim();
//																System.out.println("Enter admin password:");
																String password1 = null;
//																System.out.println("Re-enter admin password:");
																String password2;
																boolean passwordsMatch = false;
																while(!passwordsMatch) {
																	
																	System.out.println("Enter admin password:");
																    password1 = sc.nextLine().trim();

																    System.out.println("Re-enter admin password:");
																    password2 = sc.nextLine().trim();
																    if (password1.equals(password2)) {
																        passwordsMatch = true; 
																        System.out.println("Enter Hotel Mail:");
																        String hotelEmail=sc.nextLine();
																		admin=new AdminMaster();
																		admin.setHotelId(hotelId);
																		admin.setPassword(password1);
																		admin.setUsername(adminName1);
																		admin.setHotelEmail(hotelEmail);
																		String from="rohanalimkar@gmail.com";
																		String subject="Your Hotel Account details";
																		String text="Dear "+admin.getUsername()+",\n\nYour admin account has been created with the following details:\n\nUsername: "+admin.getUsername()+"\nPassword:"+admin.getPassword()+"\nHotel Details:\nHotel Name:"+hotelName+"\nHotel Location:\nState: "+stateName+"\nDistrict: "+districtName+"\nCity: "+cityName+"\nBest regards,\r\n"
																				+ "RA InfoTech";
																		email=new EmailMaster();
																		email.setTo(hotelEmail);
																		email.setFrom(from);
																		email.setSubject(subject);
																		email.setText(text);
																		
																		if(mailService.sendEmail(email));
																		
																		System.out.println(adminService.isaddNewCustomer(admin)?"Sub Admin added successfully now you can Login Now You Can Login":"Sub admin not cretaed...");
																        
							
																    } else {
																        System.out.println("Passwords do not match. Please try again.");
																    }
																}	
																
																
																}else {
																	hotel=new HotelMaster();
																	hotel.setHotelName(hotelName);
																	hotel.setHotelLocation(sdcId);
																	System.out.println(hotelService.addNewHotel(hotel)?"Hotel Added Successfully":"Hotel Not added");
																}
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
															System.out.println(cityService.addCity(city)?"City added Successfully\n":"City not added Successfully\n");
															System.out.println("**************************************************");
														}
													}else {
														district=new DistrictMaster();
														district.setDistrictName(districtName);
														//write logic for add new District
														System.out.println(districtService.addDistrict(district)?"District added Successfully\n":"District not added Successfully\n");
														System.out.println("**************************************************");
													}
													
												}else {
													state=new StateMaster();
													state.setStateName(stateName);
													//if state not found then it state add by using below method
													System.out.println(stateService.addState(state)?"State added Successfully\n":"State not added successfully\n");
													System.out.println("**************************************************");
												}

												}
											break;
											//-------------------------------Create Sub Admin End-----------------------------------
											case 2:	
														System.out.println("\n Enter Your amenities Details:");
														System.out.println("Amenity Name:");
														String amenityName=sc.nextLine().trim();
														System.out.println("Amenity description:");
														String description=sc.nextLine().trim();
														amenities=new AmenitiesMaster();
														amenities.setAmenityName(amenityName);
														amenities.setDescription(description);
														System.out.println(amenitiesService.addAmenities(amenities)?"amenity added successfully":"amenity not added");
												break;
												case 3:	//3.Delete amenities
												break;
												case 4:	System.out.println("\n Enter Amenity Name for Update:");
														 amenityName=sc.nextLine().trim();
														int amenityId=amenitiesService.getAmenityId(amenityName);
														System.out.println("Enter Updated amenityName:");
														amenityName=sc.nextLine().trim();
														System.out.println("Enter Updated amenity description:");
														description=sc.nextLine().trim();
														System.out.println(amenitiesService.updateAmenities(amenityName, description, amenityId)?"Amenity Updated Successfully":"Amenity not Updated");
												break;
												case 5:	//5.Delete State
												break;
												case 6:	//6.Update District.
												break;
												case 7:	//7.Delete District.
												break;
												case 8:	//8.Update City.
												break;
												case 9:	//9.Delete City.
												break;
												case 10:	//10.Update Sub Admin.
												break;
												case 11:	//------------------------------close the system-------------------------------
													System.out.println("Thank You for visiting\n");
													exitFlag=true;
													break;
													//------------------------------close the system End-------------------------------
												default:System.out.println("You enter wrong choice.");


											}
								}while(!exitFlag);	
								 exitFlag = false;
								
										}else {
												System.out.println("\nYou Enter Wrong User Name or password.\n");
												System.out.println("**************************************************");
											}
											
									}
									break;
									
							case 2:
							{
								//---------------------------create Main Admin Start--------------------------
								System.out.println("Enter Email as a UserName:");
								String adminName=sc.nextLine().trim();
								System.out.println("Enter Password:");
								String password=sc.nextLine().trim();
								mainAdmin=new MainAdminMaster();
								mainAdmin.setAdminName(adminName);
								mainAdmin.setPassword(password);
								System.out.println(mainAdminService.addNewCustomer(mainAdmin)?"Admin Created Successfully\n":"Admin not created Successfully\n");
								//---------------------------create Main Admin End--------------------------
							}
							break;
							case 3:
								//------------------------------close the system start-------------------------------
								System.out.println("Thank You for visiting\n");
								exitFlag=true;
								break;
								//------------------------------close the system End-------------------------------
							default:System.out.println("You enter wrong choice.");
							}
						}while(!exitFlag);
						exitFlag = false;  
					}
					break;
					case 3:
						//------------------------------close the system-------------------------------
						System.out.println("Thank You for visiting\n");
						exitFlag=true;
						break;
						//------------------------------close the system End-------------------------------
					default:System.out.println("You enter wrong choice.");

					}
					
				}while(!exitFlag);
				exitFlag = false;  // Reset exitflag
                break;
				}
			case 3:
				//------------------------------close the system-------------------------------
				System.out.println("Thank You for visiting");
				flag=false;
				break;
				//------------------------------close the system End-------------------------------
			default:System.out.println("You Enetr Wrong Choice.");
			}
			}while(flag);
		} catch (Exception e) {
			log.fatal(e);
		}
	}

}
