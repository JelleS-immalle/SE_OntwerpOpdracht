package be.kdg.deliDish.business.domain.user;


import be.kdg.deliDish.business.CourierManager;
import be.kdg.deliDish.business.OrderManager;
import be.kdg.deliDish.business.domain.order.Order;
import be.kdg.distanceAPI.DistanceCalculator;
import be.kdg.distanceAPI.Point;
import be.kdg.foundation.contact.ContactInfo;
import be.kdg.foundation.contact.Position;
import org.threeten.extra.Minutes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;


//TODO Je mag zaken toevoegen aan deze klasse. Bestaande attributen en methodes wijzigen doe je beter niet.
public class Courier extends User {

    private Position currentPosition;
    private boolean isAvailable;
    private List<DeliveryPointEvent> pointEvents = new ArrayList<>();
    private Partner partner;

    public Courier(String firstName, String lastName, ContactInfo contactInfo, Position currentPosition, Partner partner) {
        super(firstName, lastName, contactInfo);
        this.currentPosition = currentPosition;
        this.partner = partner;
        this.isAvailable = true;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public boolean switchAvailability() {
        isAvailable = !isAvailable;
        return isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void addPointEvent(DeliveryPointEvent e) {
        pointEvents.add(e);
    }

    public Collection<DeliveryPointEvent> getDeliveryPointEvents() {
        return pointEvents;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setAvailable(boolean available) {

        isAvailable = available;
    }

    public List<Order> geefBeschikbareLeveringen(){
        DistanceCalculator dc = new DistanceCalculator();
        CourierManager cmanager = new CourierManager();
        OrderManager omanager = new OrderManager();
        List<Order> relevanteOrders = new ArrayList<>();
        Collection<Order> openOrders = omanager.getOrders();
        String country = "Belgium";

        if (country.equals("Belgium")){
            for (Order o : openOrders){
                if (relevanteOrders.size() < 4){
                    Position restaurantPos = o.getRestaurantPosition();
                    Position currentPos = getCurrentPosition();
                    double afstand = dc.getDistance(new Point(currentPos.getLattitude(), currentPos.getLongitude()), new Point(restaurantPos.getLattitude(), restaurantPos.getLongitude()));
                    int lowestProductionTime = omanager.getLowestProductionManager(o);

                    if (afstand * 4 < lowestProductionTime){
                        LocalDateTime orderedTime = omanager.getTimeOrdered(o);
                        if (Minutes.between(LocalDateTime.now(), orderedTime).getAmount() < 5){
                            int averagePoints = omanager.getAverageCourierDeliveryPoints(o);
                            int courierPoints = pointEvents.get(0).getPoints();
                            if (courierPoints > averagePoints){
                                relevanteOrders.add(o);
                            }
                        } else {
                            relevanteOrders.add(o);
                        }
                    }
                }
            }
        } else {
            relevanteOrders = omanager.getThreeOldestOrders(openOrders);
        }
        return relevanteOrders;
    }
}
