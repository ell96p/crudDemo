package example.com.thymeleafh2demo.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.com.thymeleafh2demo.Entity.Customer;
import example.com.thymeleafh2demo.Service.CustomerServiceImp;


@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	private CustomerServiceImp customerService;
	
	@Autowired
	public CustomerController(CustomerServiceImp theCustomerService) {
		customerService=theCustomerService;
	}
	
@GetMapping("/list")
	public String listCustomers (Model theModel) {
		
	List<Customer> theCustomer = customerService.findAll();
	
	theModel.addAttribute("customer",theCustomer);
	return "customers/list-customers";
		
	}
@GetMapping("/showFormForAdd")
public String showFormForAdd(Model theModel) {
	
	Customer theCustomer = new Customer();
	
	theModel.addAttribute("customer", theCustomer);
	return "customers/customers-form";
}
@PostMapping("/save")
public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
	
	customerService.save(theCustomer);
	return "redirect:/customers/list";
	
}

@GetMapping("/showFormForUpdate")
public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
	
	Customer theCustomer = customerService.findbyId(theId);
	theModel.addAttribute("customer", theCustomer);
	
	return "customers/customers-form";
}

@GetMapping("/delete")
public String delete(@RequestParam("customerId")int theId) {
	customerService.deleteById(theId);
	return "redirect:/customers/list";
	
}

@GetMapping("/search")
public String search(@RequestParam("customerName") String theName, Model theModel) {
	
	List<Customer> theCustomers = customerService.searchBy(theName);
	
	theModel.addAttribute("customer", theCustomers);
	
	return "/customers/list-customers";
	
	
}
@GetMapping("/sorted")
public String sortByLastName (Model theModel) {
	
List<Customer> theCustomer = customerService.sortByLastName();

theModel.addAttribute("customer", theCustomer);
return "customers/list-customers";
}
}