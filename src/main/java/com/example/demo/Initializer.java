package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;

@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private RoleRepository roleRepository;
		
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	
		for (int i = 0; i < 1000; i++) {
			this.saveRole("Admin", StatusRole.ATIVO);
		}
		
		for (int i = 0; i < 1000; i++) {
			this.saveRole("Teste", StatusRole.INATIVO);
		}
		
		PageRequest pageable = PageRequest.of(10, 10);
		
		Page<Role> roles = this.roleRepository.findAll(pageable);
		
		for (Role role : roles) {
			System.out.println(role.getName());
		}
	}
	
	public void saveRole(String name, StatusRole status) {
		Role role = new Role(name, status);
		
		this.roleRepository.save(role);
	}
	
	
}
