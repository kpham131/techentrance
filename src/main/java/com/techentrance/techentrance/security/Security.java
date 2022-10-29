package com.techentrance.techentrance.security;

import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Security {
    private final LoginService loginService;

    public UUID authenticate(User user) {
        User foundUser = loginService.getUser(user.getEmail());
        if(foundUser==null) {
            return null;
        }
        if(foundUser.getPassword().equals(user.getPassword())) {
            UUID sessionId = UUID.randomUUID();
            foundUser.setSessionId(sessionId);
            loginService.saveUser(foundUser);
            return sessionId;
        }
        return null;
    }

    public Cookie validateSession(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("SessionId")){
                    // validate sessionID
                    if(validSession(UUID.fromString(cookie.getValue()))){
                        cookie.setMaxAge(10);
                        return cookie;
                    }

                }
            }
        }
        return null;
    }

    public boolean validSession(UUID sessionId) {
        User foundUser = loginService.getUserBySessionId(sessionId);
        if(foundUser==null) {
            return false;
        }
        return true;
    }
}