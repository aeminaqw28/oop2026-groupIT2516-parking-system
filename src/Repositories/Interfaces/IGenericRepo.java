package Repositories.Interfaces;

import java.util.List;
import java.util.function.Predicate;

public interface IGenericRepo<T, ID> {
    T findByIdentifier(ID id);
    List<T> findAll();

    default List<T> findByCondition(Predicate<T> condition) {
        return findAll().stream()
                .filter(condition)
                .toList();
    }

    default T findFirst(Predicate<T> condition) {
        return findAll().stream()
                .filter(condition)
                .findFirst()
                .orElse(null);
    }
}
