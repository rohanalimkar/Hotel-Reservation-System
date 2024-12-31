package Services;

import java.util.List;
import java.util.Optional;

import Model.HotelMaster;
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


	@Override
	public Optional<List<HotelMaster>> StateWiseHotel(String stateName) {
		
		return stateRepo.StateWiseHotel(stateName);
	}


	@Override
	public boolean isUpdateState(int Stateid, String UpdatedName) {
		// TODO Auto-generated method stub
		return stateRepo.isUpdateState(Stateid, UpdatedName);
	}


	@Override
	public boolean isDeleteState(int Stateid) {
		// TODO Auto-generated method stub
		return stateRepo.isDeleteState(Stateid);
	}


}
