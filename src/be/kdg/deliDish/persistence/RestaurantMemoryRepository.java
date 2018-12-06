package be.kdg.deliDish.persistence;

import be.kdg.deliDish.business.domain.restaurant.Restaurant;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RestaurantMemoryRepository implements RestaurantRepository {
    private Set<Restaurant> restaurants = new HashSet<>();

    @Override
    public Collection<Restaurant> entities() {
        return restaurants;
    }

    @Override
    public Boolean put(Restaurant value) {
        return restaurants.add(value);
    }

    @Override
    public void update(Restaurant value) {
        Iterator<Restaurant> tempIterator = restaurants.iterator();
        while(tempIterator.hasNext()){
            Restaurant tempRestaurant = tempIterator.next();
            // De equals(...) methode gaat nakijken of de namen gelijk zijn
            if(tempRestaurant.equals(value)){
                restaurants.remove(tempRestaurant);
                restaurants.add(value);
            }
        }
    }

    @Override
    //TODO  --> sorter parameter nodig?
    public Collection<Restaurant> findWhere(Predicate predicate, Comparator<Restaurant> sorter) {
        return null;
    }

    @Override
    public Collection<Restaurant> findWhere(Predicate predicate) {
        return restaurants.stream()
                .filter(predicate::test)
                .collect(Collectors.toSet());
    }

    @Override
    public Restaurant findOneWhere(Predicate predicate) {
        return restaurants.stream()
                .filter(predicate::test)
                .findFirst().get();
    }

    @Override
    //TODO --> via welk attribuut een waarde ophalen?
    public Restaurant get(int id) {
        return null;
    }
}
