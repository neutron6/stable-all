package com.rsn.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsn.model.Employee;
import com.rsn.model.LoginCredentials;
import com.rsn.repository.EmployeeRepo;
import com.rsn.security.JWTUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public Map<String, Object> registerHandler(@RequestBody Employee employee) {
		String encodedPass = passwordEncoder.encode(employee.getEmployeePassword());
		employee.setEmployeePassword(encodedPass);
		employee = employeeRepo.save(employee);
		String token = jwtUtil.generateToken(employee.getEmployeeEmail());
		return Collections.singletonMap("jwt-token", token);
	}

	@PostMapping("/login")
	public Map<String, Object> loginHandler(@RequestBody LoginCredentials body) {
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getEmployeeEmail(), body.getEmployeePassword());

			authManager.authenticate(authInputToken);

			String token = jwtUtil.generateToken(body.getEmployeeEmail());

			return Collections.singletonMap("jwt-token", token);
		} catch (AuthenticationException authExc) {
			throw new RuntimeException("Invalid Login Credentials");
		}
	}

}