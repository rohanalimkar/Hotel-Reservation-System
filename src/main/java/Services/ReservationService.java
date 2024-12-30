package Services;

import Model.ReservationMaster;

public interface ReservationService {
	public abstract boolean isConfirmedBoking(ReservationMaster confirmeReservation);
	public abstract boolean isUpdatestatus(int customerId,int reservationId,String status);
	public abstract float isAnyPendingPayment(int customerId,int reservationId);
	public abstract boolean isUpdatestatus(int customerId,int reservationId,float payment,String status);
}
