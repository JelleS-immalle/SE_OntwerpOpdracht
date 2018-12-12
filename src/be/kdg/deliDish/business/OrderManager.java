package be.kdg.deliDish.business;

import be.kdg.deliDish.business.domain.order.Order;
import be.kdg.deliDish.persistence.OrderMemoryRepository;
import be.kdg.deliDish.persistence.OrderRepository;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class OrderManager {
    private final OrderRepository orderRepository = new OrderMemoryRepository();

    public Collection<Order> getOrders(){return orderRepository.entities();}

    public void addOrder(Order order){orderRepository.put(order);}

    public int getLowestProductionManager(Order order){
        return order.getLowestProductionTime();
    }

    public LocalDateTime getTimeOrdered(Order order){
        return order.getTimeOrdered();
    }

    public int getAverageCourierDeliveryPoints(Order order){
        return order.getAverageCourierDeliveryPoints();
    }

    public List<Order> getThreeOldestOrders(Collection<Order> orders){
        List<Order> list = new ArrayList<>();
        for (int i = 0; i < 3 ; i++) {
            // todo
        }
        return list;
    }
}
