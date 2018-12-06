package be.kdg.deliDish.persistence;

import be.kdg.deliDish.business.domain.user.Customer;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomerMemoryRepository implements CustomerRepository {
    private Set<Customer> customers = new HashSet<>();

    @Override
    public Collection<Customer> entities() {
        return customers;
    }

    @Override
    public Boolean put(Customer value) {
        return customers.add(value);
    }

    @Override
    public void update(Customer value) {
        Iterator<Customer> tempIterator = customers.iterator();
        while(tempIterator.hasNext()){
            Customer tempCustomer = tempIterator.next();
            // De equals(...) methode gaat nakijken of de voor- en achternaam gelijk zijn
            if(tempCustomer.equals(value)){
                customers.remove(tempCustomer);
                customers.add(value);
            }
        }
    }

    @Override
    //TODO  --> sorter parameter nodig?
    public Collection<Customer> findWhere(Predicate predicate, Comparator<Customer> sorter) {
        return null;
    }

    @Override
    public Collection<Customer> findWhere(Predicate predicate) {
        return customers.stream()
                .filter(predicate::test)
                .collect(Collectors.toSet());
    }

    @Override
    public Customer findOneWhere(Predicate predicate) {
        return customers.stream()
                .filter(predicate::test)
                .findFirst().get();
    }

    @Override
    //TODO --> via welk attribuut een waarde ophalen?
    public Customer get(int id) {
        return null;
    }
}
