package com.nasr.supportingsystemproject.base.service;

import com.nasr.supportingsystemproject.base.domain.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E extends BaseEntity<ID,U>,U extends CharSequence, ID extends Serializable> {

    E saveOrUpdate(E e);

    void deleteById(ID id);

    E findById(ID id);

    List<E> findAll();
}
