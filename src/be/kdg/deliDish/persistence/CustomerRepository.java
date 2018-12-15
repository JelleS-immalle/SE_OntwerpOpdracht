package be.kdg.deliDish.persistence;

import be.kdg.deliDish.Infrastructure.Repository;
import be.kdg.deliDish.business.domain.user.Customer;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;

public interface CustomerRepository extends Repository<Customer> {
    @Override
    Collection<Customer> entities();

    @Override
    Boolean put(Customer value);

    @Override
    void update(Customer value);

    @Override
    Collection<Customer> findWhere(Predicate predicate, Comparator<Customer> sorter);

    @Override
    Collection<Customer> findWhere(Predicate predicate);

    @Override
    Customer findOneWhere(Predicate predicate);

}
