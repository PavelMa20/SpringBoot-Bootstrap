package com.user.userspring.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        boolean hasUserRole = false;
        boolean hasAdminRole = false;

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();// здесь приходят все роли при авторизации
        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals("ADMIN")) {
                hasAdminRole = true;
                break;
            } else if (role.getAuthority().equals("USER")) {
                hasUserRole = true;
                break;
            }else if(role.getAuthority().equals("ROLE_USER")){
                hasUserRole=true;
                break;
            }
        }
        if (hasUserRole) {
            redirectStrategy.sendRedirect( httpServletRequest,httpServletResponse,"/user");
        } else if (hasAdminRole) {
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/admin");
        } else {
            throw new IllegalStateException();
        }
    }


}