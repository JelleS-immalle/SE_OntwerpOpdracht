package be.kdg.deliDish.business;

import be.kdg.deliDish.business.domain.restaurant.Restaurant;
import be.kdg.deliDish.persistence.RestaurantMemoryRepository;
import be.kdg.deliDish.persistence.RestaurantRepository;

import java.util.Collection;

public class RestaurantManager {
    private final RestaurantRepository restaurantRepository = new RestaurantMemoryRepository();

    public Collection<Restaurant> getRestaurants(){return restaurantRepository.entities();}

    public void addRestaurant(Restaurant restaurant){restaurantRepository.put(restaurant);}
}
