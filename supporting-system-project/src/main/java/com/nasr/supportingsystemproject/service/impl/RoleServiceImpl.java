package com.nasr.supportingsystemproject.service.impl;

import com.nasr.supportingsystemproject.base.service.impl.BaseServiceImpl;
import com.nasr.supportingsystemproject.domain.Role;
import com.nasr.supportingsystemproject.exception.ModelNotFoundException;
import com.nasr.supportingsystemproject.repository.RoleRepository;
import com.nasr.supportingsystemproject.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long, String, RoleRepository> implements RoleService {

    public RoleServiceImpl(RoleRepository repository) {
        super(repository);
    }

    @Override
    public Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public void saveAll(Iterable<Role> roles) {
        repository.saveAll(roles);
    }

    @Override
    public Role getByRoleName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new ModelNotFoundException("dont find any role with name of : " + name));
    }
}
