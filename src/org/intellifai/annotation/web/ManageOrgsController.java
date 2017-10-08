/**
 * @(#)ManageRolesController.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.intellifai.annotation.command.EditUserCommand;
import org.intellifai.annotation.command.OrganizationCommand;
import org.intellifai.annotation.command.RoleCommand;
import org.intellifai.annotation.command.SignupCommand;
import org.intellifai.annotation.model.Organization;
import org.intellifai.annotation.model.Permission;
import org.intellifai.annotation.model.Role;
import org.intellifai.annotation.model.User;
import org.intellifai.annotation.service.OrganizationService;
import org.intellifai.annotation.service.PermissionService;
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
 * @date 2017-10-5 下午5:38:40
 * @version V2.0
 */
@Controller
public class ManageOrgsController {

	private static final Logger log = LoggerFactory.getLogger(ManageOrgsController.class);

	@Autowired
	private OrganizationService orgService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;
	
    /**
     * show Org list
     * @param model
     * @param request
     */
    @RequestMapping("org/manageOrgs")  
    @RequiresPermissions("org:-")
    public void manageOrgs(Model model, HttpServletRequest request) {
    	//get the currentPage number from request
        int currentPage = Page.getCurrentPage(request);
        //get the pageSize number from cookies
        int pageSize = CookieUtils.getPageSize(request);
        //put Page object to request scope
    	model.addAttribute("Page", orgService.getPageOrganizations(currentPage, pageSize));
    	log.debug("opened manage Org list page...");
    }
    
    /**
     * add new Org
     * @param model
     * @param OrgCommand
     * @return String
     */
    @RequestMapping(value="org/addOrg",method= RequestMethod.GET)
    @RequiresPermissions("org:add")
    public String showAddOrgForm(Model model){
    	
    	OrganizationCommand orgCommand = new OrganizationCommand();
    	model.addAttribute("OrganizationCommand", orgCommand);
    	
    	return "org/addOrg";
    }
    
    @RequestMapping(value="org/addOrg",method= RequestMethod.POST)
    @RequiresPermissions("org:add")
    public String addOrg(Model model, @ModelAttribute OrganizationCommand orgCommand) {
    	
    	if(orgCommand.getName() != null && !orgCommand.getName().isEmpty() && !orgCommand.getName().equals("")) {
			
    		orgCommand.setCreator(userService.getCurrentUser().getId());
    		orgCommand.setCreateTime(new Date());
    		orgCommand.setDeleted(null);
			orgService.createOrganization(orgCommand);
		}
    	
    	return "forward:/s/org/manageOrgs";
    }
    
    
    
    
    
    @RequestMapping(value="org/editOrg",method= RequestMethod.GET)
    @RequiresPermissions("org:edit")
    public String showEditOrgForm(Model model, @RequestParam Integer OrgId, @ModelAttribute OrganizationCommand command) {
    	
    	//try change
    	
    	Organization org = orgService.getOrganization(OrgId);
    	command.setId(OrgId);
    	command.setName(org.getName());
    	command.setAddress(org.getAddress());
    	model.addAttribute("OrganizationCommand", command);
    	
    	return "org/editOrg";
    }
    
    @RequestMapping(value="org/editOrg",method= RequestMethod.POST)
    @RequiresPermissions("org:edit")
    public String editOrg(Model model, @RequestParam Integer orgId, @ModelAttribute OrganizationCommand command) {
    	
    	
    	
    	return "forward:/s/org/manageOrgs";
    }
    
    
    
    
	/*
    
    public String showEditOrgForm(Model model, @RequestParam Integer OrgId, @ModelAttribute OrgCommand command) {

		Role role = roleService.getRole(roleId);
		command.setId(roleId);
        command.setName(role.getName());
        command.setDescription(role.getDescription());
        command.setPermissions(role.getPermissions().toString().substring(1, role.getPermissions().toString().length()-1));
		
        Set<String> checkedRolePermissionSet = role.getPermissions();
        
        User currentUser = userService.getCurrentUser();
    	List<Permission> plist = permissionService.getAllPermissions();
    	
    	//stripped simple permission string to a set to compare and mixed with current permission
    	HashSet<String> allRolePermissionSet = new HashSet<String>();
    	for (Permission p : plist) {
    		allRolePermissionSet.add(p.getElement());
		}
    	
    	//get all of permissions from current role that related with current user
    	HashSet<String> currentRolePermissionSet = new HashSet<String>();
    	for (Role r : currentUser.getRoles()) {
    		currentRolePermissionSet.addAll(r.getPermissions());
		}
    	
    	//compare and get mixed permissions
    	allRolePermissionSet.retainAll(currentRolePermissionSet);
    	
    	String jsonStr = "";
    	for (Permission permission : plist) {
    		if( allRolePermissionSet.contains(permission.getElement()) || permission.getElement().contains(":-")){
	    		String tmp = "{'pid':"+"'"+permission.getParentId()+"'"+"," +
	    				"'input':"+"'"+permission.getElement()+"'"+"," +
	    				"'text':"+"'"+permission.getName()+"'"+"," +
	    				"'id':"+"'"+permission.getId()+"'"+"," +
	    				"'checked':"+ checkStatus(checkedRolePermissionSet,permission.getElement()) +
	    				"}";
	    		jsonStr +=tmp+","; 
    		}
		}
    	
    	//remove last comma in the end of the string
    	model.addAttribute("permissionTree", jsonStr.substring(0, jsonStr.length()-1));
        return "org/editOrg";
    }*/
	
	/*// status 1 means checked, 0 means unchecked
	private String checkStatus(Set<String> cSet,String pStr){
		if(cSet.contains(pStr)){
			return "'1'";
		}else{
			return "'0'";
		}
	}
	
	
	 @RequestMapping(value="org/editOrg",method= RequestMethod.POST)
     @RequiresPermissions("org:edit")
     public String editRole(Model model, @RequestParam Integer roleId, @ModelAttribute RoleCommand command) {
		Role role = roleService.getRole( roleId );
        command.updateRole( role );
        roleService.updateRole( role );
    	Notification notification =  new Notification();
        notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
        notification.setTitle(ConfigUtil.getConfig().getString("msg.title.update"));
        notification.setMessage(ConfigUtil.getConfig().getString("msg.message.update"));
        model.addAttribute("Notification",notification);
        return "forward:/s/org/manageOrgs";
     }
	
     @RequestMapping(value="org/viewOrg",method= RequestMethod.GET)
     @RequiresPermissions("role:view")
     public String showViewRoleForm(Model model, @RequestParam Integer roleId, @ModelAttribute RoleCommand command) {
     	
    	 Role role = roleService.getRole( roleId );
 		 command.setId(roleId);
         command.setName(role.getName());
         command.setDescription(role.getDescription());
         command.setPermissions(role.getPermissions().toString().substring(1, role.getPermissions().toString().length()-1));
 		
         Set<String> checkedRolePermissionSet = role.getPermissions();
         
         User currentUser = userService.getCurrentUser();
     	List<Permission> plist = permissionService.getAllPermissions();
     	
     	//stripped simple permission string to a set to compare and mixed with current permission
     	HashSet<String> allRolePermissionSet = new HashSet<String>();
     	for (Permission p : plist) {
     		allRolePermissionSet.add(p.getElement());
 		}
     	
     	//get all of permissions from current role that related with current user
     	HashSet<String> currentRolePermissionSet = new HashSet<String>();
     	for (Role r : currentUser.getRoles()) {
     		currentRolePermissionSet.addAll(r.getPermissions());
 		}
     	
     	//compare and get mixed permissions
     	allRolePermissionSet.retainAll(currentRolePermissionSet);
     	
     	String jsonStr = "";
     	for (Permission permission : plist) {
     		if( allRolePermissionSet.contains(permission.getElement()) || permission.getElement().contains(":-")){
 	    		String tmp = "{'pid':"+"'"+permission.getParentId()+"'"+"," +
 	    				"'input':"+"'"+permission.getElement()+"'"+"," +
 	    				"'text':"+"'"+permission.getName()+"'"+"," +
 	    				"'id':"+"'"+permission.getId()+"'"+"," +
 	    				"'checked':"+ checkStatus(checkedRolePermissionSet,permission.getElement()) +
 	    				"}";
 	    		jsonStr +=tmp+","; 
     		}
 		}
     	
     	//remove last comma in the end of the string
     	model.addAttribute("permissionTree", jsonStr.substring(0, jsonStr.length()-1));
     	return "org/viewOrg";
     }	*/ 
	 
    /**
     * delete some role
     * @param model
     * @param roleId
     * @return
     */
    @RequestMapping("org/deleteOrg")
    @RequiresPermissions("org:delete")
    public String deleteOrg(Model model,@RequestParam Integer... orgId) {
    	int count = 0;
    	for (Integer longId : orgId) {
    		//this org can`t delete if any user related with it 
    		if( userService.getUserByBelongOrg(longId).isEmpty() ){
    			count++;
    			orgService.deleteOrganization(longId);
    		}
		}
    	Notification notification =  new Notification();
    	if( count>0 ){
    		notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
            notification.setTitle(ConfigUtil.getConfig().getString("msg.title.delete"));
            notification.setMessage(ConfigUtil.getConfig().getString("msg.message.delete"));
    	}else{
    		notification.setClassType(ConfigUtil.getConfig().getString("css.class.tip"));
            notification.setTitle(ConfigUtil.getConfig().getString("msg.title.org.delete.tip"));
            notification.setMessage(ConfigUtil.getConfig().getString("msg.message.org.delete.tip"));
    	}
        model.addAttribute("Notification",notification);
        return "forward:/s/org/manageOrgs";
    }
    
    @RequestMapping(value="org/searchOrgs",method= RequestMethod.POST)
    @RequiresPermissions("org:view")
    public String searchRoleForm(Model model,HttpServletRequest request, @ModelAttribute OrganizationCommand command) {
    	
    	//get the currentPage number from request
        int currentPage = Page.getCurrentPage(request);
        //get the pageSize number from cookies
        int pageSize = CookieUtils.getPageSize(request);
        //put Page object to request scope
    	model.addAttribute("Page", orgService.searchPageOrganizations(
    			currentPage, pageSize, command));
    	return "org/manageOrgs";
    }
}

