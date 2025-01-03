package Client_Application_Main;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

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
import Model.HotelAmenitiesMaster;
import Model.HotelMaster;
import Model.MainAdminMaster;
import Model.ReservationMaster;
import Model.RoomMaster;
import Model.RoomTypeMaster;
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
import Services.HotelAmenitiesService;
import Services.HotelAmenitiesServiceImp;
import Services.HotelService;
import Services.HotelServiceImp;
import Services.MainAdminService;
import Services.MainAdminServiceImp;
import Services.ReservationService;
import Services.ReservationServiceImp;
import Services.RoomService;
import Services.RoomServiceImp;
import Services.RoomTypeService;
import Services.RoomTypeServiceImp;
import Services.SendMailService;
import Services.SendMailServiceImp;
import Services.StateDistrictCityJoinService;
import Services.StateDistrictCityJoinServiceImp;
import Services.StateService;
import Services.StateServiceImp;
import Controller.*;
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
		
		try (Scanner sc = new Scanner(System.in)) {
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
			HotelAmenitiesService hotelAmenityService=new HotelAmenitiesServiceImp();
			RoomTypeService roomTypeService=new RoomTypeServiceImp();
			RoomService roomService=new RoomServiceImp();
			ReservationService reservationService=new ReservationServiceImp();
			
			StateMaster state=null;
			DistrictMaster district=null;
			CityMaster city=null;
			AdminMaster admin=null;
			StateDistrictCityJoinMaster sdc=null;
			HotelMaster hotel=null;
			MainAdminMaster mainAdmin=null;
			EmailMaster email=null;
			AmenitiesMaster amenities=null;
			HotelAmenitiesMaster hotelAminity=null;
			RoomTypeMaster roomType=null;
			RoomMaster room=null;
			ReservationMaster reservation=null;
			
			int customerId=0;
			
			try {
				do {
					flag=true;
				log.info("🌸🌸🌸🌸🌸Welcom To Hotel Reservation System🌸🌸🌸🌸🌸"); 
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
								String str="yes";
								String customerName=customerService.getFirstName(customerEmail);
								System.out.println("-----------------------------------------------------------------");
								System.out.printf("%40s%n","Welcome "+customerName);
								System.out.println("-----------------------------------------------------------------");
								System.out.println("Enter State Name:");
								String stateName=sc.nextLine().trim();
								
								int stateId=stateService.isStatePresent(stateName);
								if(stateId>0) {								
									System.out.println("Do you want to see state wise hotel...");
									String choice=sc.nextLine();	
									if(choice.equals(str))  // if yes then show state wise list of hotels
									{
										Optional<List<HotelMaster>> o = stateService.StateWiseHotel(stateName);
										 if (o.isPresent() && !o.get().isEmpty()) {
									            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
									            System.out.printf("%-5s | %-30s | %-30s | %-30s | %-30s %n", "hotelId", "Hotel Name","State","District","City");
									            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
									            
									            AtomicInteger count = new AtomicInteger(1);
									            o.get().forEach(h -> {
									                System.out.printf("%-5d | %-30s | %-30s | %-30s | %-30s %n", h.getHotelId(), h.getHotelName(),h.getState(),h.getDistrictName(),h.getCityName());
									                count.incrementAndGet();
									            });
									            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
									            String reservationDate = LocalDate.now().toString();
												boolean correctDate=false;
												String checkInDate = null,checkOutDate = null;
												long daysBetween;
												LocalDate checkIn = null;
												LocalDate checkOut = null;
									            while(!correctDate) {	 
													 System.out.println("Enter checkIn-Date [yyyy-mm-dd]:");
											             checkInDate = sc.nextLine();
											            System.out.println("Enter checkOut-Date [yyyy-mm-dd]:");
											            checkOutDate = sc.nextLine();
											            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
											             checkIn = LocalDate.parse(checkInDate, formatter);
											            checkOut = LocalDate.parse(checkOutDate, formatter);
													 if (reservationDate.compareTo(checkInDate) <= 0 && checkInDate.compareTo(checkOutDate) <= 0)
											                correctDate = true;
													 
											            else
											                System.out.println("Invalid dates. Please ensure you enter correct check-in  and check-out date.");
											        }
									            daysBetween= ChronoUnit.DAYS.between(checkIn, checkOut);
									            System.out.println("Your total days of stay is:"+daysBetween);
									            System.out.println("Enter number Of Guests:");
									            int numberOfGuests=sc.nextInt();
									            sc.nextLine();
									            System.out.println("Enter Hotel Id for booking");
									            int hotelId=sc.nextInt();
									            	sc.nextLine();
									            	int bookRoom=(int) Math.ceil((double) numberOfGuests / 4);
											            if(bookRoom<=roomService.getAvailableRoomCount(hotelId))
											            {
											            if(numberOfGuests>4)
											            {
											            	System.out.println("You Have to Book"+bookRoom);
											            	int count1=0;
												            while(count1<bookRoom)
												            {
													            Optional<List<RoomMaster>> o1=roomService.getAllAvailableRoom(hotelId);									
																 if (o1.isPresent() && !o1.get().isEmpty()) {
																	 System.out.println("---------------------------------------------------------------------------------------------------------------------------");
															            System.out.printf("%-20s | %-30s | %-10s |%n","Room Number","Room Type","Room Price");
															            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
															            
															            o1.get().forEach(h -> {
															                System.out.printf("%-20s | %-30s | %-10.2f |%n",h.getRoomNumber(),h.getRoomType(),h.getRoomPrice());
															            });
															            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
															            customerId=customerService.getCustomerId(customerEmail);
																		 System.out.println("Enter Room Id:");
																		 String roomNumber=sc.nextLine();
																		 int roomId=roomService.getRoomId(hotelId, roomNumber);
															            System.out.println("Available Amenities for Hotel " + hotelId + ":");
															            
															            Optional<List<HotelAmenitiesMaster>> o2 = hotelAmenityService.getAllAmenities(hotelId);

															            if (o2.isPresent() && !o2.get().isEmpty()) {
															                System.out.println("---------------------------------------------------");
															                System.out.printf("%-10s | %-30s | %-10s%n", "Amenity ID", "Amenity Name", "Price");
															                System.out.println("---------------------------------------------------");

															                o2.get().forEach(a -> {
															                    System.out.printf("%-10d | %-30s | %-10.2f%n", a.getAmenityId(), a.getAmenityName(), a.getPrice());
															                });

															                System.out.println("---------------------------------------------------");
															            } else {
															                System.out.println("No amenities found for this hotel.");
															            }
															            float amenitiesCost = 0.0f;
															            boolean chooseAmenity=false;
															            while(!chooseAmenity) {
															            	System.out.println("do you want to add amenity:[Yes/No]");
															            	String amenityChoice=sc.nextLine().toUpperCase();
															            	if(amenityChoice.equals("NO"))
															            	{
															            		chooseAmenity=true;
															            	}else {
															            		System.out.println("Enter Amenity Id:");
															            		int amenityId=sc.nextInt();
															            		sc.nextLine();
															            		amenitiesCost+=hotelAmenityService.getHotelAmenityPrice(hotelId, amenityId);
															            	}
															            }
															            System.out.println("************** HOTEL BILL **************");
															            System.out.println("Hotel ID: " + hotelId);
															            System.out.println("Room Number: " + roomNumber);
															            System.out.println("----------------------------------------");
															            System.out.println("Amenity Cost (per day)   : " + amenitiesCost);
															            float roomPrice=roomService.getRoomPrice(hotelId, roomNumber);
															            System.out.println("Room Price (per day)     : " +roomPrice);
															            System.out.println("----------------------------------------");
															            System.out.println("Days of Stay             : " + daysBetween);
															            System.out.println("----------------------------------------");
															            float totalBill = (amenitiesCost * daysBetween) + (roomPrice * daysBetween);
															            System.out.println("Total Bill               : " + totalBill);
															            System.out.println("----------------------------------------");
															            System.out.print("Advance Payment          : ");
															            float advancePayment = sc.nextFloat();
															            sc.nextLine();
															            System.out.println("----------------------------------------");
															            float remainingAmount = totalBill - advancePayment;
															            System.out.println("Remaining Amount to Pay  : " + remainingAmount);
															            System.out.println("----------------------------------------");
															            System.out.println("************ THANK YOU FOR STAYING ************");

															            String Bookinstatus=null;
															            reservation=new ReservationMaster();
															            reservation.setAdvanceAmount(advancePayment);
															            reservation.setCheckInDate(checkInDate);
															            reservation.setCheckOutDate(checkOutDate);
															            reservation.setCustomerId(customerId);
															            reservation.setHotelId(hotelId);
															            reservation.setNumberOfGuests(numberOfGuests);
															            reservation.setReservationDate(reservationDate);
															            reservation.setRoomId(roomId);
															            reservation.setTotalAmount(totalBill);
															            System.out.println("Confirm Your Booking");
															            String confirm=sc.nextLine().toLowerCase();
															            if(confirm.equals("yes"))
															            {
															             Bookinstatus="Confirmed";
															             String otp = HotelReservationSystem.generateOTP();
															             reservation.setRemainingAmount(remainingAmount);
															            reservation.setStatus(Bookinstatus);
															            String from="rohanalimkar@gmail.com";
															            String to=customerEmail;
															            String subject = "Your OTP for Hotel Reservation System";
															            String text = "Dear " + customerName + ",\n\n" +
															                      "You requested an OTP to verify your account in the Hotel Reservation System. Here is your One-Time Password:\n\n" +
															                      "OTP: " + otp + "\n\n" +
															                      "Please enter this OTP on the verification page. This OTP is valid for the next 5 minutes.\n\n" +
															                      "If you did not request this OTP or if you have any questions, please contact us at support@"+hotelService.getHotelName(hotelId)+".com.\n\n" +
															                      "Best regards,\n" +
															                      "RA InfoTech";
															            email=new EmailMaster();
																		email.setTo(customerEmail);
																		email.setFrom(from);
																		email.setSubject(subject);
																		email.setText(text);
																		
																		if (mailService != null && email != null) {
																            boolean isSent = mailService.sendEmail(email);  // Assume sendEmail() returns true if email is sent
																            if (isSent) {
																                System.out.println("\nOTP sent successfully to " + customerEmail);
																                System.out.println("\n Enter Your OTP");
																                String custOTP=sc.nextLine();
																                if(otp.equals(custOTP)){
																                	 from = "rohanalimkar@gmail.com";
																                	 to = customerEmail;
																                	 subject = "Hotel Reservation Details";
																                	 text = "Dear " + customerName + ",\n\n" +
																                	              "We happy to inform you that your reservation at " + hotelService.getHotelName(hotelId) + " has been Confirm .\n\n" +
																                	              "Reservation Details:\n" +
																                	              "Hotel Name: " + hotelService.getHotelName(hotelId) + "\n" +
																                	              "Room Number: " + roomNumber + "\n" +
																                	              "Check-In Date: " + checkInDate + "\n" +
																                	              "Check-Out Date: " + checkOutDate + "\n" +
																                	              "Total Amount: " + totalBill + "\n\n" +
																                	              "If you have any further questions or need assistance, feel free to reach out to us at support@"+hotelService.getHotelName(hotelId)+".com.\n\n" 
																                	              +"Best regards,\n" +
																                	              "RA InfoTech";
																                	              
																                	email = new EmailMaster();
																                	email.setTo(customerEmail);
																                	email.setFrom(from);
																                	email.setSubject(subject);
																                	email.setText(text);

																                	if (mailService != null && email != null) {
																                	     isSent = mailService.sendEmail(email);
																                	    if (isSent) {
																                	        System.out.println("Room Booking  email sent successfully to " + customerEmail);
																                	    } else {
																                	        System.out.println("Failed to send Room booking  email.");
																                	    }
																                	} else {
																                	    System.out.println("Mail service or email object is not properly initialized.");
																                	}
																                	System.out.println(reservationService.isConfirmedBoking(reservation)?"Reservation Confirmed":"Issue for Reservation");
																		            String roomstatus="Booked";
																		            roomService.isRoomStatusUpdate(hotelId,roomNumber,roomstatus);
																		            
																                }else {
																                	System.out.println("You Enter Wrong OTP");
																                }
																            } else {
																                System.out.println("Failed to send OTP email.");
																            }
																        } else {
																            System.out.println("Mail service or email object is not properly initialized.");
																        }
															            
															            }else {
															            	System.out.println("*******************************************************************");
															            	System.out.println("\n🙏🙏 Thank You for visiting 🙏🙏");
															            }
																 } else {
															            System.out.println("There is no Data Found..   Sorry for inconvenience...🙏🙏\n");
															        }
																 count1++;
												            }
											            }else {
												            Optional<List<RoomMaster>> o1=roomService.getAllAvailableRoom(hotelId);									
															 if (o1.isPresent() && !o1.get().isEmpty()) {
														            System.out.println("-------------------------------------------------------------------");
														            System.out.printf("%-20s | %-30s | %-10s |%n","Room Number","Room Type","Room Price");
														            System.out.println("-------------------------------------------------------------------");
														            
														            o1.get().forEach(h -> {
														                System.out.printf("%-20s | %-30s | %-10.2f |%n",h.getRoomNumber(),h.getRoomType(),h.getRoomPrice());
														            });
														            System.out.println("-------------------------------------------------------------------");
														            customerId=customerService.getCustomerId(customerEmail);
																	 System.out.println("Enter Room Id:");
																	 String roomNumber=sc.nextLine();
																	 int roomId=roomService.getRoomId(hotelId, roomNumber);
														            System.out.println("Available Amenities for Hotel " + hotelId + ":");
														            
														            Optional<List<HotelAmenitiesMaster>> o2 = hotelAmenityService.getAllAmenities(hotelId);

														            if (o2.isPresent() && !o2.get().isEmpty()) {
														                System.out.println("---------------------------------------------------");
														                System.out.printf("%-10s | %-30s | %-10s%n", "Amenity ID", "Amenity Name", "Price");
														                System.out.println("---------------------------------------------------");

														                o2.get().forEach(a -> {
														                    System.out.printf("%-10d | %-30s | %-10.2f%n", a.getAmenityId(), a.getAmenityName(), a.getPrice());
														                });

														                System.out.println("---------------------------------------------------");
														            } else {
														                System.out.println("No amenities found for this hotel.");
														            }
														            float amenitiesCost = 0.0f;
														            boolean chooseAmenity=false;
														            while(!chooseAmenity) {
														            	System.out.println("do you want to add amenity:[Yes/No]");
														            	String amenityChoice=sc.nextLine().toUpperCase();
														            	if(amenityChoice.equals("NO"))
														            	{
														            		chooseAmenity=true;
														            	}else {
														            		System.out.println("Enter Amenity Id:");
														            		int amenityId=sc.nextInt();
														            		sc.nextLine();
														            		amenitiesCost+=hotelAmenityService.getHotelAmenityPrice(hotelId, amenityId);
														            	}
														            }
														            System.out.println("************** HOTEL BILL **************");
														            System.out.println("Hotel ID: " + hotelId);
														            System.out.println("Room Number: " + roomNumber);
														            System.out.println("----------------------------------------");
														            System.out.println("Amenity Cost (per day)   : " + amenitiesCost);
														            float roomPrice=roomService.getRoomPrice(hotelId, roomNumber);
														            System.out.println("Room Price (per day)     : " +roomPrice);
														            System.out.println("----------------------------------------");
														            System.out.println("Days of Stay             : " + daysBetween);
														            System.out.println("----------------------------------------");
														            float totalBill = (amenitiesCost * daysBetween) + (roomPrice * daysBetween);
														            System.out.println("Total Bill               : " + totalBill);
														            System.out.println("----------------------------------------");
														            System.out.print("Advance Payment          : ");
														            float advancePayment = sc.nextFloat();
														            sc.nextLine();
														            System.out.println("----------------------------------------");
														            float remainingAmount = totalBill - advancePayment;
														            System.out.println("Remaining Amount to Pay  : " + remainingAmount);
														            System.out.println("----------------------------------------");
														            System.out.println("************ THANK YOU FOR STAYING ************");

														            String Bookinstatus=null;
														            reservation=new ReservationMaster();
														            reservation.setAdvanceAmount(advancePayment);
														            reservation.setCheckInDate(checkInDate);
														            reservation.setCheckOutDate(checkOutDate);
														            reservation.setCustomerId(customerId);
														            reservation.setHotelId(hotelId);
														            reservation.setNumberOfGuests(numberOfGuests);
														            reservation.setReservationDate(reservationDate);
														            reservation.setRoomId(roomId);
														            reservation.setTotalAmount(totalBill);
														            System.out.println("Confirm Your Booking");
														            String confirm=sc.nextLine().toLowerCase();
														            if(confirm.equals("yes"))
														            {
														             Bookinstatus="Confirmed";
														             String otp = HotelReservationSystem.generateOTP();
														             reservation.setRemainingAmount(remainingAmount);
														            reservation.setStatus(Bookinstatus);
														            String from="rohanalimkar@gmail.com";
														            String to=customerEmail;
														            String subject = "Your OTP for Hotel Reservation System";
														            String text = "Dear " + customerName + ",\n\n" +
														                      "You requested an OTP to verify your account in the Hotel Reservation System. Here is your One-Time Password:\n\n" +
														                      "OTP: " + otp + "\n\n" +
														                      "Please enter this OTP on the verification page. This OTP is valid for the next 5 minutes.\n\n" +
														                      "If you did not request this OTP or if you have any questions, please contact us at support@"+hotelService.getHotelName(hotelId)+".com.\n\n" +
														                      "Best regards,\n" +
														                      "RA InfoTech";
														            email=new EmailMaster();
																	email.setTo(customerEmail);
																	email.setFrom(from);
																	email.setSubject(subject);
																	email.setText(text);
																	
																	if (mailService != null && email != null) {
															            boolean isSent = mailService.sendEmail(email);  // Assume sendEmail() returns true if email is sent
															            if (isSent) {
															                System.out.println("\nOTP sent successfully to " + customerEmail);
															                System.out.println("\n Enter Your OTP");
															                String custOTP=sc.nextLine();
															                if(otp.equals(custOTP)){
															                	 from = "rohanalimkar@gmail.com";
															                	 to = customerEmail;
															                	 subject = "Hotel Reservation Details";
															                	 text = "Dear " + customerName + ",\n\n" +
															                			 "We happy to inform you that your reservation at " + hotelService.getHotelName(hotelId) + " has been Confirm .\n\n" +
														                	              "Reservation Details:\n" +
														                	              "Hotel Name: " + hotelService.getHotelName(hotelId) + "\n" +
														                	              "Room Number: " + roomNumber + "\n" +
														                	              "Check-In Date: " + checkInDate + "\n" +
														                	              "Check-Out Date: " + checkOutDate + "\n" +
														                	              "Total Amount: " + totalBill + "\n\n" +
														                	              "If you have any further questions or need assistance, feel free to reach out to us at support@"+hotelService.getHotelName(hotelId)+".com.\n\n" 
														                	              +"Best regards,\n" +
														                	              "RA InfoTech";
															                	              
															                	email = new EmailMaster();
															                	email.setTo(customerEmail);
															                	email.setFrom(from);
															                	email.setSubject(subject);
															                	email.setText(text);

															                	if (mailService != null && email != null) {
															                	     isSent = mailService.sendEmail(email);
															                	    if (isSent) {
															                	        System.out.println("Room Booking  email sent successfully to " + customerEmail);
															                	    } else {
															                	        System.out.println("Failed to send Room booking email.");
															                	    }
															                	} else {
															                	    System.out.println("Mail service or email object is not properly initialized.");
															                	}
															                	System.out.println(reservationService.isConfirmedBoking(reservation)?"Reservation Confirmed":"Issue for Reservation");
																	            String roomstatus="Booked";
																	            roomService.isRoomStatusUpdate(hotelId,roomNumber,roomstatus);
																	            
															                }else {
															                	System.out.println("You Enter Wrong OTP");
															                }
															            } else {
															                System.out.println("Failed to send OTP email.");
															            }
															        } else {
															            System.out.println("Mail service or email object is not properly initialized.");
															        }
														            
														            }else {
														            	System.out.println("*******************************************************************");
														            	System.out.println("\n🙏🙏 Thank You for visiting 🙏🙏");
														            }
															 } else {
														            System.out.println("There is no Data Found..   Sorry for inconvenience...🙏🙏\n");
														        }
											            }
											            }else{
											            	System.out.println(bookRoom+" Rooms not available");
											            }
											   
										 } else {
									            System.out.println("There is no Data Found..   Sorry for inconvenience...🙏🙏\n");
									        }

									}else {
										
										System.out.println("Enter District Name:");
										String districtName=sc.nextLine().trim();
									int districtId=districtService.isDistrictPresent(districtName);
									System.out.println("Do you want District wise hotel...");
									 choice=sc.nextLine();	
									if(choice.equals(str)) // if yes then show district wise list of hotels
									{
										Optional<List<HotelMaster>> o=districtService.DistrictWiseHotel(stateName,districtName);
										if (o.isPresent() && !o.get().isEmpty()) {
								            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
								            System.out.printf("%-5s | %-30s | %-30s | %-30s | %-30s %n", "hotelId", "Hotel Name","State","District","City");
								            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
								            
								            AtomicInteger count = new AtomicInteger(1);
								            o.get().forEach(h -> {
								                System.out.printf("%-5d | %-30s | %-30s | %-30s | %-30s %n", h.getHotelId(), h.getHotelName(),h.getState(),h.getDistrictName(),h.getCityName());
								                count.incrementAndGet();
								            });
								            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
								            String reservationDate = LocalDate.now().toString();
												boolean correctDate=false;
												String checkInDate = null,checkOutDate = null;
												long daysBetween;
												LocalDate checkIn = null;
												LocalDate checkOut = null;
									            while(!correctDate) {	 
													 System.out.println("Enter checkIn-Date [yyyy-mm-dd]:");
											             checkInDate = sc.nextLine();
											            System.out.println("Enter checkOut-Date [yyyy-mm-dd]:");
											            checkOutDate = sc.nextLine();
											            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
											             checkIn = LocalDate.parse(checkInDate, formatter);
											            checkOut = LocalDate.parse(checkOutDate, formatter);
													 if (reservationDate.compareTo(checkInDate) <= 0 && checkInDate.compareTo(checkOutDate) <= 0)
											                correctDate = true;
													 
											            else
											                System.out.println("Invalid dates. Please ensure you enter correct check-in  and check-out date.");
											        }
									            daysBetween= ChronoUnit.DAYS.between(checkIn, checkOut);
									            System.out.println("Your total days of stay is:"+daysBetween);
									            System.out.println("Enter number Of Guests:");
									            int numberOfGuests=sc.nextInt();
									            sc.nextLine();
									            System.out.println("Enter Hotel Id for booking");
									            int hotelId=sc.nextInt();
									            	sc.nextLine();
									            	int bookRoom=(int) Math.ceil((double) numberOfGuests / 4);
									            	System.out.println("Available room count:"+bookRoom);
											            if(bookRoom<=roomService.getAvailableRoomCount(hotelId))
											            {
											            if(numberOfGuests>4)
											            {
											            	System.out.println("You Have to Book"+bookRoom);
											            	int count1=0;
												            while(count1<bookRoom)
												            {
													            Optional<List<RoomMaster>> o1=roomService.getAllAvailableRoom(hotelId);									
																 if (o1.isPresent() && !o1.get().isEmpty()) {
															            System.out.println("-------------------------------------------------------------------");
															            System.out.printf("%-20s | %-30s | %-10s |%n","Room Number","Room Type","Room Price");
															            System.out.println("-------------------------------------------------------------------");
															            
															            o1.get().forEach(h -> {
															                System.out.printf("%-20s | %-30s | %-10.2f |%n",h.getRoomNumber(),h.getRoomType(),h.getRoomPrice());
															            });
															            System.out.println("-------------------------------------------------------------------");
															            customerId=customerService.getCustomerId(customerEmail);
																		 System.out.println("Enter Room Id:");
																		 String roomNumber=sc.nextLine();
																		 int roomId=roomService.getRoomId(hotelId, roomNumber);
															            System.out.println("Available Amenities for Hotel " + hotelId + ":");
															            
															            Optional<List<HotelAmenitiesMaster>> o2 = hotelAmenityService.getAllAmenities(hotelId);

															            if (o2.isPresent() && !o2.get().isEmpty()) {
															                System.out.println("---------------------------------------------------");
															                System.out.printf("%-10s | %-30s | %-10s%n", "Amenity ID", "Amenity Name", "Price");
															                System.out.println("---------------------------------------------------");

															                o2.get().forEach(a -> {
															                    System.out.printf("%-10d | %-30s | %-10.2f%n", a.getAmenityId(), a.getAmenityName(), a.getPrice());
															                });

															                System.out.println("---------------------------------------------------");
															            } else {
															                System.out.println("No amenities found for this hotel.");
															            }
															            float amenitiesCost = 0.0f;
															            boolean chooseAmenity=false;
															            while(!chooseAmenity) {
															            	System.out.println("do you want to add amenity:[Yes/No]");
															            	String amenityChoice=sc.nextLine().toUpperCase();
															            	if(amenityChoice.equals("NO"))
															            	{
															            		chooseAmenity=true;
															            	}else {
															            		System.out.println("Enter Amenity Id:");
															            		int amenityId=sc.nextInt();
															            		sc.nextLine();
															            		amenitiesCost+=hotelAmenityService.getHotelAmenityPrice(hotelId, amenityId);
															            	}
															            }
															            System.out.println("************** HOTEL BILL **************");
															            System.out.println("Hotel ID: " + hotelId);
															            System.out.println("Room Number: " + roomNumber);
															            System.out.println("----------------------------------------");
															            System.out.println("Amenity Cost (per day)   : " + amenitiesCost);
															            float roomPrice=roomService.getRoomPrice(hotelId, roomNumber);
															            System.out.println("Room Price (per day)     : " +roomPrice);
															            System.out.println("----------------------------------------");
															            System.out.println("Days of Stay             : " + daysBetween);
															            System.out.println("----------------------------------------");
															            float totalBill = (amenitiesCost * daysBetween) + (roomPrice * daysBetween);
															            System.out.println("Total Bill               : " + totalBill);
															            System.out.println("----------------------------------------");
															            System.out.print("Advance Payment          : ");
															            float advancePayment = sc.nextFloat();
															            sc.nextLine();
															            System.out.println("----------------------------------------");
															            float remainingAmount = totalBill - advancePayment;
															            System.out.println("Remaining Amount to Pay  : " + remainingAmount);
															            System.out.println("----------------------------------------");
															            System.out.println("************ THANK YOU FOR STAYING ************");

															            String Bookinstatus=null;
															            reservation=new ReservationMaster();
															            reservation.setAdvanceAmount(advancePayment);
															            reservation.setCheckInDate(checkInDate);
															            reservation.setCheckOutDate(checkOutDate);
															            reservation.setCustomerId(customerId);
															            reservation.setHotelId(hotelId);
															            reservation.setNumberOfGuests(numberOfGuests);
															            reservation.setReservationDate(reservationDate);
															            reservation.setRoomId(roomId);
															            reservation.setTotalAmount(totalBill);
															            System.out.println("Confirm Your Booking");
															            String confirm=sc.nextLine().toLowerCase();
															            if(confirm.equals("yes"))
															            {
															             Bookinstatus="Confirmed";
															             String otp = HotelReservationSystem.generateOTP();
															             reservation.setRemainingAmount(remainingAmount);
															            reservation.setStatus(Bookinstatus);
															            String from="rohanalimkar@gmail.com";
															            String to=customerEmail;
															            String subject = "Your OTP for Hotel Reservation System";
															            String text = "Dear " + customerName + ",\n\n" +
															                      "You requested an OTP to verify your account in the Hotel Reservation System. Here is your One-Time Password:\n\n" +
															                      "OTP: " + otp + "\n\n" +
															                      "Please enter this OTP on the verification page. This OTP is valid for the next 5 minutes.\n\n" +
															                      "If you did not request this OTP or if you have any questions, please contact us at support@"+hotelService.getHotelName(hotelId)+".com.\n\n" +
															                      "Best regards,\n" +
															                      "RA InfoTech";
															            email=new EmailMaster();
																		email.setTo(customerEmail);
																		email.setFrom(from);
																		email.setSubject(subject);
																		email.setText(text);
																		
																		if (mailService != null && email != null) {
																            boolean isSent = mailService.sendEmail(email);  // Assume sendEmail() returns true if email is sent
																            if (isSent) {
																                System.out.println("\nOTP sent successfully to " + customerEmail);
																                System.out.println("\n Enter Your OTP");
																                String custOTP=sc.nextLine();
																                if(otp.equals(custOTP)){
																                	 from = "rohanalimkar@gmail.com";
																                	 to = customerEmail;
																                	 subject = "Hotel Reservation Details";
																                	 text = "Dear " + customerName + ",\n\n" +
																                			 "We happy to inform you that your reservation at " + hotelService.getHotelName(hotelId) + " has been Confirm .\n\n" +
															                	              "Reservation Details:\n" +
															                	              "Hotel Name: " + hotelService.getHotelName(hotelId) + "\n" +
															                	              "Room Number: " + roomNumber + "\n" +
															                	              "Check-In Date: " + checkInDate + "\n" +
															                	              "Check-Out Date: " + checkOutDate + "\n" +
															                	              "Total Amount: " + totalBill + "\n\n" +
															                	              "If you have any further questions or need assistance, feel free to reach out to us at support@"+hotelService.getHotelName(hotelId)+".com.\n\n" 
															                	              +"Best regards,\n" +
															                	              "RA InfoTech";
																                	              
																                	email = new EmailMaster();
																                	email.setTo(customerEmail);
																                	email.setFrom(from);
																                	email.setSubject(subject);
																                	email.setText(text);

																                	if (mailService != null && email != null) {
																                	     isSent = mailService.sendEmail(email);
																                	    if (isSent) {
																                	        System.out.println("Booking email sent successfully to " + customerEmail);
																                	    } else {
																                	        System.out.println("Failed to send booking email.");
																                	    }
																                	} else {
																                	    System.out.println("Mail service or email object is not properly initialized.");
																                	}
																                	System.out.println(reservationService.isConfirmedBoking(reservation)?"Reservation Confirmed":"Issue for Reservation");
																		            String roomstatus="Booked";
																		            roomService.isRoomStatusUpdate(hotelId,roomNumber,roomstatus);
																		            
																                }else {
																                	System.out.println("You Enter Wrong OTP");
																                }
																            } else {
																                System.out.println("Failed to send OTP email.");
																            }
																        } else {
																            System.out.println("Mail service or email object is not properly initialized.");
																        }
															            
															            }else {
															            	System.out.println("*******************************************************************");
															            	System.out.println("\n🙏🙏 Thank You for visiting 🙏🙏");
															            }
																 } else {
															            System.out.println("There is no Data Found..   Sorry for inconvenience...🙏🙏\n");
															        }
																 count1++;
												            }
											            }else {			            	
											            	Optional<List<RoomMaster>> o1=roomService.getAllAvailableRoom(hotelId);									
															 if (o1.isPresent() && !o1.get().isEmpty()) {
														            System.out.println("-------------------------------------------------------------------");
														            System.out.printf("%-20s | %-30s | %-10s |%n","Room Number","Room Type","Room Price");
														            System.out.println("-------------------------------------------------------------------");
														            
														            o1.get().forEach(h -> {
														                System.out.printf("%-20s | %-30s | %-10.2f |%n",h.getRoomNumber(),h.getRoomType(),h.getRoomPrice());
														            });
														            System.out.println("-------------------------------------------------------------------");
														            customerId=customerService.getCustomerId(customerEmail);
																	 System.out.println("Enter Room Id:");
																	 String roomNumber=sc.nextLine();
																	 int roomId=roomService.getRoomId(hotelId, roomNumber);
														            System.out.println("Available Amenities for Hotel " + hotelId + ":");
														            
														            Optional<List<HotelAmenitiesMaster>> o2 = hotelAmenityService.getAllAmenities(hotelId);

														            if (o2.isPresent() && !o2.get().isEmpty()) {
														                System.out.println("---------------------------------------------------");
														                System.out.printf("%-10s | %-30s | %-10s%n", "Amenity ID", "Amenity Name", "Price");
														                System.out.println("---------------------------------------------------");

														                o2.get().forEach(a -> {
														                    System.out.printf("%-10d | %-30s | %-10.2f%n", a.getAmenityId(), a.getAmenityName(), a.getPrice());
														                });

														                System.out.println("---------------------------------------------------");
														            } else {
														                System.out.println("No amenities found for this hotel.");
														            }
														            float amenitiesCost = 0.0f;
														            boolean chooseAmenity=false;
														            while(!chooseAmenity) {
														            	System.out.println("do you want to add amenity:[Yes/No]");
														            	String amenityChoice=sc.nextLine().toUpperCase();
														            	if(amenityChoice.equals("NO"))
														            	{
														            		chooseAmenity=true;
														            	}else {
														            		System.out.println("Enter Amenity Id:");
														            		int amenityId=sc.nextInt();
														            		sc.nextLine();
														            		amenitiesCost+=hotelAmenityService.getHotelAmenityPrice(hotelId, amenityId);
														            	}
														            }
														            System.out.println("************** HOTEL BILL **************");
														            System.out.println("Hotel ID: " + hotelId);
														            System.out.println("Room Number: " + roomNumber);
														            System.out.println("----------------------------------------");
														            System.out.println("Amenity Cost (per day)   : " + amenitiesCost);
														            float roomPrice=roomService.getRoomPrice(hotelId, roomNumber);
														            System.out.println("Room Price (per day)     : " +roomPrice);
														            System.out.println("----------------------------------------");
														            System.out.println("Days of Stay             : " + daysBetween);
														            System.out.println("----------------------------------------");
														            float totalBill = (amenitiesCost * daysBetween) + (roomPrice * daysBetween);
														            System.out.println("Total Bill               : " + totalBill);
														            System.out.println("----------------------------------------");
														            System.out.print("Advance Payment          : ");
														            float advancePayment = sc.nextFloat();
														            sc.nextLine();
														            System.out.println("----------------------------------------");
														            float remainingAmount = totalBill - advancePayment;
														            System.out.println("Remaining Amount to Pay  : " + remainingAmount);
														            System.out.println("----------------------------------------");
														            System.out.println("************ THANK YOU FOR STAYING ************");

														            String Bookinstatus=null;
														            reservation=new ReservationMaster();
														            reservation.setAdvanceAmount(advancePayment);
														            reservation.setCheckInDate(checkInDate);
														            reservation.setCheckOutDate(checkOutDate);
														            reservation.setCustomerId(customerId);
														            reservation.setHotelId(hotelId);
														            reservation.setNumberOfGuests(numberOfGuests);
														            reservation.setReservationDate(reservationDate);
														            reservation.setRoomId(roomId);
														            reservation.setTotalAmount(totalBill);
														            System.out.println("Confirm Your Booking");
														            String confirm=sc.nextLine().toLowerCase();
														            if(confirm.equals("yes"))
														            {
														             Bookinstatus="Confirmed";
														             String otp = HotelReservationSystem.generateOTP();
														             reservation.setRemainingAmount(remainingAmount);
														            reservation.setStatus(Bookinstatus);
														            String from="rohanalimkar@gmail.com";
														            String to=customerEmail;
														            String subject = "Your OTP for Hotel Reservation System";
														            String text = "Dear " + customerName + ",\n\n" +
														                      "You requested an OTP to verify your account in the Hotel Reservation System. Here is your One-Time Password:\n\n" +
														                      "OTP: " + otp + "\n\n" +
														                      "Please enter this OTP on the verification page. This OTP is valid for the next 5 minutes.\n\n" +
														                      "If you did not request this OTP or if you have any questions, please contact us at support@"+hotelService.getHotelName(hotelId)+".com.\n\n" +
														                      "Best regards,\n" +
														                      "RA InfoTech";
														            email=new EmailMaster();
																	email.setTo(customerEmail);
																	email.setFrom(from);
																	email.setSubject(subject);
																	email.setText(text);
																	
																	if (mailService != null && email != null) {
															            boolean isSent = mailService.sendEmail(email);  // Assume sendEmail() returns true if email is sent
															            if (isSent) {
															                System.out.println("\nOTP sent successfully to " + customerEmail);
															                System.out.println("\n Enter Your OTP");
															                String custOTP=sc.nextLine();
															                if(otp.equals(custOTP)){
															                	 from = "rohanalimkar@gmail.com";
															                	 to = customerEmail;
															                	 subject = "Hotel Reservation Details";
															                	 text = "Dear " + customerName + ",\n\n" +
															                			 "We happy to inform you that your reservation at " + hotelService.getHotelName(hotelId) + " has been Confirm .\n\n" +
														                	              "Reservation Details:\n" +
														                	              "Hotel Name: " + hotelService.getHotelName(hotelId) + "\n" +
														                	              "Room Number: " + roomNumber + "\n" +
														                	              "Check-In Date: " + checkInDate + "\n" +
														                	              "Check-Out Date: " + checkOutDate + "\n" +
														                	              "Total Amount: " + totalBill + "\n\n" +
														                	              "If you have any further questions or need assistance, feel free to reach out to us at support@"+hotelService.getHotelName(hotelId)+".com.\n\n" 
														                	              +"Best regards,\n" +
														                	              "RA InfoTech";
															                	              
															                	email = new EmailMaster();
															                	email.setTo(customerEmail);
															                	email.setFrom(from);
															                	email.setSubject(subject);
															                	email.setText(text);

															                	if (mailService != null && email != null) {
															                	     isSent = mailService.sendEmail(email);
															                	    if (isSent) {
															                	        System.out.println("Booking  email sent successfully to " + customerEmail);
															                	    } else {
															                	        System.out.println("Failed to send booking  email.");
															                	    }
															                	} else {
															                	    System.out.println("Mail service or email object is not properly initialized.");
															                	}
															                	System.out.println(reservationService.isConfirmedBoking(reservation)?"Reservation Confirmed":"Issue for Reservation");
																	            String roomstatus="Booked";
																	            roomService.isRoomStatusUpdate(hotelId,roomNumber,roomstatus);
																	            
															                }else {
															                	System.out.println("You Enter Wrong OTP");
															                }
															            } else {
															                System.out.println("Failed to send OTP email.");
															            }
															        } else {
															            System.out.println("Mail service or email object is not properly initialized.");
															        }
														            
														            }else {
														            	System.out.println("*******************************************************************");
														            	System.out.println("\n🙏🙏 Thank You for visiting 🙏🙏");
														            }
															 } else {
														            System.out.println("There is no Data Found..   Sorry for inconvenience...🙏🙏\n");
														        }
											            }
											            }else{
												            System.out.println(bookRoom+" room not Available");
											            }
									        } else {
									            System.out.println("There is no Data Present in table.");
									        }									
									}else {
									if(districtId>0)
									{
										
										System.out.println("Enter City Name:");
										String cityName=sc.nextLine().trim();
										int cityId=cityService.isCityPresent(cityName);
										if(cityId>0)
										{
											Optional<List<HotelMaster>> o=cityService.CityWiseHotel(stateName, districtName, cityName);									
											if (o.isPresent() && !o.get().isEmpty()) {
									            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
									            System.out.printf("%-5s | %-30s | %-30s | %-30s | %-30s %n", "hotelId", "Hotel Name","State","District","City");
									            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
									            
									            AtomicInteger count = new AtomicInteger(1);
									            o.get().forEach(h -> {
									                System.out.printf("%-5d | %-30s | %-30s | %-30s | %-30s %n", h.getHotelId(), h.getHotelName(),h.getState(),h.getDistrictName(),h.getCityName());
									                count.incrementAndGet();
									            });
									            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
									            String reservationDate = LocalDate.now().toString();
													boolean correctDate=false;
													String checkInDate = null,checkOutDate = null;
													long daysBetween;
													LocalDate checkIn = null;
													LocalDate checkOut = null;
										            while(!correctDate) {	 
														 System.out.println("Enter checkIn-Date [yyyy-mm-dd]:");
												             checkInDate = sc.nextLine();
												            System.out.println("Enter checkOut-Date [yyyy-mm-dd]:");
												            checkOutDate = sc.nextLine();
												            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
												             checkIn = LocalDate.parse(checkInDate, formatter);
												            checkOut = LocalDate.parse(checkOutDate, formatter);
														 if (reservationDate.compareTo(checkInDate) <= 0 && checkInDate.compareTo(checkOutDate) <= 0)
												                correctDate = true;
														 
												            else
												                System.out.println("Invalid dates. Please ensure you enter correct check-in  and check-out date.");
												        }
										            daysBetween= ChronoUnit.DAYS.between(checkIn, checkOut);
										            System.out.println("Your total days of stay is:"+daysBetween);
										            System.out.println("Enter number Of Guests:");
										            int numberOfGuests=sc.nextInt();
										            sc.nextLine();
										            System.out.println("Enter Hotel Id for booking");
										            int hotelId=sc.nextInt();
										            	sc.nextLine();
										            	int bookRoom=(int) Math.ceil((double) numberOfGuests / 4);
												            if(bookRoom<=roomService.getAvailableRoomCount(hotelId))
												            {
												            if(numberOfGuests>4)
												            {
												            	System.out.println("You Have to Book"+bookRoom);
												            	int count1=0;
													            while(count1<bookRoom)
													            {
														            Optional<List<RoomMaster>> o1=roomService.getAllAvailableRoom(hotelId);									
																	 if (o1.isPresent() && !o1.get().isEmpty()) {
																            System.out.println("-------------------------------------------------------------------");
																            System.out.printf("%-20s | %-30s | %-10s |%n","Room Number","Room Type","Room Price");
																            System.out.println("-------------------------------------------------------------------");
																            
																            o1.get().forEach(h -> {
																                System.out.printf("%-20s | %-30s | %-10.2f |%n",h.getRoomNumber(),h.getRoomType(),h.getRoomPrice());
																            });
																            System.out.println("-------------------------------------------------------------------");
																            customerId=customerService.getCustomerId(customerEmail);
																			 System.out.println("Enter Room Id:");
																			 String roomNumber=sc.nextLine();
																			 int roomId=roomService.getRoomId(hotelId, roomNumber);
																            System.out.println("Available Amenities for Hotel " + hotelId + ":");
																            
																            Optional<List<HotelAmenitiesMaster>> o2 = hotelAmenityService.getAllAmenities(hotelId);

																            if (o2.isPresent() && !o2.get().isEmpty()) {
																                System.out.println("---------------------------------------------------");
																                System.out.printf("%-10s | %-30s | %-10s%n", "Amenity ID", "Amenity Name", "Price");
																                System.out.println("---------------------------------------------------");

																                o2.get().forEach(a -> {
																                    System.out.printf("%-10d | %-30s | %-10.2f%n", a.getAmenityId(), a.getAmenityName(), a.getPrice());
																                });

																                System.out.println("---------------------------------------------------");
																            } else {
																                System.out.println("No amenities found for this hotel.");
																            }
																            float amenitiesCost = 0.0f;
																            boolean chooseAmenity=false;
																            while(!chooseAmenity) {
																            	System.out.println("do you want to add amenity:[Yes/No]");
																            	String amenityChoice=sc.nextLine().toUpperCase();
																            	if(amenityChoice.equals("NO"))
																            	{
																            		chooseAmenity=true;
																            	}else {
																            		System.out.println("Enter Amenity Id:");
																            		int amenityId=sc.nextInt();
																            		sc.nextLine();
																            		amenitiesCost+=hotelAmenityService.getHotelAmenityPrice(hotelId, amenityId);
																            	}
																            }
																            System.out.println("************** HOTEL BILL **************");
																            System.out.println("Hotel ID: " + hotelId);
																            System.out.println("Room Number: " + roomNumber);
																            System.out.println("----------------------------------------");
																            System.out.println("Amenity Cost (per day)   : " + amenitiesCost);
																            float roomPrice=roomService.getRoomPrice(hotelId, roomNumber);
																            System.out.println("Room Price (per day)     : " +roomPrice);
																            System.out.println("----------------------------------------");
																            System.out.println("Days of Stay             : " + daysBetween);
																            System.out.println("----------------------------------------");
																            float totalBill = (amenitiesCost * daysBetween) + (roomPrice * daysBetween);
																            System.out.println("Total Bill               : " + totalBill);
																            System.out.println("----------------------------------------");
																            System.out.print("Advance Payment          : ");
																            float advancePayment = sc.nextFloat();
																            sc.nextLine();
																            System.out.println("----------------------------------------");
																            float remainingAmount = totalBill - advancePayment;
																            System.out.println("Remaining Amount to Pay  : " + remainingAmount);
																            System.out.println("----------------------------------------");
																            System.out.println("************ THANK YOU FOR STAYING ************");

																            String Bookinstatus=null;
																            reservation=new ReservationMaster();
																            reservation.setAdvanceAmount(advancePayment);
																            reservation.setCheckInDate(checkInDate);
																            reservation.setCheckOutDate(checkOutDate);
																            reservation.setCustomerId(customerId);
																            reservation.setHotelId(hotelId);
																            reservation.setNumberOfGuests(numberOfGuests);
																            reservation.setReservationDate(reservationDate);
																            reservation.setRoomId(roomId);
																            reservation.setTotalAmount(totalBill);
																            System.out.println("Confirm Your Booking");
																            String confirm=sc.nextLine().toLowerCase();
																            if(confirm.equals("yes"))
																            {
																             Bookinstatus="Confirmed";
																             String otp = HotelReservationSystem.generateOTP();
																             reservation.setRemainingAmount(remainingAmount);
																            reservation.setStatus(Bookinstatus);
																            String from="rohanalimkar@gmail.com";
																            String to=customerEmail;
																            String subject = "Your OTP for Hotel Reservation System";
																            String text = "Dear " + customerName + ",\n\n" +
																                      "You requested an OTP to verify your account in the Hotel Reservation System. Here is your One-Time Password:\n\n" +
																                      "OTP: " + otp + "\n\n" +
																                      "Please enter this OTP on the verification page. This OTP is valid for the next 5 minutes.\n\n" +
																                      "If you did not request this OTP or if you have any questions, please contact us at support@"+hotelService.getHotelName(hotelId)+".com.\n\n" +
																                      "Best regards,\n" +
																                      "RA InfoTech";
																            email=new EmailMaster();
																			email.setTo(customerEmail);
																			email.setFrom(from);
																			email.setSubject(subject);
																			email.setText(text);
																			
																			if (mailService != null && email != null) {
																	            boolean isSent = mailService.sendEmail(email);  // Assume sendEmail() returns true if email is sent
																	            if (isSent) {
																	                System.out.println("\nOTP sent successfully to " + customerEmail);
																	                System.out.println("\n Enter Your OTP");
																	                String custOTP=sc.nextLine();
																	                if(otp.equals(custOTP)){
																	                	 from = "rohanalimkar@gmail.com";
																	                	 to = customerEmail;
																	                	 subject = "Hotel Reservation Details";
																	                	 text = "Dear " + customerName + ",\n\n" +
																	                			 "We happy to inform you that your reservation at " + hotelService.getHotelName(hotelId) + " has been Confirm .\n\n" +
																                	              "Reservation Details:\n" +
																                	              "Hotel Name: " + hotelService.getHotelName(hotelId) + "\n" +
																                	              "Room Number: " + roomNumber + "\n" +
																                	              "Check-In Date: " + checkInDate + "\n" +
																                	              "Check-Out Date: " + checkOutDate + "\n" +
																                	              "Total Amount: " + totalBill + "\n\n" +
																                	              "If you have any further questions or need assistance, feel free to reach out to us at support@"+hotelService.getHotelName(hotelId)+".com.\n\n" 
																                	              +"Best regards,\n" +
																                	              "RA InfoTech";
																	                	              
																	                	email = new EmailMaster();
																	                	email.setTo(customerEmail);
																	                	email.setFrom(from);
																	                	email.setSubject(subject);
																	                	email.setText(text);

																	                	if (mailService != null && email != null) {
																	                	     isSent = mailService.sendEmail(email);
																	                	    if (isSent) {
																	                	        System.out.println("Booking  email sent successfully to " + customerEmail);
																	                	    } else {
																	                	        System.out.println("Failed to send booking email.");
																	                	    }
																	                	} else {
																	                	    System.out.println("Mail service or email object is not properly initialized.");
																	                	}
																	                	System.out.println(reservationService.isConfirmedBoking(reservation)?"Reservation Confirmed":"Issue for Reservation");
																			            String roomstatus="Booked";
																			            roomService.isRoomStatusUpdate(hotelId,roomNumber,roomstatus);
																			            
																	                }else {
																	                	System.out.println("You Enter Wrong OTP");
																	                }
																	            } else {
																	                System.out.println("Failed to send OTP email.");
																	            }
																	        } else {
																	            System.out.println("Mail service or email object is not properly initialized.");
																	        }
																            
																            }else {
																            	System.out.println("*******************************************************************");
																            	System.out.println("\n🙏🙏 Thank You for visiting 🙏🙏");
																            }
																	 } else {
																            System.out.println("There is no Data Found..   Sorry for inconvenience...🙏🙏\n");
																        }
																	 count1++;
													            }
												            }else {
													            Optional<List<RoomMaster>> o1=roomService.getAllAvailableRoom(hotelId);									
																 if (o1.isPresent() && !o1.get().isEmpty()) {
															            System.out.println("-------------------------------------------------------------------");
															            System.out.printf("%-20s | %-30s | %-10s |%n","Room Number","Room Type","Room Price");
															            System.out.println("-------------------------------------------------------------------");
															            
															            o1.get().forEach(h -> {
															                System.out.printf("%-20s | %-30s | %-10.2f |%n",h.getRoomNumber(),h.getRoomType(),h.getRoomPrice());
															            });
															            System.out.println("-------------------------------------------------------------------");
															            customerId=customerService.getCustomerId(customerEmail);
																		 System.out.println("Enter Room Id:");
																		 String roomNumber=sc.nextLine();
																		 int roomId=roomService.getRoomId(hotelId, roomNumber);
															            System.out.println("Available Amenities for Hotel " + hotelId + ":");
															            
															            Optional<List<HotelAmenitiesMaster>> o2 = hotelAmenityService.getAllAmenities(hotelId);

															            if (o2.isPresent() && !o2.get().isEmpty()) {
															                System.out.println("---------------------------------------------------");
															                System.out.printf("%-10s | %-30s | %-10s%n", "Amenity ID", "Amenity Name", "Price");
															                System.out.println("---------------------------------------------------");

															                o2.get().forEach(a -> {
															                    System.out.printf("%-10d | %-30s | %-10.2f%n", a.getAmenityId(), a.getAmenityName(), a.getPrice());
															                });

															                System.out.println("---------------------------------------------------");
															            } else {
															                System.out.println("No amenities found for this hotel.");
															            }
															            float amenitiesCost = 0.0f;
															            boolean chooseAmenity=false;
															            while(!chooseAmenity) {
															            	System.out.println("do you want to add amenity:[Yes/No]");
															            	String amenityChoice=sc.nextLine().toUpperCase();
															            	if(amenityChoice.equals("NO"))
															            	{
															            		chooseAmenity=true;
															            	}else {
															            		System.out.println("Enter Amenity Id:");
															            		int amenityId=sc.nextInt();
															            		sc.nextLine();
															            		amenitiesCost+=hotelAmenityService.getHotelAmenityPrice(hotelId, amenityId);
															            	}
															            }
															            System.out.println("************** HOTEL BILL **************");
															            System.out.println("Hotel ID: " + hotelId);
															            System.out.println("Room Number: " + roomNumber);
															            System.out.println("----------------------------------------");
															            System.out.println("Amenity Cost (per day)   : " + amenitiesCost);
															            float roomPrice=roomService.getRoomPrice(hotelId, roomNumber);
															            System.out.println("Room Price (per day)     : " +roomPrice);
															            System.out.println("----------------------------------------");
															            System.out.println("Days of Stay             : " + daysBetween);
															            System.out.println("----------------------------------------");
															            float totalBill = (amenitiesCost * daysBetween) + (roomPrice * daysBetween);
															            System.out.println("Total Bill               : " + totalBill);
															            System.out.println("----------------------------------------");
															            System.out.print("Advance Payment          : ");
															            float advancePayment = sc.nextFloat();
															            sc.nextLine();
															            System.out.println("----------------------------------------");
															            float remainingAmount = totalBill - advancePayment;
															            System.out.println("Remaining Amount to Pay  : " + remainingAmount);
															            System.out.println("----------------------------------------");
															            System.out.println("************ THANK YOU FOR STAYING ************");

															            String Bookinstatus=null;
															            reservation=new ReservationMaster();
															            reservation.setAdvanceAmount(advancePayment);
															            reservation.setCheckInDate(checkInDate);
															            reservation.setCheckOutDate(checkOutDate);
															            reservation.setCustomerId(customerId);
															            reservation.setHotelId(hotelId);
															            reservation.setNumberOfGuests(numberOfGuests);
															            reservation.setReservationDate(reservationDate);
															            reservation.setRoomId(roomId);
															            reservation.setTotalAmount(totalBill);
															            System.out.println("Confirm Your Booking");
															            String confirm=sc.nextLine().toLowerCase();
															            if(confirm.equals("yes"))
															            {
															             Bookinstatus="Confirmed";
															             String otp = HotelReservationSystem.generateOTP();
															             reservation.setRemainingAmount(remainingAmount);
															            reservation.setStatus(Bookinstatus);
															            String from="rohanalimkar@gmail.com";
															            String to=customerEmail;
															            String subject = "Your OTP for Hotel Reservation System";
															            String text = "Dear " + customerName + ",\n\n" +
															                      "You requested an OTP to verify your account in the Hotel Reservation System. Here is your One-Time Password:\n\n" +
															                      "OTP: " + otp + "\n\n" +
															                      "Please enter this OTP on the verification page. This OTP is valid for the next 5 minutes.\n\n" +
															                      "If you did not request this OTP or if you have any questions, please contact us at support@"+hotelService.getHotelName(hotelId)+".com.\n\n" +
															                      "Best regards,\n" +
															                      "RA InfoTech";
															            email=new EmailMaster();
																		email.setTo(customerEmail);
																		email.setFrom(from);
																		email.setSubject(subject);
																		email.setText(text);
																		
																		if (mailService != null && email != null) {
																            boolean isSent = mailService.sendEmail(email);  // Assume sendEmail() returns true if email is sent
																            if (isSent) {
																                System.out.println("\nOTP sent successfully to " + customerEmail);
																                System.out.println("\n Enter Your OTP");
																                String custOTP=sc.nextLine();
																                if(otp.equals(custOTP)){
																                	 from = "rohanalimkar@gmail.com";
																                	 to = customerEmail;
																                	 subject = "Hotel Reservation Details";
																                	 text = "Dear " + customerName + ",\n\n" +
																                			 "We happy to inform you that your reservation at " + hotelService.getHotelName(hotelId) + " has been Confirm .\n\n" +
															                	              "Reservation Details:\n" +
															                	              "Hotel Name: " + hotelService.getHotelName(hotelId) + "\n" +
															                	              "Room Number: " + roomNumber + "\n" +
															                	              "Check-In Date: " + checkInDate + "\n" +
															                	              "Check-Out Date: " + checkOutDate + "\n" +
															                	              "Total Amount: " + totalBill + "\n\n" +
															                	              "If you have any further questions or need assistance, feel free to reach out to us at support@"+hotelService.getHotelName(hotelId)+".com.\n\n" 
															                	              +"Best regards,\n" +
															                	              "RA InfoTech";
																                	              
																                	email = new EmailMaster();
																                	email.setTo(customerEmail);
																                	email.setFrom(from);
																                	email.setSubject(subject);
																                	email.setText(text);

																                	if (mailService != null && email != null) {
																                	     isSent = mailService.sendEmail(email);
																                	    if (isSent) {
																                	        System.out.println("Booking email sent successfully to " + customerEmail);
																                	    } else {
																                	        System.out.println("Failed to send booking email.");
																                	    }
																                	} else {
																                	    System.out.println("Mail service or email object is not properly initialized.");
																                	}
																                	System.out.println(reservationService.isConfirmedBoking(reservation)?"Reservation Confirmed":"Issue for Reservation");
																		            String roomstatus="Booked";
																		            roomService.isRoomStatusUpdate(hotelId,roomNumber,roomstatus);
																		            
																                }else {
																                	System.out.println("You Enter Wrong OTP");
																                }
																            } else {
																                System.out.println("Failed to send OTP email.");
																            }
																        } else {
																            System.out.println("Mail service or email object is not properly initialized.");
																        }
															            
															            }else {
															            	System.out.println("*******************************************************************");
															            	System.out.println("\n🙏🙏 Thank You for visiting 🙏🙏");
															            }
																 } else {
															            System.out.println("There is no Data Found..   Sorry for inconvenience...🙏🙏\n");
															        }
												            }
												            }else{
												            	System.out.println(bookRoom+" Rooms not available");
												            }   
											 } else {
										            System.out.println("There is no Data Found..   Sorry for inconvenience...🙏🙏\n");
										        }										
										}else {
											System.out.println("City not Found");
										}
									}
									else {
										System.out.println("State Not Found");
									}
								}
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
							System.out.println("\n🙏🙏 Thank You for visiting 🙏🙏");
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
								System.out.println("-----------------------------------------------------------------");
								System.out.println("\n\t\t"+hotelService.getHotelName(hotelEmail));
								System.out.println("-----------------------------------------------------------------");
								do {
									int hotelId=adminService.getHotelId(hotelEmail);
									String amenityName=null;
									System.out.println("\n1.Add amenities\n2.Delete amenities\n3.Update amenities Price.\n4.Show All Rooms\n5.Add Room\n6.Update Room price.\n7.Update Room Status.\n8.Check-out  \n9.Exit from current menu.");
									System.out.println("Enter Your Choice:");
									int ch3=sc.nextInt();
									sc.nextLine();
									switch(ch3)
									{
									case 1:
										//------------------------Add amenities-------------------------------------
										Optional<List<AmenitiesMaster>> o=amenitiesService.getAllAmenities();									

										 if (o.isPresent()) {
											 AtomicInteger count = new AtomicInteger(1);
										     o.get().forEach(a -> {  
										         System.out.println(count.get()+". "+a.getAmenityName());
										         count.incrementAndGet();
										     });
										 } else {
										     System.err.println("There is no Data Present in table.");
										 }
										System.out.println("Enter Amenity Name to add in your hotel:");
										amenityName=sc.nextLine();
										int amenityId=amenitiesService.getAmenityId(amenityName);
										if(amenityId>0)
										{
										System.out.println("Enter Amenity Price:");
										float price=sc.nextFloat();
										sc.nextLine();
										hotelAminity=new HotelAmenitiesMaster();
										hotelAminity.setHotelId(hotelId);
										hotelAminity.setAmenityId(amenityId);
										hotelAminity.setPrice(price);
										System.out.println(hotelAmenityService.addHotelAmenities(hotelAminity)?"\nHotel Amenity added successfully":"\nHotel Amenity not added successfully");
										}else {
											System.out.println("No such amenity found");
										}
										break;
									case 2://2.Delete amenities
										System.out.println("Enter Amenity Name for delete:");
										 amenityName=sc.nextLine();
										 amenityId=amenitiesService.getAmenityId(amenityName);
										 System.out.println(hotelAmenityService.deleteHotelAmenities(hotelId, amenityId)?"Hotel amenity delted successfully":"Hotel amenity not delted successfully");
										break;
									case 3://Update amenities Price.
										System.out.println("Enter Amenity Name for Update:");
										amenityName=sc.nextLine();
										amenityId=amenitiesService.getAmenityId(amenityName);
										System.out.println("Enter Updated Value for amenity:");
										float price=sc.nextFloat();
													sc.nextLine();
									System.out.println(hotelAmenityService.updateHotelAmenities(price, hotelId, amenityId)?"Hotel Amenity Updated SuccessFully":"Hotel Amenity not Updated SuccessFully");	
										break;
									case 4://------------------------------Show All Room------------------------------
									{
										hotelId=adminService.getHotelId(hotelEmail);
										Optional<List<RoomMaster>> o1=roomService.getAllAvailableRoom(hotelId);									
										 if (o1.isPresent() && !o1.get().isEmpty()) {
									            System.out.println("-------------------------------------------------------------------");
									            System.out.printf("%-20s | %-30s | %-10s |%n","Room Number","Room Type","Room Price");
									            System.out.println("-------------------------------------------------------------------");
									            
									            o1.get().forEach(h -> {
									                System.out.printf("%-20s | %-30s | %-10.2f |%n",h.getRoomNumber(),h.getRoomType(),h.getRoomPrice());
									            });
									            System.out.println("-------------------------------------------------------------------");
									        } else {
									            System.out.println("There is no Data Found..   Sorry for inconvenience...🙏🙏\n");
									        }
									}
										break;
									case 5://------------------------------Add Room------------------------------
											System.out.println("Enter Room Number:");
											String roomNumber=sc.nextLine();
											System.out.println("Enter You Room Type:");
											String roomtype=sc.nextLine().trim();
											int typeId=roomTypeService.getRoomTypeId(roomtype);
											System.out.println("Enter Room Status [Available,Booked,Under Maintenance]:");
											String roomStatus=sc.nextLine().trim();
											System.out.println("Enter Room Price:");
											float roomPrice=sc.nextFloat();
											room=new RoomMaster();
											room.setHotelId(hotelId);
											room.setRoomNumber(roomNumber);
											room.setRoomTypeId(typeId);
											room.setRoomStatus(roomStatus);
											room.setRoomPrice(roomPrice);
											System.out.println(roomService.isRoomPresent(hotelId, roomNumber)?"Room Already Present":(roomService.isAddNewRoom(room)?"Room Added SuccessFully":"Room Not Added SuccessFully"));
										break;
									case 6://------------------------------Update Room Price------------------------------
											hotelId=adminService.getHotelId(hotelEmail);
											System.out.println("Enter Room Number:");
											roomNumber=sc.nextLine();
											System.out.println("Enter Updated Room Price:");
											price=sc.nextFloat();
											sc.nextLine();
											System.out.println(roomService.isRoomPresent(hotelId, roomNumber)?((roomService.isRoomUpdatePrice(hotelId, roomNumber,price)?"Room Price Updated SuccessFully":"Room Price Not Updated SuccessFully")):"No Such Room Present");
										break;
									case 7://------------------------------Update Room Status------------------------------
										hotelId=adminService.getHotelId(hotelEmail);
										System.out.println("Enter Room Number:");
										roomNumber=sc.nextLine();
										System.out.println("Enter Room Status: [Available,Under Maintenance]:");
										String roomstatus=sc.nextLine().toLowerCase();
										if(roomstatus.equals("book"))
										{
											System.out.println("Sorry this status will not accepted.. please try again.");
										}else {
											System.out.println( roomService.isRoomStatusUpdate(hotelId, roomNumber, roomstatus)?"Room Status Updated":"Room Status not updated");
										}
										break;
									case 8:System.out.println("Enter Hotel Reservation Id:");
											int reservationId=sc.nextInt();
											System.out.println("Enter Customer Id:");
											 customerId=sc.nextInt();
											 System.out.println("Enter Room Id:");
											 int roomId=sc.nextInt();
											float remainingAmount=0;
											String status="Completed";
											System.out.println(reservationService.isUpdatestatus(customerId, reservationId, customerId, status)?"Check-Out Successfully":"Check-Out not Successfully");
											roomstatus="Available";
											System.out.println(roomService.isRoomStatusUpdateToAvailable(hotelId, roomId, roomstatus));
											break;
										
									case 9://------------------------------close the system-------------------------------
										System.out.println("\n🙏🙏 Thank You for visiting 🙏🙏\n");
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
								System.out.println("Enter Your choice:");
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
												System.out.println("\n1.Create sub Admin.\n2.Add amenities\n3.Delete amenities\n4.Update amenities.\n5.Update State.\n6.Delete State\n7.Update District.\n8.Delete District.\n9.Update City.\n10.Delete City.\n11.Update Sub Admin.\n12.Show All Room Type.\n13.Add Rooms.\n14.Update RoomType\n15.Delete Room Type.\n16.Exit from current menu.");
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
																	String password1 = null;
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
																			
																			if(mailService.sendEmail(email))
																			{
																			System.out.println(adminService.isaddNewAdmin(admin)?"Sub Admin added successfully now you can Login Now You Can Login":"Sub admin not cretaed...");
																			}else {
																				System.out.println("Failed to send the email.");
																			}
								
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
													//--------------------------------Add Amenities Start----------------------------------
															System.out.println("\n Enter Your amenities Details:");
															System.out.println("Amenity Name:");
															String amenityName=sc.nextLine().trim();
															System.out.println("Amenity description:");
															String description=sc.nextLine().trim();
															amenities=new AmenitiesMaster();
															amenities.setAmenityName(amenityName);
															amenities.setDescription(description);
															System.out.println(amenitiesService.addAmenities(amenities)?"\nAmenity added successfully":"amenity not added");
													break;
													//--------------------------------Add Amenities End----------------------------------
													case 3:	//--------------------------------Delete Amenities Start----------------------------------
															System.out.println("Enter Amenity Name for delete it:");
															amenityName=sc.nextLine().trim();
															System.out.println(amenitiesService.deleteAmenities(amenityName)?"\nAmenity Deleted successfully":"\nAmenity not Deleted successfully");
													break;
													case 4:
														//--------------------------------Update Amenity---------------------------------
															System.out.println("\n Enter Amenity Name for Update:");
															 amenityName=sc.nextLine().trim();
															System.out.println("\n Enter Your amenities Details for update:");
															int amenityId=amenitiesService.getAmenityId(amenityName);
															System.out.println("Enter Updated amenityName:");
															amenityName=sc.nextLine().trim();
															System.out.println("Enter Updated amenity description:");
															description=sc.nextLine().trim();
															System.out.println(amenitiesService.updateAmenities(amenityName, description, amenityId)?"\nAmenity Updated Successfully":"\nAmenity not Updated");
													break;
													case 5:	//--------------------Update State--------------------
															System.out.println("Enter State Name for Update:");
															String stateName=sc.nextLine();
															int stateID=stateService.isStatePresent(stateName);
															System.out.println("Enter Nesw State Name For Update:");
															String updatedName=sc.nextLine();
															System.out.println(stateService.isUpdateState(stateID, updatedName)?"State Updated Successfully":"State Not Updated");
													break;
													case 6:	//--------------------Delete State--------------------
															System.out.println("Enter State Name To Delete :");
															 stateName=sc.nextLine();
															int stateId=stateService.isStatePresent(stateName);
															System.out.println(stateService.isDeleteState(stateId)?"State Deleted Successfuly":"State Not deleted Succefully");
													break;
													case 7:	//--------------------Update District--------------------
															System.out.println("Enter district Name for Update:");
															String districtName=sc.nextLine();
															int districtId=districtService.isDistrictPresent(districtName);
															System.out.println("Enter New district Name For Update:");
															updatedName=sc.nextLine();
															System.out.println(districtService.isDistrictUpdate(districtId, districtName)?"District Updated Succefully":"District Not Updated Successfully");
														
													break;
													case 8:	//--------------------Delete District--------------------
															System.out.println("Enter district Name for Update:");
															districtName=sc.nextLine();
															districtId=districtService.isDistrictPresent(districtName);
															System.out.println(districtService.isDistrictDelete(districtId)?"District Deleted Succefully":"District Not deleted Successfully");
													break;
													case 9:	//--------------------Update City--------------------
															System.out.println("Enter City Name for Update:");
															String cityName=sc.nextLine();
															int cityId=cityService.isCityPresent(cityName);
															System.out.println("Enter New City Name for Update:");
															updatedName=sc.nextLine();
															System.out.println(cityService.isUpdateCity(cityId, updatedName)?"City Name Updated Successfully":"City Name Not updtaed Successfully");
															
													break;
													case 10:	//--------------------Delete City--------------------
															System.out.println("Enter City Name for Update:");
															 cityName=sc.nextLine();
															cityId=cityService.isCityPresent(cityName);
															System.out.println(cityService.isDeleteCity(cityId)?"city Name Deleted Successfully":"city Name not deleted successfully");
															
													break;
													case 11:	//--------------------Update Sub Admin--------------------
																System.out.println("Enter username for update:");
																String username=sc.nextLine();
																System.out.println("Enter New password:");
																password=sc.nextLine().trim();
																System.out.println("Enter hotelEmail:");
																String hotelEmail=sc.nextLine().trim();
																System.out.println(adminService.isUpdatedAdmin(username, password, hotelEmail)?"Admin updated Successfully":"Admin not updated Successfully");
													break;
													case 12:	//--------------------Show All Room Type--------------------
														
														Optional<List<RoomTypeMaster>> o = roomTypeService.getAllRoomType();
														System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
														System.out.printf("%-5s | %-20s | %-100s|%n", "No.", "Room Type", "Description");
														System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
														if (o.isPresent()) {
														    AtomicInteger count = new AtomicInteger(1);
														    o.get().forEach(a -> {
														        System.out.printf("%-5d | %-20s | %-100s|%n", count.get(), a.getTypeName(), a.getDescription());
														        count.incrementAndGet();
														    });
														} else {
														    System.err.println("There is no Data Present in table.");
														}
														System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
														break;
													case 13:	//-------------------Add Room Type----------------------------
																System.out.println("Enter Room Type:");
																String typeName=sc.nextLine().trim();
																System.out.println("Enter Room type description:");
																 description=sc.nextLine().trim();
																 roomType=new RoomTypeMaster();
																 roomType.setTypeName(typeName);
																 roomType.setDescription(description);
																 System.out.println(roomTypeService.addRoomType(roomType)?"Room Type Added Successfully":"Room Type not Added Successfully");
													break;
													case 14:	//--------------------update Room Type-------------------------
															System.out.println("Enter Room Type For Update:");
															typeName=sc.nextLine().trim();
															amenityId=roomTypeService.getRoomTypeId(typeName);
															System.out.println("Enter Room Type For Update:");
															typeName=sc.nextLine().trim();
															System.out.println("Enter Updated Room Type description:");
															description=sc.nextLine().trim();
															System.out.println(roomTypeService.UpdateRoomType(typeName, description, amenityId)?"Room Type Updated Successfully":"Room Type not Updated Successfully");
													break;
													case 15:	//--------------------delete room type--------------------
														System.out.println("Enter Room Type For Update:");
														typeName=sc.nextLine().trim();
														amenityId=roomTypeService.getRoomTypeId(typeName);
														System.out.println(roomTypeService.deleteRoomTypeId(amenityId)?"Room Type Deleted Successfully":"Room Type not Deleted Successfully");
														break;
													case 16:	//------------------------------close the system-------------------------------
														System.out.println("\n\t 🙏🙏 Thank You for visiting 🙏🙏 \n");
														System.out.println("------------------------------------------------------");
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
									System.out.println("\n\t 🙏🙏 Thank You for visiting 🙏🙏\n");
									System.out.println("------------------------------------------------------");
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
							System.out.println("\n\t🙏🙏 Thank You for visiting 🙏🙏\n");
							System.out.println("------------------------------------------------------");
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
					System.out.println("\n\t 🙏🙏 Thank You for visiting 🙏🙏\n");
					System.out.println("------------------------------------------------------");
					flag=false;
					break;
					//------------------------------close the system End-------------------------------
				default:System.out.println("You Enetr Wrong Choice.");
				}
				}while(flag);
			} catch (Exception e) {
				log.fatal(e);
			}
		}catch (Exception e) {
		e.printStackTrace();
		}
	}
	public static String generateOTP() {
        Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000);
        return String.valueOf(otp);
    }
}


