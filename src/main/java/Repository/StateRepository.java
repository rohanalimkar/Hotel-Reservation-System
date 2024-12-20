package Repository;

import Model.StateMaster;

public interface StateRepository {

	public abstract boolean addState(StateMaster state);
	public abstract int isStatePresent(String stateName);
}
