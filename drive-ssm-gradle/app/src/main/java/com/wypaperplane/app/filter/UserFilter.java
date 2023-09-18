package com.wypaperplane.app.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UserFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(UserFilter.class);

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("UserFilter---{}", request.getContextPath());
        logger.info("UserFilter---{}", request.getServletPath());
        logger.info("UserFilter---{}", request.getRequestURI());

        filterChain.doFilter(request, response);
    }
}
