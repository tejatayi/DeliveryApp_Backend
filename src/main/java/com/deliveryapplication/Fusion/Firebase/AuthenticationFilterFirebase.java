package com.deliveryapplication.Fusion.Firebase;


import com.deliveryapplication.Fusion.RequestLoggingFilter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class AuthenticationFilterFirebase extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    private static final String FIREBASE_AUTH_HEADER = "Authorization";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        logger.info("Entered  firebase authentication filter");
        String token = getFirebaseTokenFromHeader(request);
        logger.info("This is the token provided :"+token);
        if(token != null && validateFirebaseToken(token)){
            try {
                FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(token);  // this is to retrieve the authentication obj/ user details from firebase
                Authentication authentication = new FirebaseAuthentication(firebaseToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (FirebaseAuthException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            logger.info("Invalid token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

   private String getFirebaseTokenFromHeader(HttpServletRequest request)
    {
        String header = request.getHeader(FIREBASE_AUTH_HEADER);

        if(header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }

    private  boolean validateFirebaseToken(String token)    // validating the token with firebase
    {
        try{
            FirebaseAuth.getInstance().verifyIdToken(token);   // a quick check if the token is valid
            logger.info("Verified IdToken:"+FirebaseAuth.getInstance().verifyIdToken(token));
            return true;
        } catch (FirebaseAuthException e) {
            logger.info("invalid toke from request");
            return false;
        }
    }


}
