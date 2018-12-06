package be.kdg.deliDish.business;

import be.kdg.deliDish.business.domain.user.Courier;
import be.kdg.deliDish.business.domain.user.DeliveryPointEvent;
import be.kdg.deliDish.persistence.CourierMemoryRepository;
import be.kdg.deliDish.persistence.CourierRepository;

import java.util.Collection;

public class CourierManager {
    private final CourierRepository courierRepository = new CourierMemoryRepository();

    public Collection<Courier> getCouriers(){return courierRepository.entities();}

    public void addCourier(Courier courier){courierRepository.put(courier);}

    public int calculateDeliveryPointsTotal(Courier c){
        int totalPoints = 0;
        // Wanneer de Courier geen DeliveryPointEvents heeft, wordt er 0 teruggegeven
        if(c.getDeliveryPointEvents() != null) {
            for (DeliveryPointEvent dpe : c.getDeliveryPointEvents()) {
                totalPoints += dpe.getPoints();
            }
        }
        return totalPoints;
    }
}
