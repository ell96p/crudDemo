package example.com.thymeleafh2demo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.com.thymeleafh2demo.Entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public List<Customer> findAllByOrderByLastNameAsc();
	public List<Customer> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String name, String lName);

}
