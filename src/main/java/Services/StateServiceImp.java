package Services;

import Model.StateMaster;
import Repository.StateRepository;
import Repository.StateRepositoryImp;

public class StateServiceImp implements StateService {

	StateRepository stateRepo = new StateRepositoryImp();
	
	
	@Override
	public boolean addState(StateMaster state) {
		
		return stateRepo.addState(state);
	}


	@Override
	public int isStatePresent(String stateName) {
		
		return stateRepo.isStatePresent(stateName);
	}

}
