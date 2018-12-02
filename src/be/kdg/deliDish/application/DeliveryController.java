package be.kdg.deliDish.application;


import be.kdg.deliDish.business.domain.order.Order;
import be.kdg.deliDish.business.domain.restaurant.Restaurant;
import be.kdg.deliDish.business.domain.user.Courier;
import be.kdg.deliDish.business.domain.user.Customer;
import be.kdg.deliDish.business.domain.user.DeliveryPointEvent;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jan Van Overveldt.
 */
// Dit is de controllerklasse van  Delidish. Je dient geen UI laag te implementeren. De methoden van de controller worden vanuit DeliveryControllerTest aangeroepen. Attributen mag je toevoegen indien dit nodig is voor een correct ontwerp.

public class DeliveryController {

    //Session Info
    private Courier appUser;
    private Collection<Order> orders;
    private Collection<Courier> couriers;
    private Collection<Customer> customers;
    private Collection<Restaurant> restaurants;


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
        // Als er nog geen collectie bestaat, er één aanmaken
        if(this.orders == null){
            this.orders = new ArrayList<>();
        }
        // Vanaf er een collectie is, het Order toevoegen
        this.orders.add(o);
    }

    // TODO (Week 3-4): Methode implementeren (wordt gebruikt om data beschikbaar te stellen)
    public void addCourier(Courier courier) {
        // Als er nog geen collectie bestaat, er één aanmaken
        if(this.couriers == null){
            this.couriers = new ArrayList<>();
        }
        // Vanaf er een collectie is, de Courier toevoegen
        this.couriers.add(courier);
    }

    // TODO (Week 3-4): Methode implementeren (wordt gebruikt om data beschikbaar te stellen)
    public void addCustomer(Customer customer) {
        // Als er nog geen collectie bestaat, er één aanmaken
        if(this.customers == null){
            this.customers = new ArrayList<>();
        }
        // Vanaf er een collectie is, de Customer toevoegen
        this.customers.add(customer);
    }

    // TODO (Week 3-4): Methode implementeren (wordt gebruikt om data beschikbaar te stellen)
    public void addResto(Restaurant restaurant) {
        // Als er nog geen collectie bestaat, er één aanmaken
        if(this.restaurants == null){
            this.restaurants = new ArrayList<>();
        }
        // Vanaf er een collectie is, het Restaurant toevoegen
        this.restaurants.add(restaurant);
    }

    // TODO (Week 3-4): Methode implementeren (wordt gebruikt om data beschikbaar te stellen
    public Collection<Courier> getCouriers() {
        // De collectie van Courier objecten returnen
        return this.couriers;
    }

    // TODO (Week 3-5): Methode implementeren (wordt gebruikt in een test)
    public int getDeliveryPointsTotal(Courier c) {
        int totalPoints = 0;
        // Wanneer de Courier geen DeliveryPointEvents heeft, wordt er 0 teruggegeven
        if(c.getDeliveryPointEvents() != null) {
            for (DeliveryPointEvent dpe : c.getDeliveryPointEvents()) {
                totalPoints += dpe.getPoints();
            }
        }
        return totalPoints;
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
        return null;
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
