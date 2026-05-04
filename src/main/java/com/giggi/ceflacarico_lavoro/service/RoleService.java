package com.giggi.ceflacarico_lavoro.service;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Role;

public interface RoleService {
    Role save(Role role);

    Role update(Role role);

    void deleteById(Long id);

    List<Role> findAll();

    Role findById(Long id);
}