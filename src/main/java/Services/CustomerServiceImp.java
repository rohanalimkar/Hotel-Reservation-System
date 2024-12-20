package Services;

import Model.CustomerMaster;
import Repository.CustomerRepository;
import Repository.CustomerRepositoryImp;

public class CustomerServiceImp implements CustomerService {

	CustomerRepository customerRepo=new CustomerRepositoryImp();
	@Override
	public boolean isaddNewCustomer(CustomerMaster customer) {
		
		return customerRepo.isaddNewCustomer(customer);
	}
	@Override
	public boolean validateLogin(String email, String password) {
		
		return customerRepo.validateLogin(email, password);
	}
	@Override
	public String getFirstName(String customerEmail) {
		
		return customerRepo.getFirstName(customerEmail);
	}

}
