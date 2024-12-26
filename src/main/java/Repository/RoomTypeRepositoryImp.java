package Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.RoomTypeMaster;

public class RoomTypeRepositoryImp extends DBState implements RoomTypeRepository {
List<RoomTypeMaster>  list=new ArrayList<RoomTypeMaster>();
	@Override
	public boolean addRoomType(RoomTypeMaster roomType) {
		try {
			ps=con.prepareStatement("insert into roomtype(typeName,description) values(?,?)");
			ps.setString(1, roomType.getTypeName());
			ps.setString(2,roomType.getDescription());
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean UpdateRoomType(String typeName,String description,int roomTypeId) {
		try {
			ps=con.prepareStatement("update roomtype set typeName=?,description=? where roomTypeId=?");
			ps.setString(1, typeName);
			ps.setString(2,description);
			ps.setInt(3,roomTypeId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public int getRoomTypeId(String typeName) {
		try {
			ps=con.prepareStatement("select roomTypeId from roomtype where typeName=?");
			ps.setString(1, typeName);
			rs=ps.executeQuery();
					if(rs.next())
					{
						int roomTypeId=rs.getInt(1);
						return roomTypeId;
					}else {
					return 0;}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public boolean deleteRoomTypeId(int roomTypeId) {
		try {
			ps=con.prepareStatement("delete from roomtype where roomTypeId=?");
			ps.setInt(1, roomTypeId);
			int result=ps.executeUpdate();
			return result>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Optional<List<RoomTypeMaster>> getAllRoomType() {
		try {
			ps=con.prepareStatement("select * from roomtype");
			rs=ps.executeQuery();
			list.clear();
			while(rs.next())
			{
				String typeName=rs.getString("typeName");
				String description=rs.getString("description");
				 //stateName=rs.getString("stateName");
				list.add(new RoomTypeMaster(typeName,description));
			}
			return list.isEmpty() ? Optional.empty():Optional.of(list);
		} catch (Exception e) {
			System.out.println("Error is:"+e);
			return null;
		}
	}
}
