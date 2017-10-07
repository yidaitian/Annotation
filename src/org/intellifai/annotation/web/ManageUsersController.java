/**
 * @(#)ManageUsersController.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.intellifai.annotation.command.EditUserCommand;
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
import org.intellifai.annotation.util.CookieUtils;
import org.intellifai.annotation.web.util.Notification;
import org.intellifai.annotation.web.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 下午8:12:32
 * @version V2.0
 */
@Controller
public class ManageUsersController {

private static final Logger log = LoggerFactory.getLogger(ManageUsersController.class);
	
    private EditUserValidator editUserValidator = new EditUserValidator();

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrganizationService orgService;

    @RequestMapping("user/manageUsers")
    @RequiresPermissions("user:-")
    public void manageUsers(Model model, HttpServletRequest request) {
    	//get the currentPage number from request
        int currentPage = Page.getCurrentPage(request);
        //get the pageSize number from cookies
        int pageSize = CookieUtils.getPageSize(request);
        //put Page object to request scope
    	model.addAttribute("Page", userService.getPageUsers(currentPage, pageSize));
    	log.info("manageUsers.....");
    }

    @RequestMapping(value="user/searchUsers",method= RequestMethod.POST)
    @RequiresPermissions("user:view")
    public String searchUserForm(Model model,HttpServletRequest request, @ModelAttribute UserCommand command) {
    	
    	//get the currentPage number from request
        int currentPage = Page.getCurrentPage(request);
        //get the pageSize number from cookies
        int pageSize = CookieUtils.getPageSize(request);
        //put Page object to request scope
    	model.addAttribute("Page", userService.searchPageUsers(currentPage, pageSize, command));
    	return "user/manageUsers";
    }
    
    @RequestMapping(value="user/viewUser",method= RequestMethod.GET)
    @RequiresPermissions("user:view")
    public String showViewUserForm(Model model, @RequestParam Long userId, @ModelAttribute EditUserCommand command) {
    	
    	User user = userService.getUser(userId);
    	command.setId(userId);
    	command.setAccount(user.getAccount());
    	command.setRealName(user.getRealName());
    	//command.setPassword(user.getPassword());
    	command.setBelongOrg(user.getBelongOrg().getName());
    	
    	List<RoleCommand> originalCommandList = new ArrayList<RoleCommand>();
    	for (Role oRole : user.getRoles()) {
    		RoleCommand rc = new RoleCommand();
    		rc.setId(oRole.getId());
    		rc.setName(oRole.getName());
    		rc.setDescription(oRole.getDescription());
    		originalCommandList.add(rc);
		}
    	
    	//List<Attachment> alist = attachmentService.getAttachmentList(userId);
    	//model.addAttribute("AttachmentList", alist);
    	model.addAttribute("originalCommandList", originalCommandList);
    	return "user/viewUser";
    }
    
    @RequestMapping(value="user/editUser",method= RequestMethod.GET)
    @RequiresPermissions("user:edit")
    public String showEditUserForm(Model model, @RequestParam Long userId, @ModelAttribute EditUserCommand command) {
        User user = userService.getUser(userId);
        command.setId(userId);
        command.setAccount(user.getAccount());
        command.setRealName(user.getRealName());
        command.setBelongOrg(user.getBelongOrg().getId().toString());
      //set hospitalCommandList
    	List<Organization> hospitalList = orgService.getAllOrganization(); 
    	List<OrganizationCommand> hospitalCommandList = new ArrayList<OrganizationCommand>();
    	for(Organization hospital:hospitalList){
    		OrganizationCommand hc = new OrganizationCommand();
    		hc.setId(hospital.getId());
    		hc.setName(hospital.getName());
    		hospitalCommandList.add(hc);
    	}
    	model.addAttribute("orgCommandList", hospitalCommandList);
        
      //suppose 5000 role is enough for a application
    	Page<Role> role = roleService.getPageRoles(0, 5000);
    	//get all roles
    	List<Role> allRoleList = role.getResultList();
    	//get current user
    	User currentUser = userService.getCurrentUser();
    	//get current user's rolelist which contains current user's permission.
    	//current user cannot give other users the permission which not in himself's.
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
    	//set originalCommandList
    	List<RoleCommand> originalCommandList = new ArrayList<RoleCommand>();
    	for (Role oRole : user.getRoles()) {
    		RoleCommand rc = new RoleCommand();
    		rc.setId(oRole.getId());
    		rc.setName(oRole.getName());
    		rc.setDescription(oRole.getDescription());
    		originalCommandList.add(rc);
		}
    	//set roleCommandList
    	List<RoleCommand> roleCommandList = new ArrayList<RoleCommand>();
    	for (Role cRole : currentRoleList) {
    		RoleCommand rc = new RoleCommand();
    		rc.setId(cRole.getId());
    		rc.setName(cRole.getName());
    		rc.setDescription(cRole.getDescription());
    	    
    		roleCommandList.add(rc);
		}
    	//remove the original role form current user's rolelist
    	for (RoleCommand rc : originalCommandList) {
    		roleCommandList.remove(rc);
		}
    	model.addAttribute("originalCommandList", originalCommandList);
    	model.addAttribute("roleCommandList", roleCommandList);
        return "user/editUser";
    }

    @RequestMapping(value="user/editUser",method= RequestMethod.POST)
    @RequiresPermissions("user:edit")
    public String editUser(Model model, @RequestParam Long userId, @ModelAttribute EditUserCommand command,@ModelAttribute SignupCommand scommand, BindingResult errors) {
        editUserValidator.validate( command, errors );
        if( errors.hasErrors() ) {
            return "editUser";
        }
        User user = userService.getUser(userId);
        command.updateUser(user);
        //set belong Hospital
        Organization hospital = orgService.getOrganization(Integer.parseInt(command.getBelongOrg()));
        user.setBelongOrg(hospital);
        //set roles
        Set<Role> roles = new HashSet<Role>();
        String[] rid = scommand.getRoleId().split(",");
        for (String string : rid) {
        	Role role = roleService.getRole(Integer.parseInt(string));
        	roles.add(role);
		}
        
        user.setRoles(roles);
        userService.updateUser( user );
        
        model.addAttribute("Notification",Notification.createUpdateNotification());
        return "forward:/s/user/manageUsers";
    }

    @RequestMapping("user/deleteUser")
    @RequiresPermissions("user:delete")
    public String deleteUser(Model model,@RequestParam Long... userId) {
    	for (Long longId : userId) {
    		userService.deleteUser( longId );
		}
    	
        model.addAttribute("Notification",Notification.createDeleteNotification());
        return "forward:/s/user/manageUsers";
    }
}

