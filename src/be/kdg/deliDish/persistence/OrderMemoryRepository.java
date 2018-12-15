package be.kdg.deliDish.persistence;

import be.kdg.deliDish.business.domain.order.Order;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OrderMemoryRepository implements OrderRepository {
    private Set<Order> orders = new HashSet<>();

    @Override
    public Collection<Order> entities() {
        return orders;
    }

    @Override
    public Boolean put(Order value) {
        return orders.add(value);
    }

    @Override
    public void update(Order value) {
        Iterator<Order> tempIterator = orders.iterator();
        while(tempIterator.hasNext()){
            Order tempOrder = tempIterator.next();
            // De equals(...) methode gaat nakijken of de orderId's gelijk zijn
            if(tempOrder.equals(value)){
                orders.remove(tempOrder);
                orders.add(value);
            }
        }
    }

    @Override
    public Collection<Order> findWhere(Predicate predicate, Comparator<Order> sorter) {
        return orders.stream()
                .filter(predicate::test)
                .sorted(sorter)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Order> findWhere(Predicate predicate) {
        return orders.stream()
                .filter(predicate::test)
                .collect(Collectors.toSet());
    }

    @Override
    public Order findOneWhere(Predicate predicate) {
        return orders.stream()
                .filter(predicate::test)
                .findFirst().get();
    }
}
