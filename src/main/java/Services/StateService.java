package Services;

import java.util.List;
import java.util.Optional;

import Model.HotelMaster;
import Model.StateMaster;

public interface StateService {
	public boolean addState(StateMaster state);
	public abstract int isStatePresent(String stateName);
	public Optional<List<HotelMaster>> StateWiseHotel(String stateName);
}
