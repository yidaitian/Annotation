package org.intellifai.annotation.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.intellifai.annotation.model.User;
import org.intellifai.annotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * A Spring MVC interceptor that adds the currentUser into the request as a request attribute
 * before the JSP is rendered.  This operation is assumed to be fast because the User should be
 * cached in the Hibernate second-level cache.
 */
@Component
public class CurrentUserInterceptor extends HandlerInterceptorAdapter{

	private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // Add the current user into the request
        User currentUser = userService.getCurrentUser();
        if( currentUser != null ) {
            httpServletRequest.setAttribute( "currentUser", currentUser );
        }
    }
}
