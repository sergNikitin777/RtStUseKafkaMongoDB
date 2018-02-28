package com.kafkaiot.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

@NoRepositoryBean
public class BaseJpaRepositoryImpl<T, ID extends Serializable> extends QuerydslJpaRepository<T, ID> implements BaseJpaRepository<T, ID> {

    private final EntityManager em;

    public BaseJpaRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }

    public BaseJpaRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager, EntityPathResolver resolver) {
        super(entityInformation, entityManager, resolver);
        this.em = entityManager;
    }

    @Override
    public void detach(T entity) {
        em.detach(entity);
    }

    @Override
    public void detach(Iterable<T> entities) {
        entities.forEach(this::detach);
    }
}
