package com.rsn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rsn.model.Employee;
import com.rsn.model.LoginCredentials;
import com.rsn.repository.EmployeeRepo;
import com.rsn.security.JWTUtil;

@RestController
@EnableTransactionManagement
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

	@Transactional
	@PostMapping("/register")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<String> registerHandler(@RequestBody Employee employee) {
		String encodedPass = passwordEncoder.encode(employee.getEmployeePassword());
		employee.setEmployeePassword(encodedPass);
		employee = employeeRepo.save(employee);
		String token = jwtUtil.generateToken(employee.getEmployeeEmail());
		return ResponseEntity.ok("** Account created SuccessFully ✔ **");
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginHandler(@RequestBody LoginCredentials body) {
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getEmployeeEmail(), body.getEmployeePassword());

			authManager.authenticate(authInputToken);

			String token = jwtUtil.generateToken(body.getEmployeeEmail());

			return ResponseEntity.ok(token);
		} catch (AuthenticationException authExc) {
			throw new RuntimeException("Invalid Login Credentials");
		}
	}

}
