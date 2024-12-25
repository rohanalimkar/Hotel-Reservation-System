package Repository;

import java.util.List;
import java.util.Optional;

import Model.HotelMaster;
import Model.StateMaster;
import model.StateModel;

public interface StateRepository {

	public abstract boolean addState(StateMaster state);
	public abstract int isStatePresent(String stateName);
	public abstract Optional<List<HotelMaster>> StateWiseHotel(String stateName);
}
