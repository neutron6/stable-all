package com.rsn.security;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.rsn.model.Employee;
import com.rsn.repository.EmployeeRepo;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String employeeEmail) throws UsernameNotFoundException {
		Optional<Employee> employeeRes = employeeRepo.findByEmployeeEmail(employeeEmail);
		if (employeeRes.isEmpty()) {
			throw new UsernameNotFoundException("could not find email" + employeeEmail);
		}
		Employee employee = employeeRes.get();
		return new org.springframework.security.core.userdetails.User(employeeEmail, employee.getEmployeePassword(),
				Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

	}

}
