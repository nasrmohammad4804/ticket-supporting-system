package com.nasr.supportingsystemproject.domain.enumeration;

import com.nasr.supportingsystemproject.domain.Role;
import com.nasr.supportingsystemproject.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter

public enum UpdateCustomerRole {

    SUPPORT() {
        @Override
        public Role getRole(RoleService roleService) {
            return roleService.getByRoleName(roleNameConverter());
        }
    }, MANAGER() {
        @Override
        public Role getRole(RoleService roleService) {
            return roleService.getByRoleName(roleNameConverter());
        }

    };

    public abstract Role getRole(RoleService roleService);

    public String roleNameConverter() {
        return this.name().toLowerCase();
    }
}
