package be.kdg.deliDish.business.domain.order;

import be.kdg.deliDish.business.domain.user.Courier;
import be.kdg.deliDish.business.domain.user.Customer;
import be.kdg.foundation.contact.Adress;
import be.kdg.foundation.contact.Position;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {
    private int orderID;
    private List<OrderEvent> events = new ArrayList<>();
    private List<OrderLine> orderlines;
    private Adress deliveryAdress;
    private String deliveryInstructions;
    private Customer customer;
    private Courier deliverer;
    private int averageCourierDeliveryPoints;


    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public Order(List<OrderLine> orderlines, Adress deliveryAdress, String deliveryInstructions, Customer customer, int orderID, int averageDeliveryPoints) {
        this.orderID = orderID;
        this.orderlines = orderlines;
        this.deliveryAdress = deliveryAdress;
        this.deliveryInstructions = deliveryInstructions;
        this.customer = customer;
        this.averageCourierDeliveryPoints = averageDeliveryPoints;
    }

    public int getAverageCourierDeliveryPoints() {
        return averageCourierDeliveryPoints;
    }

    public LocalDateTime getTimeOrdered() {
        LocalDateTime orderedTime = null;

        for(OrderEvent orderEvent : events){
            OrderState state = orderEvent.getOrderState();

            if(state == OrderState.ORDER_PLACED){
                orderedTime = orderEvent.getTimestamp();
            }
        }

        return orderedTime;
    }

    public Position getRestaurantPosition(){
        OrderLine firstOrderLine = orderlines.get(0);
        return firstOrderLine.getPosition();
    }

    public int getLowestProductionTime(){
        int minProductionTime = Integer.MAX_VALUE;

        for (OrderLine ol : orderlines){
            int newProductionTime = ol.getProductionTime();

            if(minProductionTime > newProductionTime){
                minProductionTime = newProductionTime;
            }
        }
        return minProductionTime;
    }


    public Courier getDeliverer() {
        return deliverer;
    }

    public OrderState getCurrentState() {
        return events.get(events.size() - 1).getOrderState();
    }

    public void setDeliverer(Courier deliverer) {
        this.deliverer = deliverer;
    }

    public void addEvent(OrderEvent e) {
        events.add(e);
    }

    public int getOrderID() {
        return orderID;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return orderID == order.orderID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderID);
	}
}
