package be.kdg.deliDish.business;

import be.kdg.deliDish.business.domain.user.Customer;
import be.kdg.deliDish.persistence.CustomerMemoryRepository;
import be.kdg.deliDish.persistence.CustomerRepository;

import java.util.Collection;

public class CustomerManager {
    private final CustomerRepository customerRepository = new CustomerMemoryRepository();

    public Collection<Customer> getCustomers(){return customerRepository.entities();}

    public void addCustomer(Customer customer){customerRepository.put(customer);}
}
