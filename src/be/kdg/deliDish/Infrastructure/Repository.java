package be.kdg.deliDish.Infrastructure;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;

public interface Repository <V> {
    Collection<V> entities();

    Boolean put(V value);

    void update(V value);

    Collection<V> findWhere(Predicate predicate, Comparator<V> sorter);

    Collection<V> findWhere(Predicate predicate);

    V findOneWhere(Predicate predicate);
}
