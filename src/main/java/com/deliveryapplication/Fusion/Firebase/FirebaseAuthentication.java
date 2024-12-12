package com.deliveryapplication.Fusion.Firebase;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;


public class FirebaseAuthentication extends AbstractAuthenticationToken {
    private final FirebaseToken firebaseToken;

    public FirebaseAuthentication(FirebaseToken firebaseToken) {
        super(null);
        this.firebaseToken = firebaseToken;
        setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return firebaseToken.getUid();
    }
    @Override
    public Object getCredentials() {
        return firebaseToken.getEmail();
    }

}
