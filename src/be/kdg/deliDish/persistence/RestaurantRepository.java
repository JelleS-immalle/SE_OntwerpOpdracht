package be.kdg.deliDish.persistence;

import be.kdg.deliDish.Infrastructure.Repository;
import be.kdg.deliDish.business.domain.restaurant.Restaurant;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;

public interface RestaurantRepository extends Repository<Restaurant> {
    @Override
    Collection<Restaurant> entities();

    @Override
    Boolean put(Restaurant value);

    @Override
    void update(Restaurant value);

    @Override
    Collection<Restaurant> findWhere(Predicate predicate, Comparator<Restaurant> sorter);

    @Override
    Collection<Restaurant> findWhere(Predicate predicate);

    @Override
    Restaurant findOneWhere(Predicate predicate);
}
