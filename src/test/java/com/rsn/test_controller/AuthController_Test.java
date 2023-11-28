package com.rsn.test_controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rsn.controller.AuthController;
import com.rsn.model.Employee;
import com.rsn.model.LoginCredentials;
import com.rsn.repository.EmployeeRepo;
import com.rsn.security.JWTUtil;

import net.bytebuddy.asm.Advice.This;

@RunWith(MockitoJUnitRunner.class)
@SuiteClasses(AuthController.class)
public class AuthController_Test {

	@InjectMocks
	private AuthController authController;

	@Mock
	private EmployeeRepo employeeRepo;;

	@Mock
	private JWTUtil jwtUtil;

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_registerHandler() {
		Employee employee1 = new Employee(1, "rushi", "nichit", "Nashik", "PP", "Single", "@gmail", "123",
				LocalDate.now(), null);
		String encodedPass = passwordEncoder.encode(employee1.getEmployeePassword());
		employee1.setEmployeePassword(encodedPass);
		when(employeeRepo.save(any())).thenReturn(employee1);
		String token = jwtUtil.generateToken(employee1.getEmployeeEmail());
		assertNotNull(authController.registerHandler(employee1));
	}

	@Test
	void test_loginHandler() {
		LoginCredentials body = new LoginCredentials("rushi@gmail.com", "123");
		UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
				body.getEmployeeEmail(), body.getEmployeePassword());
		authenticationManager.authenticate(authInputToken);
		String token = jwtUtil.generateToken(body.getEmployeeEmail());
		verify(authenticationManager, times(1)).authenticate(any());
		assertNotNull(authController.loginHandler(body));
	}

	@Test
	void test_loginHandlerExp() {
		 when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
         .thenThrow(new AuthenticationException("Invalid credentials") {});
        LoginCredentials loginCredentials = new LoginCredentials("test@example.com", "wrongPassword");
        RuntimeException exception = assertThrows(RuntimeException.class,
         () -> authController.loginHandler(loginCredentials));
        assertEquals("Invalid Login Credentials", exception.getMessage());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtil, never()).generateToken(anyString());;
	}
}
