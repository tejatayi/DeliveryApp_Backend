package com.deliveryapplication.Fusion;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull  HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
// Log basic request details
        logger.info("Request Method: {}", request.getMethod());
        logger.info("Request URL: {}", request.getRequestURL().toString());

        // Log headers
        logger.info("Request Headers: ");
        request.getHeaderNames().asIterator().forEachRemaining(headerName ->
                logger.info("{}: {}", headerName, request.getHeader(headerName))
        );

        // Log parameters
        logger.info("Request Parameters: ");
        request.getParameterMap().forEach((key, value) ->
                logger.info("{}: {}", key, String.join(", ", value))
        );

        // Log the body for POST/PUT requests
        if ("POST".equals(request.getMethod()) || "PUT".equals(request.getMethod())) {
            StringBuilder body = new StringBuilder();
            request.getReader().lines().forEach(line -> body.append(line));
            logger.info("Request Body: {}", body.toString());
        }

        // Continue with the request processing
        filterChain.doFilter(request, response);
    }
}
