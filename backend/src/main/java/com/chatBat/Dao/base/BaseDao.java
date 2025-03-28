package com.chatBat.Dao.base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public abstract class BaseDao {

    @PersistenceContext
    protected EntityManager entityManager;

    protected <T> List<T> nativeQuery(String sql, Class<T> resultClass, List<Object> params) {
        var query = entityManager.createNativeQuery(sql, resultClass);
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 1, params.get(i)); // SQL 是從 1 開始
        }
        return query.getResultList();
    }
    
    protected void persist(Object entity) {
        entityManager.persist(entity);
    }

    protected <T> T merge(T entity) {
        return entityManager.merge(entity);
    }

    protected void remove(Object entity) {
        entityManager.remove(entity);
    }
}

