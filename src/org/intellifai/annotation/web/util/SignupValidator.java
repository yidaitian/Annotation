/**
 * @(#)SignupValidator.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.web.util;

import java.util.regex.Pattern;

import org.apache.shiro.util.StringUtils;
import org.intellifai.annotation.command.SignupCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 下午9:14:20
 * @version V2.0
 */
public class SignupValidator  implements Validator{

	private static final String SIMPLE_EMAIL_REGEX = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}";

    public boolean supports(Class aClass) {
        return SignupCommand.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
        SignupCommand command = (SignupCommand)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account", "error.account.empty", "Please specify a account.");
        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.email.empty", "Please specify an email address.");
        if( StringUtils.hasText( command.getEmail() ) && !Pattern.matches( SIMPLE_EMAIL_REGEX, command.getEmail().toUpperCase() ) ) {
            errors.rejectValue( "email", "error.email.invalid", "Please enter a valid email address." );
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password.empty", "Please specify a password.");
        */
    }
}

