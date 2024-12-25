package Services;

import java.util.List;
import java.util.Optional;

import Model.RoomTypeMaster;

public interface RoomTypeService {
	public abstract boolean addRoomType(RoomTypeMaster roomType);
	public abstract boolean UpdateRoomType(String typeName,String description,int roomTypeId);
	public abstract int getRoomTypeId(String typeName);
	public abstract boolean deleteRoomTypeId(int roomTypeId);
	public abstract Optional<List<RoomTypeMaster>> getAllRoomType();
}
