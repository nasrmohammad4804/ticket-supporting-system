package com.nasr.supportingsystemproject.service;

import com.nasr.supportingsystemproject.base.service.BaseService;
import com.nasr.supportingsystemproject.domain.Role;

public interface RoleService extends BaseService<Role,String,Long> {
    Long count();

    void saveAll(Iterable<Role> roles);

    Role getByRoleName(String name);
}
