/**
 * @(#)EditUserValidator.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.web;

import java.util.regex.Pattern;

import org.apache.shiro.util.StringUtils;
import org.intellifai.annotation.command.EditUserCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 下午8:17:46
 * @version V2.0
 */
public class EditUserValidator {

	private static final String SIMPLE_EMAIL_REGEX = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}";

    public boolean supports(Class aClass) {
        return EditUserCommand.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
        EditUserCommand command = (EditUserCommand)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account", "error.account.empty", "Please specify a account.");
        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.email.empty", "Please specify an email address.");
        if( StringUtils.hasText( command.getEmail() ) && !Pattern.matches( SIMPLE_EMAIL_REGEX, command.getEmail().toUpperCase() ) ) {
            errors.rejectValue( "email", "error.email.invalid", "Please enter a valid email address." );
        }*/
    }
}

