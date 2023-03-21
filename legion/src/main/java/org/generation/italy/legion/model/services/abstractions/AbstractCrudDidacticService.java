package org.generation.italy.legion.model.services.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AbstractCrudDidacticService<T> {
    List<T> findAllEntity() throws DataException;
    Optional<T> findEntityById(long id) throws DataException;
    T createEntity(T entity) throws DataException;
    void updateEntity(T entity) throws EntityNotFoundException, DataException;
    void deleteEntityById(long id) throws EntityNotFoundException, DataException;
}
