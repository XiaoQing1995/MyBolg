package com.xiaoqing.blog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoqing.blog.model.role.IRoleService;
import com.xiaoqing.blog.model.role.Role;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
//@CrossOrigin("https://happy-meadow-001df7a00.5.azurestaticapps.net")
@CrossOrigin("http://localhost:5173")
public class RoleController {

	private final IRoleService roleService;

	@GetMapping
	public List<Role> getAllRoles() {
		return roleService.getAllRoles();
	}
}