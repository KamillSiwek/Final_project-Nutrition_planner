package pl.coderslab.nutrition_planner.services;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseService<T, I extends Serializable> {

    T save(T dto);

    T update(T dto, I id);

    T find(I id);

    Boolean remove(I id);

    Collection<T> getAll();

    List<T> findAllByNameLike(String name);


}
