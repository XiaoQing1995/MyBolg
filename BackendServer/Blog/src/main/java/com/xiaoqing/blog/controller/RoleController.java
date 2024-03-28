package com.xiaoqing.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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
public class RoleController {
	
	private final IRoleService roleService;

	@GetMapping
	public List<Role> getAllRoles() {
		return roleService.getAllRoles();
	}
}