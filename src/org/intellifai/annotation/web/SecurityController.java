package org.intellifai.annotation.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;

import org.intellifai.annotation.command.ChangePwdCommand;
import org.intellifai.annotation.command.EditUserCommand;
import org.intellifai.annotation.command.LoginCommand;
import org.intellifai.annotation.model.User;
import org.intellifai.annotation.service.UserService;
import org.intellifai.annotation.web.LoginValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//http://blog.csdn.net/stone_miao/article/details/37958931

/**
 * Web MVC controller that handles security-related web requests, such as login and logout.
 */
@Controller
public class SecurityController {

	private LoginValidator loginValidator = new LoginValidator();
	@Autowired
	private UserService userService;

    @RequestMapping(value="user/login",method= RequestMethod.GET)
    public String showLoginForm(Model model, @ModelAttribute LoginCommand command) {
        return "user/login";
    }

    @RequestMapping(value="user/login",method= RequestMethod.POST)
    public String login(Model model, @ModelAttribute LoginCommand command, BindingResult errors) {
        loginValidator.validate(command, errors);

        if( errors.hasErrors() ) {
            return showLoginForm(model, command);
        }

        UsernamePasswordToken token = new UsernamePasswordToken(command.getUsername(), command.getPassword(), false);
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            errors.reject( "error.login.generic", "Invalid username or password.  Please try again." );
        }

        if( errors.hasErrors() ) {
            return showLoginForm(model, command);
        } else {
            //return "redirect:/s/system/home/waterfullPic";
            return "redirect:/s/user/home";
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/";
    }
    
    @RequestMapping(value="user/changePwd",method= RequestMethod.GET)
    public String showChangePwdForm(Model model, @ModelAttribute ChangePwdCommand command){
    	return "user/changePwd";
    }
    
    @RequestMapping(value="user/changePwd",method= RequestMethod.POST)
    public String changePwd(Model model, @ModelAttribute ChangePwdCommand command, BindingResult errors){
    	User currentUser = userService.getCurrentUser();
    	//confirm oldPwd
    	String oldPwd = new Sha256Hash(command.getPassword()).toHex();
    	
    	if(currentUser.getPassword().equals(oldPwd)){
    		currentUser.setPassword(new Sha256Hash(command.getNewPassword()).toHex());
    		userService.updateUser(currentUser);
    		return "user/home";
    	}else
    		return "user/changePwd";
    }
}
