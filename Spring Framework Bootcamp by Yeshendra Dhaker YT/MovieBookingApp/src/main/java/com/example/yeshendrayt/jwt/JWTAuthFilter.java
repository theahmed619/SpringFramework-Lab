package com.example.yeshendrayt.jwt;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.yeshendrayt.repository.UserRepo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {

	    final String authHeader = request.getHeader("Authorization");
	    final String jwtToken;
	    final String username;

	    // Fix here: proceed only if header present AND starts with "Bearer "
	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        filterChain.doFilter(request, response);
	        return;
	    }

	    jwtToken = authHeader.substring(7);
	    username = jwtService.extractUsername(jwtToken);

	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	        var userdetails = userRepo.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        if (jwtService.isTokenValid(jwtToken, userdetails)) {

	            List<SimpleGrantedAuthority> authorities = userdetails.getRoles().stream()
	                    .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
	                    .map(SimpleGrantedAuthority::new)
	                    .collect(Collectors.toList());

	            UsernamePasswordAuthenticationToken authToken =
	                    new UsernamePasswordAuthenticationToken(userdetails, null, authorities);

	            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	            SecurityContextHolder.getContext().setAuthentication(authToken);
	        }
	    }
	    filterChain.doFilter(request, response);
	}

}
