package be.kdg.deliDish.persistence;

import be.kdg.deliDish.Infrastructure.Repository;
import be.kdg.deliDish.business.domain.order.Order;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;

public interface OrderRepository extends Repository<Order> {
    @Override
    Collection<Order> entities();

    @Override
    Boolean put(Order value);

    @Override
    void update(Order value);

    @Override
    Collection<Order> findWhere(Predicate predicate, Comparator<Order> sorter);

    @Override
    Collection<Order> findWhere(Predicate predicate);

    @Override
    Order findOneWhere(Predicate predicate);

    @Override
    Order get(int id);
}
