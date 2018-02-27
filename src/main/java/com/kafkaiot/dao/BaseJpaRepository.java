package com.kafkaiot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import ru.diasoft.report.xbrl.common.database.jpa.QueryDslPredicateExecutorFix;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>, QueryDslPredicateExecutorFix<T> {

    void detach(T entity);

    void detach(Iterable<T> entities);
}
