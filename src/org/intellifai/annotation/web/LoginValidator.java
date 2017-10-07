package org.intellifai.annotation.web;

import org.intellifai.annotation.command.LoginCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

    //参考网上的shiro单点登录，实现单点登录功能
    //http://www.360doc.com/content/12/0415/23/9318309_203895580.shtml
    //http://thinkgem.iteye.com/blog/2194142
/**
 * Validator for login.
 */
public class LoginValidator implements Validator{

	public boolean supports(Class aClass) {
        return LoginCommand.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.username.empty", "Please specify a username.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password.empty", "Please specify a password.");
    }
}
