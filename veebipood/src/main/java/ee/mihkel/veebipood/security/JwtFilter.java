package ee.mihkel.veebipood.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // localhost:8080/supplier1?login=admin&principal=42&credentials=Maarika

//        if (request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith("Bearer ") && request.getHeader("Authorization").endsWith("123")) {
        if (request.getHeader("Authorization") != null && request.getHeader("Authorization").equals("Bearer 123")) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    request.getParameter("principal"), request.getParameter("credentials"), new ArrayList<>()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(request,response);
    }
}
