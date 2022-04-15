package com.hjjang.backend.global.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class UserUtil {

    public static String getUserIdByToken() {
        User principal = (User)SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();

        return principal.getUsername();
    }

}
