package Repository;

import java.util.List;
import java.util.Optional;
import Model.RoomMaster;

public interface RoomRepository {

	public abstract boolean isAddNewRoom(RoomMaster room); //Add New Rooms
	public abstract boolean isRoomPresent(int hotelId,String roomNumber);  //check Room is present
	public abstract boolean isRoomUpdatePrice(int hotelId,String roomNumber,float roomPrice); //update room price
	public abstract boolean isRoomStatusUpdate(int hotelId,String roomNumber,String roomStatus); //update Room Status
	public abstract int getAvailableRoomCount(int hotelId); //update Room Status
	public abstract Optional<List<RoomMaster>> getAllAvailableRoom(int hotelId);
	public abstract float getRoomPrice(int hotelId,String roomNumber);
}
