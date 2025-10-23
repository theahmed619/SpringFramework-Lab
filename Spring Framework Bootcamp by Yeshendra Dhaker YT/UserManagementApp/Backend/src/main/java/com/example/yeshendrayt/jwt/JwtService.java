package com.example.yeshendrayt.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${jwt.secretKey}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private Long jwtExpiration;

	
	
	//extract user id
	public Long extractUserId(String jwtToken) {
		
		String userIdStr=extractClaim(jwtToken, claims -> claims.get("userId", String.class));
		
		return userIdStr !=null ? Long.parseLong(userIdStr) : null;
	}
	
	// method to extract username
	public String  extractUsername(String jwtToken) {
		
		return extractClaim(jwtToken, Claims::getSubject);
	}
	
	private <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver){
		
		final Claims claims=extractAllClaims(jwtToken);
		return claimsResolver.apply(claims);
		
	}
	
	private Claims extractAllClaims(String jwtToken) {
		
		return Jwts
				.parser()
				.verifyWith(getSignInKey())
				.build()
				.parseSignedClaims(jwtToken)
				.getPayload();
				
				
	}
	
	public SecretKey getSignInKey() {
	    byte[] keyBytes = Base64.getDecoder().decode(secretKey);
	    return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(User user) {
		return generateToken(new HashMap(), user);
		
		
	}
	
	public String generateToken(Map<String, Object> extraClaims, User user) {
		
		Map<String, Object> claims=new HashMap<>(extraClaims);
		claims.put("userId", user.getId().toString());
		
		return Jwts
				.builder()
				.claims(claims)
				.subject(user.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + jwtExpiration))
				.signWith(getSignInKey())
				.compact();
		
	}

	public boolean isTokenValid(String jwtToken, User user) {
		
		final Long UserIdFromToken=extractUserId(jwtToken);
		
		final Long userIdDetails=user.getId();
		return (UserIdFromToken !=null && UserIdFromToken.equals(userIdDetails)
				&& !isTokenExpired(jwtToken));
	}
	

	private boolean isTokenExpired(String jwtToken) {
		
		return extractExpiration(jwtToken).before(new Date());
	}
	
	private Date extractExpiration(String jwtToken) {
		
		return extractClaim(jwtToken, Claims::getExpiration);
	}
	
	
	
}
