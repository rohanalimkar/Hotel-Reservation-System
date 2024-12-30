package Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.RoomMaster;

public class RoomRepositoryImp extends DBState implements RoomRepository{

	@Override
	public boolean isAddNewRoom(RoomMaster room) {
		try {
			ps=con.prepareStatement("insert into room(hotelId,roomNumber,roomTypeId,roomStatus,roomPrice) values(?,?,?,?,?)");
			ps.setInt(1, room.getHotelId());
			ps.setString(2, room.getRoomNumber());
			ps.setInt(3, room.getRoomTypeId());
			ps.setString(4, room.getRoomStatus());
			ps.setFloat(5, room.getRoomPrice());
			int result=ps.executeUpdate();
			return result>0?true:false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	public boolean isRoomPresent(int hotelId, String roomNumber) {
		try {
			ps=con.prepareStatement("select roomId from room where hotelId=? and roomNumber=?");
			ps.setInt(1, hotelId);
			ps.setString(2, roomNumber);
			rs=ps.executeQuery();
			if(rs.next())
			{
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isRoomUpdatePrice(int hotelId, String roomNumber,float roomPrice) {
		try {
			ps=con.prepareStatement("update room set roomPrice=? where hotelId=? and roomNumber=?");
			ps.setFloat(1, roomPrice);
			ps.setInt(2, hotelId);
			ps.setString(3, roomNumber);
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isRoomStatusUpdate(int hotelId, String roomNumber, String roomStatus) {
		try {
			ps=con.prepareStatement("update room set roomStatus=? where hotelId=? and roomNumber=?");
			ps.setString(1, roomStatus);
			ps.setInt(2, hotelId);
			ps.setString(3, roomNumber);
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int getAvailableRoomCount(int hotelId) {
		try {
			ps = con.prepareStatement("SELECT COUNT(*) AS availableRoomCount FROM room r JOIN roomType rt ON r.roomTypeId = rt.roomTypeId JOIN hotel h ON r.hotelId = h.hotelId WHERE r.roomStatus = 'Available' AND h.hotelId = ?");
			ps.setInt(1, hotelId);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("availableRoomCount");
			}else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public Optional<List<RoomMaster>> getAllAvailableRoom(int hotelId) {
	    List<RoomMaster> list = new ArrayList<>();
	    try {
	        ps = con.prepareStatement("SELECT r.roomId, r.roomNumber, r.roomPrice, rt.typeName FROM room r JOIN roomType rt ON r.roomTypeId = rt.roomTypeId JOIN hotel h ON r.hotelId = h.hotelId WHERE r.roomStatus = 'Available' and h.hotelid=?");
	        ps.setInt(1, hotelId);
	        rs = ps.executeQuery();
	        list.clear();
	        while (rs.next()) {
	            String roomNumber = rs.getString("roomNumber");
	            String roomType = rs.getString("typeName");
	            float roomPrice = rs.getFloat("roomPrice");
	            list.add(new RoomMaster(roomNumber,roomType,roomPrice));
	        }
	        return list.isEmpty() ? Optional.empty() : Optional.of(list);
	    } catch (Exception e) {
	        System.out.println("Error is: " + e);
	        return Optional.empty();
	    }
	}

	@Override
	public float getRoomPrice(int hotelId, String roomNumber) {
		try {
			ps=con.prepareStatement("select roomPrice from room where hotelId=? and roomNumber=?");
			ps.setInt(1, hotelId);
			ps.setString(2,roomNumber);
			rs=ps.executeQuery();
			if(rs.next())
			{
				return rs.getFloat("roomPrice");
			}else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}	
	}

	@Override
	public int getRoomId(int hotelId, String roomNumber) {
		try {
			ps=con.prepareStatement("select roomId from room where hotelId=? and roomNumber=?");
			ps.setInt(1, hotelId);
			ps.setString(2,roomNumber);
			rs=ps.executeQuery();
			if(rs.next())
			{
				return rs.getInt("roomId");
			}else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean isRoomStatusUpdateToAvailable(int hotelId, int roomId, String roomStatus) {
		try {
			ps=con.prepareStatement("update room set roomStatus=? where hotelId=? and roomId=?");
			ps.setString(1, roomStatus);
			ps.setInt(2, hotelId);
			ps.setInt(3, roomId);
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

		
	
}
