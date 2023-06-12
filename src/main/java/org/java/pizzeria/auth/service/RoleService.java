package org.java.pizzeria.auth.service;

import java.util.List;
import java.util.Optional;

import org.java.pizzeria.auth.pojo.Role;
import org.java.pizzeria.auth.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepo roleRepo;
	
	public List<Role> findAll() {
		
		return roleRepo.findAll();
	}
	
	public Optional<Role> findById(int id) {
		
		return roleRepo.findById(id);
		
	}
	
	public Role save(Role role) {
		
		return roleRepo.save(role);
		
	}
}
