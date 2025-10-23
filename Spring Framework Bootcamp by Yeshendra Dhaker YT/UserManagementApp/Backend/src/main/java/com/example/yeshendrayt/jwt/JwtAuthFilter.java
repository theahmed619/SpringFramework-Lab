package com.example.yeshendrayt.jwt;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.yeshendrayt.repository.UserRepo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepo userRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		Long userId = null;
		String jwtToken = null;
		final String authHeader = request.getHeader("Authorization");

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			jwtToken = authHeader.substring(7);
		}

		// if jwt token is null need to check the cookie
		if (jwtToken == null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("JWT".equals(cookie.getName())) {
						jwtToken = cookie.getValue();
						break;
					}
				}
			}

		}
		// if not token found
		if (jwtToken == null) {
			filterChain.doFilter(request, response);
			return;
		}

		// extract the user id

		userId = jwtService.extractUserId(jwtToken);
		if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// fetch user details

			var userDetails = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

			// validate token
			if (jwtService.isTokenValid(jwtToken, userDetails)) {
				List<SimpleGrantedAuthority> authorities = userDetails.getRoles().stream()
						.map(SimpleGrantedAuthority::new).collect(Collectors.toList());

				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, authorities);

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authToken);

			}
		}
		filterChain.doFilter(request, response);

	}

}
