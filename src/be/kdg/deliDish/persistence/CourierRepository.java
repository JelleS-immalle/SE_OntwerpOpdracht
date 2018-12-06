package be.kdg.deliDish.persistence;

import be.kdg.deliDish.Infrastructure.Repository;
import be.kdg.deliDish.business.domain.user.Courier;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;

public interface CourierRepository extends Repository<Courier> {
    @Override
    Collection<Courier> entities();

    @Override
    Boolean put(Courier value);

    @Override
    void update(Courier value);

    @Override
    Collection<Courier> findWhere(Predicate predicate, Comparator<Courier> sorter);

    @Override
    Collection<Courier> findWhere(Predicate predicate);

    @Override
    Courier findOneWhere(Predicate predicate);

    @Override
    Courier get(int id);
}
