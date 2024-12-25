package Services;

import java.util.List;
import java.util.Optional;

import Model.RoomTypeMaster;
import Repository.RoomTypeRepository;
import Repository.RoomTypeRepositoryImp;

public class RoomTypeServiceImp implements RoomTypeService{
	RoomTypeRepository roomTypeRepo=new RoomTypeRepositoryImp();
	@Override
	public boolean addRoomType(RoomTypeMaster roomType) {
		
		return roomTypeRepo.addRoomType(roomType);
	}
	@Override
	public boolean UpdateRoomType(String typeName,String description,int roomTypeId) {
		
		return roomTypeRepo.UpdateRoomType(typeName, description, roomTypeId);
	}
	@Override
	public int getRoomTypeId(String typeName) {
		
		return roomTypeRepo.getRoomTypeId(typeName);
	}
	@Override
	public boolean deleteRoomTypeId(int roomTypeId) {
		
		return roomTypeRepo.deleteRoomTypeId(roomTypeId);
	}
	@Override
	public Optional<List<RoomTypeMaster>> getAllRoomType() {
		
		return roomTypeRepo.getAllRoomType();
	}

}
