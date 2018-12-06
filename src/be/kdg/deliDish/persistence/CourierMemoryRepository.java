package be.kdg.deliDish.persistence;

import be.kdg.deliDish.business.domain.user.Courier;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CourierMemoryRepository implements CourierRepository {
    private Set<Courier> couriers = new HashSet<>();

    @Override
    public Collection<Courier> entities() {return couriers;}

    @Override
    public Boolean put(Courier value) {return couriers.add(value);}

    @Override
    public void update(Courier value) {
        Iterator<Courier> tempIterator = couriers.iterator();
        while(tempIterator.hasNext()){
            Courier tempCourier = tempIterator.next();
            // De equals(...) methode gaat nakijken of de voor- en achternaam gelijk zijn
            if(tempCourier.equals(value)){
                couriers.remove(tempCourier);
                couriers.add(value);
            }
        }
    }

    @Override
    //TODO  --> sorter parameter nodig?
    public Collection<Courier> findWhere(Predicate predicate, Comparator<Courier> sorter) {
        return null;
    }

    @Override
    public Collection<Courier> findWhere(Predicate predicate) {
        return couriers.stream()
                .filter(predicate::test)
                .collect(Collectors.toSet());
    }

    @Override
    public Courier findOneWhere(Predicate predicate) {
        return couriers.stream()
                .filter(predicate::test)
                .findFirst().get();
    }

    @Override
    //TODO --> via welk attribuut een waarde ophalen?
    public Courier get(int id) {
        return null;
    }
}
