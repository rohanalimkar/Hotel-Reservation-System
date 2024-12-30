package Services;

import java.util.List;
import java.util.Optional;

import Model.RoomMaster;
import Repository.RoomRepository;
import Repository.RoomRepositoryImp;

public class RoomServiceImp implements RoomService{
	RoomRepository roomRepo=new RoomRepositoryImp();
	@Override
	public boolean isAddNewRoom(RoomMaster room) {
		
		return roomRepo.isAddNewRoom(room);
	}
	@Override
	public boolean isRoomPresent(int hotelId, String roomNumber) {
		
		return roomRepo.isRoomPresent(hotelId, roomNumber);
	}
	@Override
	public boolean isRoomUpdatePrice(int hotelId, String roomNumber,float roomPrice) {
		
		return roomRepo.isRoomUpdatePrice(hotelId, roomNumber,roomPrice);
	}
	@Override
	public boolean isRoomStatusUpdate(int hotelId, String roomNumber, String roomStatus) {
		
		return roomRepo.isRoomStatusUpdate(hotelId, roomNumber, roomStatus);
	}
	@Override
	public Optional<List<RoomMaster>> getAllAvailableRoom(int hotelId) {
		
		return roomRepo.getAllAvailableRoom(hotelId);
	}
	@Override
	public float getRoomPrice(int hotelId, String roomNumber) {
		
		return roomRepo.getRoomPrice(hotelId, roomNumber);
	}
	@Override
	public int getAvailableRoomCount(int hotelId) {
		
		return roomRepo.getAvailableRoomCount(hotelId);
	}
	@Override
	public int getRoomId(int hotelId, String roomNumber) {
		
		return roomRepo.getRoomId(hotelId, roomNumber);
	}
	@Override
	public boolean isRoomStatusUpdateToAvailable(int hotelId, int roomId, String roomStatus) {
		// TODO Auto-generated method stub
		return roomRepo.isRoomStatusUpdateToAvailable(hotelId, roomId, roomStatus);
	}
	

}
