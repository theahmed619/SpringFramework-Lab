package com.example.yeshendrayt.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secretKey}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private Long jwtexpiration;

	public String extractUsername(String jwtToken) {

		return extractClaim(jwtToken, Claims::getSubject);
	}

	private <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver) {

		final Claims claims = extractAllClaims(jwtToken);

		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String jwtToken) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwtToken).getBody();
	}

	public SecretKey getSignInKey() {
	    byte[] decodedKey = java.util.Base64.getDecoder().decode(secretKey);
	    return Keys.hmacShaKeyFor(decodedKey);
	}


	public String generateToken(UserDetails userDetails) {

		return generateToken(new HashMap<>(), userDetails);
	}

	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
	    return Jwts.builder()
	            .setClaims(extraClaims)
	            .setSubject(userDetails.getUsername())
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + jwtexpiration))
	            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
	            .compact();
	}

	public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
		final String username=extractUsername(jwtToken);
		return (userDetails.getUsername().equals(username) && !isTokenExpired(jwtToken));
	}
	
	private boolean isTokenExpired(String jwtToken) {
		
		return extractExpiration(jwtToken).before(new Date());
	}
	
	private Date extractExpiration(String jwtToken) {
		
		return extractClaim(jwtToken, Claims::getExpiration);
	}

}
