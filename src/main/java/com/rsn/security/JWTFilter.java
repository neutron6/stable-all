package com.rsn.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JWTUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
			String jwt = authHeader.substring(7);
			if (jwt == null || jwt.isBlank()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
			} else {
				try {
					String employeeEmail = jwtUtil.validateTokenAndRetrieveSubject(jwt);
					UserDetails userDetails = userDetailsService.loadUserByUsername(employeeEmail);
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
							employeeEmail, userDetails.getPassword(), userDetails.getAuthorities());
					if (SecurityContextHolder.getContext().getAuthentication() == null) {
						SecurityContextHolder.getContext().setAuthentication(authToken);
					}
				} catch (JWTVerificationException exc) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
				}
			}
		}

		filterChain.doFilter(request, response);
	}

}
