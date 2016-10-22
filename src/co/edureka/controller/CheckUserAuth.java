package co.edureka.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class CheckUserAuth {
    static public String checkUserAndAuth() {
        String ret = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            ret = "UserName: " + userDetail.getUsername() + " Authority: " + userDetail.getAuthorities() + " ";
        }
        return ret;
    }
}
