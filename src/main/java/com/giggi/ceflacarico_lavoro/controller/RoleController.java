package com.giggi.ceflacarico_lavoro.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Role;
import com.giggi.ceflacarico_lavoro.service.RoleService;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }
    // CRUD endpoints qui
}