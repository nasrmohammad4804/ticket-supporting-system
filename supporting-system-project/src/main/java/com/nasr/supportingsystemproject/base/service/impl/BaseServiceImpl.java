package com.nasr.supportingsystemproject.base.service.impl;

import com.nasr.supportingsystemproject.base.domain.BaseEntity;
import com.nasr.supportingsystemproject.base.service.BaseService;
import com.nasr.supportingsystemproject.exception.ModelNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
public abstract class BaseServiceImpl <E extends BaseEntity<ID,U>,ID extends Serializable, U extends CharSequence,R extends JpaRepository<E,ID>> implements BaseService<E,U,ID> {

    protected final R repository;

    public abstract Class<E> getEntityClass();

    @Override
    public E saveOrUpdate(E e) {
        return repository.save(e);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public E findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("dont find any "+getEntityClass().getSimpleName()+" with id : "+id));
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }
}
