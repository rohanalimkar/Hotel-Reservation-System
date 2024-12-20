package Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.StateMaster;
import model.StateModel;

public class StateRepositoryImp extends DBState implements StateRepository {
List<StateMaster>  list=new ArrayList<StateMaster>();
	@Override
	public boolean addState(StateMaster state) {

		try {
			ps=con.prepareStatement("insert into state values('0',?)");
			ps.setString(1,state.getStateName());
			int result=ps.executeUpdate();
			return result>0?true:false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	//----------------------check State is present or not
	@Override
	public int isStatePresent(String stateName) {
		StateMaster state=null;
		try {
			ps=con.prepareStatement("select stateId from state where stateName=?");
			ps.setString(1,stateName);
			rs=ps.executeQuery();
			if(rs.next())
			{
				int stateId=rs.getInt(stateName);
				state=new StateMaster();
				state.setStateId(stateId);
				return state.getStateId();
			}else {
	            // Return null if state fetching failed (state not found)
	            return 0;
			}
		} catch (Exception e) {
			return 0;
		}
		
	}
}
