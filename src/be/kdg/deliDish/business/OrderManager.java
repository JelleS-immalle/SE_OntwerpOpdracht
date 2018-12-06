package be.kdg.deliDish.business;

import be.kdg.deliDish.business.domain.order.Order;
import be.kdg.deliDish.persistence.OrderMemoryRepository;
import be.kdg.deliDish.persistence.OrderRepository;

import java.util.Collection;

public class OrderManager {
    private final OrderRepository orderRepository = new OrderMemoryRepository();

    public Collection<Order> getOrders(){return orderRepository.entities();}

    public void addOrder(Order order){orderRepository.put(order);}
}
