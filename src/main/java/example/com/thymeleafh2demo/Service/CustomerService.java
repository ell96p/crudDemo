package example.com.thymeleafh2demo.Service;

import java.util.List;

import example.com.thymeleafh2demo.Entity.Customer;


public interface CustomerService {
	
	public List<Customer> findAll();
	public Customer findbyId(int theId);
	public void save (Customer theCustomer);
	public void deleteById(int theId);
	public List<Customer> searchBy(String theName);
	public List<Customer> sortByLastName();

}
