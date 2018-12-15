package be.kdg.deliDish.application;


import be.kdg.deliDish.business.CourierManager;
import be.kdg.deliDish.business.CustomerManager;
import be.kdg.deliDish.business.OrderManager;
import be.kdg.deliDish.business.RestaurantManager;
import be.kdg.deliDish.business.domain.order.Order;
import be.kdg.deliDish.business.domain.restaurant.Restaurant;
import be.kdg.deliDish.business.domain.user.Courier;
import be.kdg.deliDish.business.domain.user.Customer;
import be.kdg.deliDish.business.domain.user.DeliveryPointEvent;
import be.kdg.distanceAPI.DistanceCalculator;
import be.kdg.distanceAPI.Point;
import be.kdg.foundation.contact.Position;
import org.threeten.extra.Minutes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Jan Van Overveldt.
 */
// Dit is de controllerklasse van  Delidish. Je dient geen UI laag te implementeren. De methoden van de controller worden vanuit DeliveryControllerTest aangeroepen. Attributen mag je toevoegen indien dit nodig is voor een correct ontwerp.

public class DeliveryController {

    //Session Info
    private Courier appUser;
    private OrderManager orderManager = new OrderManager();
    private CourierManager courierManager = new CourierManager();
    private CustomerManager customerManager = new CustomerManager();
    private RestaurantManager restaurantManager = new RestaurantManager();


        /*
    DE METHODEN HIERONDER ZIJN NODIG VOOR DE INITIALISATIE VAN HET SYSTEEM.
    #######################################################################
     */

    public Courier getAppUser() {
        return appUser;
    }

    /**
     * Sets the sessionUser
     *
     * @param appUser
     */
    public void setAppUser(Courier appUser) {
        this.appUser = appUser;
    }

    // TODO (Week 3-4): Methode implementeren (wordt gebruikt om data beschikbaar te stellen)
    public void addOrder(Order o) {
        orderManager.addOrder(o);
    }

    // TODO (Week 3-4): Methode implementeren (wordt gebruikt om data beschikbaar te stellen)
    public void addCourier(Courier courier) {
        courierManager.addCourier(courier);
    }

    // TODO (Week 3-4): Methode implementeren (wordt gebruikt om data beschikbaar te stellen)
    public void addCustomer(Customer customer) {
        customerManager.addCustomer(customer);
    }

    // TODO (Week 3-4): Methode implementeren (wordt gebruikt om data beschikbaar te stellen)
    public void addResto(Restaurant restaurant) {
        restaurantManager.addRestaurant(restaurant);
    }

    // TODO (Week 3-4): Methode implementeren (wordt gebruikt om data beschikbaar te stellen
    public Collection<Courier> getCouriers() {
        return courierManager.getCouriers();
    }

    // TODO (Week 3-5): Methode implementeren (wordt gebruikt in een test)
    public int getDeliveryPointsTotal(Courier c) {
        return courierManager.calculateDeliveryPointsTotal(c);
    }

           /*
    (EINDE INITIALISATIEMETHODEN)
    #####################################################################################################
     */




        /*
    DE METHODEN HIERONDER ZIJN DEGENE DIE JE UIT DIENT TE WERKEN VOOR HET PROJECT (ZIE SSD EN CONTRACTEN).
    #####################################################################################################
     */

    // TODO (Week 3-4): Dit is DE methode die in bij de eerste oplevering moest uitgewerkt worden comform de interactiediagrammen die ook worden uitgewerkt.
    // TODO (Week 4-5): In de definitieve  moet de implementatie van deze methode aangepast worden (zie beschrijving)
    public Collection<Order> getAvailableDeliveries() {
        DistanceCalculator dc = new DistanceCalculator();
        List<Order> relevanteOrders = new ArrayList<>();
        Collection<Order> openOrders = orderManager.getOrders();
        String country = appUser.getContactInfo().getAdress().getCity().getCountry();

        if (country.equals("Belgium")){
            for (Order o : openOrders){
                if (relevanteOrders.size() < 4){
                    Position restaurantPos = o.getRestaurantPosition();
                    Position currentPos = appUser.getCurrentPosition();
                    double afstand = dc.getDistance(new Point(currentPos.getLattitude(), currentPos.getLongitude()), new Point(restaurantPos.getLattitude(), restaurantPos.getLongitude()));
                    int lowestProductionTime = orderManager.getLowestProductionManager(o);

                    if (afstand * 4 < lowestProductionTime){
                        LocalDateTime orderedTime = orderManager.getTimeOrdered(o);
                        if (Minutes.between(LocalDateTime.now(), orderedTime).getAmount() < 5){
                            int averagePoints = orderManager.getAverageCourierDeliveryPoints(o);
                            Collection<DeliveryPointEvent> pointEvents = appUser.getDeliveryPointEvents();
                            int points = 0;
                            for (DeliveryPointEvent dpe : pointEvents){
                                points += dpe.getPoints();
                            }
                            int courierPoints = points;
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
            relevanteOrders = orderManager.getThreeOldestOrders(openOrders);
        }
        return relevanteOrders;
    }

    // TODO (Week 4-5): Deze methode moet ontworpen worden voor de de definitieve oplevering. Code en diagrammen moet consistent zijn.
    public Order selectDelivery(int orderId) {
        return null;
    }
               /*
    (EINDE UIT TE WERKEN METHODEn)
    #####################################################################################################
     */

            /*
    DE METHODEN HIERONDER KAN JE OPTIONEEL NOG UITWERKEN.
    ######################################################

    // TODO (Week 4-5): Optioneel: Deze methode kan ontworpen worden voor de definitieve oplevering.
    public Order registerDeliveryPickup(int orderId) {
        return null;
    }

    // TODO (Week 4-5): Optioneel: Deze methode kan ontworpen worden voor de definitieve oplevering.
    public Order registerSuccesfullDelivery(int orderId) {
        return null;
    }
*/


}
