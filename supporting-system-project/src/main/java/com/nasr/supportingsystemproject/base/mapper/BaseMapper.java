package com.nasr.supportingsystemproject.base.mapper;

import java.util.List;

public interface BaseMapper <E,D>{

    List<E> convertDtosToEntities(List<D> entities);
    List<D> convertEntitiesToDtos(List<E> entities);
    D convertEntityToDto(E entity);
    E convertDtoToEntity(D dto);
}
