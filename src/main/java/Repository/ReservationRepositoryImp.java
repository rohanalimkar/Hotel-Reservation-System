package Repository;

import Model.ReservationMaster;

public class ReservationRepositoryImp extends DBState implements ReservationRepository {

	@Override
	public boolean isConfirmedBoking(ReservationMaster confirmeReservation) {
		try {
			ps=con.prepareStatement("insert into reservation(hotelId,customerId,roomId,reservationDate,checkInDate,checkOutDate,numberOfGuests,totalAmount,advanceAmount,remainingAmount,status) values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1,confirmeReservation.getHotelId());
			ps.setInt(2, confirmeReservation.getCustomerId());
			ps.setInt(3, confirmeReservation.getRoomId());
			ps.setString(4, confirmeReservation.getReservationDate());
			ps.setString(5, confirmeReservation.getCheckInDate());
			ps.setString(6,confirmeReservation.getCheckOutDate());
			ps.setInt(7, confirmeReservation.getNumberOfGuests());
			ps.setFloat(8, confirmeReservation.getTotalAmount());
			ps.setFloat(9, confirmeReservation.getAdvanceAmount());
			ps.setFloat(10,confirmeReservation.getRemainingAmount());
			ps.setString(11, confirmeReservation.getStatus());
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean isUpdatestatus(int customerId, int reservationId,String status) {
		try {
			ps=con.prepareStatement("update reservation set status=? where reservationId=? and customerId=?");
			ps.setString(1,status);
			ps.setInt(2, reservationId);
			ps.setInt(3, customerId);
			rs=ps.executeQuery();
			return rs.next()?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public float isAnyPendingPayment(int customerId, int reservationId) {
		try {
			ps=con.prepareStatement("select remainingAmount from reservation where customerId=? and reservationId=?");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean isUpdatestatus(int customerId, int reservationId, float payment,String status) {
		try {
			ps=con.prepareStatement("update reservation set remainingAmount=? , status=? where reservationId=? and customerId=?");
			ps.setFloat(1, payment);
			ps.setString(2,status);
			ps.setInt(3, reservationId);
			ps.setInt(4, customerId);
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
