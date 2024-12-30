package Services;

import java.util.List;
import java.util.Optional;

import Model.RoomMaster;

public interface RoomService {
	public abstract boolean isAddNewRoom(RoomMaster room);
	public abstract boolean isRoomPresent(int hotelId,String roomNumber);
	public abstract boolean isRoomUpdatePrice(int hotelId,String roomNumber,float roomPrice);
	public abstract boolean isRoomStatusUpdate(int hotelId,String roomNumber,String roomStatus);
	public abstract Optional<List<RoomMaster>> getAllAvailableRoom(int hotelId);
	public abstract float getRoomPrice(int hotelId,String roomNumber);
	public abstract int getAvailableRoomCount(int hotelId);
	public abstract int getRoomId(int hotelId,String roomNumber);
	public abstract boolean isRoomStatusUpdateToAvailable(int hotelId,int roomId,String roomStatus);
}
