package example.com.thymeleafh2demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.com.thymeleafh2demo.DAO.CustomerRepository;
import example.com.thymeleafh2demo.Entity.Customer;


@Service
public class CustomerServiceImp implements CustomerService {

	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImp(CustomerRepository theCustomerRepository) {
		customerRepository = theCustomerRepository;
	}
	
	
	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findbyId(int theId) {
		Optional<Customer> result = customerRepository.findById(theId); 
		
		Customer theCustomer = null;
		if(result.isPresent()) {
			theCustomer = result.get();
			
		}else {
			throw new RuntimeException("Dod not found customer id " + theId);
		}
		
		return theCustomer;
	}

	@Override
	public void save(Customer theCustomer) {
		customerRepository.save(theCustomer);

	}

	@Override
	public void deleteById(int theId) {
		customerRepository.deleteById(theId);

	}

	@Override
	public List<Customer> searchBy(String theName) {
		
		List<Customer> results = null;
		
		if (theName != null && (theName.trim().length()> 0)) {
			results = customerRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName);
		}
		else {
			results = findAll();
		}
		
		return results;
	}


	@Override
	public List<Customer> sortByLastName() {
		// TODO Auto-generated method stub
		return customerRepository.findAllByOrderByLastNameAsc();
	}

}
