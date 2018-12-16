package be.kdg.deliDish.business;

import be.kdg.deliDish.business.domain.order.Order;
import be.kdg.deliDish.business.domain.order.OrderEvent;
import be.kdg.deliDish.business.domain.order.OrderState;
import be.kdg.deliDish.business.domain.user.Courier;
import be.kdg.deliDish.business.domain.user.DeliveryPointEvent;
import be.kdg.deliDish.persistence.OrderMemoryRepository;
import be.kdg.deliDish.persistence.OrderRepository;
import be.kdg.foundation.contact.Position;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class OrderManager {
    private final OrderRepository orderRepository = new OrderMemoryRepository();

    public Collection<Order> getOrders(){return orderRepository.entities();}

    public Collection<Order> getOpenOrders(){
        Collection<Order> orders = orderRepository.entities();
        Collection<Order> tempOpenOrders = new HashSet<Order>();

        for(Order order : orders){
            OrderState state = order.getCurrentState();

            if(state == OrderState.ORDER_PLACED){
                tempOpenOrders.add(order);
            }
        }

        return tempOpenOrders;
    }

    public void addOrder(Order order){orderRepository.put(order);}

    public int getLowestProductionTime(Order order){
        return order.getLowestProductionTime();
    }

    public LocalDateTime getTimeOrdered(Order order){
        return order.getTimeOrdered();
    }

    public int getAverageCourierDeliveryPoints(Order order){
        return order.getAverageCourierDeliveryPoints();
    }

    public List<Order> getThreeOldestOrders(Collection<Order> orders){
        return orderRepository.entities().stream()
                .sorted(Comparator.comparing(Order::getOrderID))
                .limit(3)
                .collect(Collectors.toList());
    }

    public Position getRestaurantPosition(Order o){
        return o.getRestaurantPosition();
    }

    public Order selectDelivery(int orderId, Courier courier){
        Optional<Order> optOrder = orderRepository.entities()
                .stream()
                .filter(o -> o.getOrderID() == orderId).findFirst();

        Order order = optOrder.orElseGet(() -> null);

        if (order != null){
            order.setDeliverer(courier);
            OrderEvent orderEvent = new OrderEvent(LocalDateTime.now(), OrderState.COURIER_ASSIGNED, "");
            order.addEvent(orderEvent);
        }

        return order;
    }
}
