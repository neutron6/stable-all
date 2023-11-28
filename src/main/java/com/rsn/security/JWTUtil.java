package com.rsn.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class JWTUtil {

	@Value("${jwt_secret}")
	private String secret;

	public String generateToken(String employeeEmail) throws IllegalArgumentException, JWTCreationException {
		return JWT.create().withSubject("User Details").withClaim("employeeEmail", employeeEmail)
				.withIssuedAt(new Date()).withIssuer("simple-all/employeeData/rsn").sign(Algorithm.HMAC256(secret));
	}

	public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).withSubject("User Details")
				.withIssuer("simple-all/employeeData/rsn").build();
		DecodedJWT jwt = verifier.verify(token);
		return jwt.getClaim("employeeEmail").asString();
	}
}
