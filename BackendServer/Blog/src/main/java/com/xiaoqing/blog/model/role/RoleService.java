package com.xiaoqing.blog.model.role;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService implements IRoleService {
	@Override
	public List<Role> getAllRoles() {
		return Arrays.asList(Role.values());
	}

}
