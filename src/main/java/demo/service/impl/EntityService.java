package demo.service.impl;

import demo.model.core.CoreEntity;
import demo.service.IEntityService;
import demo.service.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Extending this class provides basic CRUD operations
 * for entities which extends CoreEntity
 *
 * @author Erofeev Danil
 * @see demo.model.core.CoreEntity
 */
public abstract class EntityService<E extends CoreEntity> implements IEntityService<E> {

    @PersistenceContext(unitName = "employeePU")
    protected EntityManager em;

    protected Class<E> clazz;

    protected EntityService(Class<E> clazz) {
        this.clazz = clazz;
    }

    protected Class<E> getClazz() {
        return clazz;
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public E find(Long uid) {
        return em.find(getClazz(), uid);
    }

    @Override
    public List<E> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(clazz);
        Root<E> from = cq.from(getClazz());
        cq.select(from);
        cq.orderBy(cb.asc(from.get("id")));
        return em.createQuery(cq).getResultList();
    }

    @Transactional
    @Override
    public E saveOrUpdate(E entity) {
        if (null == entity.getId()) em.persist(entity);
        return em.merge(entity);
    }

    @Transactional
    @Override
    public void remove(E entity) {
        em.remove(getEntityManager().merge(entity));
    }
}