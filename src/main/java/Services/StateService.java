package Services;

import Model.StateMaster;

public interface StateService {
	public boolean addState(StateMaster state);
	public abstract int isStatePresent(String stateName);
}
