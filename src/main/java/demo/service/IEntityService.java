package demo.service;

import demo.model.core.CoreEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for CRUD operations
 *
 * @author Erofeev Danil
 */
public interface IEntityService<E extends CoreEntity> extends Serializable {

    E find(Long uid);

    List<E> findAll();

    E saveOrUpdate(E entity);

    void remove(E entity);

}
