package Services;

import Model.ReservationMaster;
import Repository.ReservationRepository;
import Repository.ReservationRepositoryImp;

public class ReservationServiceImp implements ReservationService {
	ReservationRepository reservation=new ReservationRepositoryImp();
	@Override
	public boolean isConfirmedBoking(ReservationMaster confirmeReservation) {
		// TODO Auto-generated method stub
		return reservation.isConfirmedBoking(confirmeReservation);
	}

	@Override
	public boolean isUpdatestatus(int customerId, int reservationId,String status) {
		// TODO Auto-generated method stub
		return reservation.isUpdatestatus(customerId, reservationId,status);
	}

	@Override
	public float isAnyPendingPayment(int customerId, int reservationId) {
		// TODO Auto-generated method stub
		return reservation.isAnyPendingPayment(customerId, reservationId);
	}

	@Override
	public boolean isUpdatestatus(int customerId, int reservationId, float payment, String status) {
		// TODO Auto-generated method stub
		return reservation.isUpdatestatus(customerId, reservationId, payment, status);
	}

}
