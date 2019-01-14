package pl.coderslab.finishingproject.services;

import java.io.Serializable;
import java.util.Collection;

public interface BaseService<T, I extends Serializable> {

    T save(T dto);

    T update(T dto, I id);

    T find(I id);

    Boolean remove(I id);

    Collection<T> getAll();

}
