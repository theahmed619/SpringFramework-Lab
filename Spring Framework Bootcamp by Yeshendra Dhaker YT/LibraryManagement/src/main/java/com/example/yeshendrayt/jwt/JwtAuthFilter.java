package com.example.yeshendrayt.jwt;

import com.example.yeshendrayt.repository.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private final  JwtService jwtService;

    @Autowired
    private final UserRepo userRepo;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader=request.getHeader("Authorization");
        final String jwtToken;
        final  String username;

        //check if authorization header is present and starts with "Bearer"
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        // Extract JWT token from header
        jwtToken=authHeader.substring(7);
        username=jwtService.extractUsername(jwtToken);

        //check if we have username and no authentication exist yet
        if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null){

            //get the User details from database

            var userDetails=userRepo.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));

         //validate the token
         if(jwtService.isTokenValid(jwtToken, userDetails)){

             //create the authentication with user roles
             List<SimpleGrantedAuthority> authorities=userDetails.getRoles().stream()
                     .map(SimpleGrantedAuthority::new)
                     .collect(Collectors.toList());

             UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

             //set authentication details

             authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

             //update security context
             SecurityContextHolder.getContext().setAuthentication(authToken);

         }
        }
        filterChain.doFilter(request,response);
    }
}
