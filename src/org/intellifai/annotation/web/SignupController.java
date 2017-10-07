/**
 * @(#)SignupController.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.intellifai.annotation.command.OrganizationCommand;
import org.intellifai.annotation.command.RoleCommand;
import org.intellifai.annotation.command.SignupCommand;
import org.intellifai.annotation.command.UserCommand;
import org.intellifai.annotation.model.Organization;
import org.intellifai.annotation.model.Role;
import org.intellifai.annotation.model.User;
import org.intellifai.annotation.service.OrganizationService;
import org.intellifai.annotation.service.RoleService;
import org.intellifai.annotation.service.UserService;
import org.intellifai.annotation.web.util.Notification;
import org.intellifai.annotation.web.util.Page;
import org.intellifai.annotation.web.util.SignupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 下午9:13:08
 * @version V2.0
 */
@Controller
public class SignupController {

	private SignupValidator signupValidator = new SignupValidator();
    
    @Autowired
    private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private OrganizationService orgService;
    
    @RequestMapping(value="user/unauthorized")
    public String unauthorized(Model model) {
    	return "unauthorized";
    }
    
    /**
     * implemented role inheritance
     * @param model
     * @param command
     * @return
     */
    @RequestMapping(value="user/signup",method= RequestMethod.GET)
    @RequiresPermissions("user:add")
    public String showSignupForm(Model model, @ModelAttribute SignupCommand command) {
    	//set hospitalCommandList
    	List<Organization> hospitalList = orgService.getAllOrganization(); 
    	List<OrganizationCommand> hospitalCommandList = new ArrayList<OrganizationCommand>();
    	for(Organization hospital:hospitalList){
    		OrganizationCommand hc = new OrganizationCommand();
    		hc.setId(hospital.getId());
    		hc.setName(hospital.getName());
    		hospitalCommandList.add(hc);
    	}
    	model.addAttribute("organizationCommandList", hospitalCommandList);
    	//suppose 5000 role is enough for a application
    	Page<Role> role = roleService.getPageRoles(0, 5000);
    	List<Role> allRoleList = role.getResultList();
    	
    	User currentUser = userService.getCurrentUser();
    	List<Role> currentRoleList = new ArrayList<Role>();
    	for (Role cr : currentUser.getRoles()) {
    		for (Role ar : allRoleList) {
				if( cr.getPermissions().containsAll(ar.getPermissions()) && !cr.getId().equals(ar)){
					currentRoleList.add(ar);
				}
			}
    		currentRoleList.add(cr);
		}
    	
    	Set<Role> set = new HashSet<Role>(currentRoleList);  
    	currentRoleList =  new ArrayList<Role>(set);
    	List<RoleCommand> roleCommandList = new ArrayList<RoleCommand>();
    	for (Role cRole : currentRoleList) {
    		RoleCommand rc = new RoleCommand();
    		rc.setId(cRole.getId());
    		rc.setName(cRole.getName());
    		rc.setDescription(cRole.getDescription());
    		roleCommandList.add(rc);
		}
    	model.addAttribute("roleCommandList", roleCommandList);
    	
        return "user/signup";
    }

    @RequestMapping(value="user/signup",method= RequestMethod.POST)
    @RequiresPermissions("user:add")
    public String showSignupForm(Model model, @ModelAttribute SignupCommand command, BindingResult errors) {
        signupValidator.validate(command, errors);

        if( errors.hasErrors() ) {
            return showSignupForm(model, command);
        }

        // Create the user      
        Long id = userService.createUser(command.getAccount(), command.getRealName(), command.getPassword(), 
        		command.getRoleId(), command.getBelongOrg());
        
        // Login the newly created user
        //SecurityUtils.getSubject().login(new UsernamePasswordToken(command.getUsername(), command.getPassword()));
        model.addAttribute("Notification",Notification.createCreateNotification());
        return "forward:/s/user/manageUsers";
    }
}

