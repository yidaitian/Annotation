/**
 * @(#)ManageRolesController.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.intellifai.annotation.command.RoleCommand;
import org.intellifai.annotation.model.Permission;
import org.intellifai.annotation.model.Role;
import org.intellifai.annotation.model.User;
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
public class ManageRolesController {

	private static final Logger log = LoggerFactory.getLogger(ManageRolesController.class);
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;
	
    /**
     * show role list
     * @param model
     * @param request
     */
    @RequestMapping("role/manageRoles")  
    @RequiresPermissions("role:-")
    //@RequiresPermissions("role:manage")
    public void manageRoles(Model model, HttpServletRequest request) {
    	//get the currentPage number from request
        int currentPage = Page.getCurrentPage(request);
        //get the pageSize number from cookies
        int pageSize = CookieUtils.getPageSize(request);
        //put Page object to request scope
    	model.addAttribute("Page", roleService.getPageRoles(currentPage, pageSize));
    	log.debug("opened manage role list page...");
    }
    
    /**
     * add new role
     * @param model
     * @param roleCommand
     * @return String
     */
    @RequestMapping("role/addRole")
    @RequiresPermissions("role:add")
    public String addRole(Model model, @ModelAttribute RoleCommand  roleCommand){
    	roleService.createRole(roleCommand);
    	//show operation result to client
    	Notification notification =  new Notification();
        notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
        notification.setTitle(ConfigUtil.getConfig().getString("msg.title.add"));
        notification.setMessage(ConfigUtil.getConfig().getString("msg.message.add"));
        model.addAttribute("Notification",notification);
    	return "forward:/s/role/manageRoles";
    }
    
	/**
	 * to implemented inherit permissions before show the add role form
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("role/showAddRoleForm")
    @RequiresPermissions("role:add")
    public String showAddRoleForm(Model model, HttpServletRequest request) {
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
	    				"'checked':"+"0" +
	    				"}";
	    		jsonStr +=tmp+","; 
    		}
		}
    	
    	//remove last comma in the end of the string
    	model.addAttribute("permissionTree", jsonStr.substring(0, jsonStr.length()-1));
    	log.debug("opened add role page...");
    	return "role/addRole";
    }
    
	@RequestMapping(value="role/editRole",method= RequestMethod.GET)
    @RequiresPermissions("role:edit")
    public String showEditRoleForm(Model model, @RequestParam Integer roleId, @ModelAttribute RoleCommand command) {

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
        return "role/editRole";
    }
	
	// status 1 means checked, 0 means unchecked
	private String checkStatus(Set<String> cSet,String pStr){
		if(cSet.contains(pStr)){
			return "'1'";
		}else{
			return "'0'";
		}
	}
	
	
	 @RequestMapping(value="role/editRole",method= RequestMethod.POST)
     @RequiresPermissions("role:edit")
     public String editRole(Model model, @RequestParam Integer roleId, @ModelAttribute RoleCommand roleCommand) {
		Role role = roleService.getRole( roleId );
		roleCommand.updateRole( role );
        roleService.updateRole( role );
    	Notification notification =  new Notification();
        notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
        notification.setTitle(ConfigUtil.getConfig().getString("msg.title.update"));
        notification.setMessage(ConfigUtil.getConfig().getString("msg.message.update"));
        model.addAttribute("Notification",notification);
        return "forward:/s/role/manageRoles";
     }
	
     @RequestMapping(value="role/viewRole",method= RequestMethod.GET)
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
     	return "role/viewRole";
     }	 
	 
    /**
     * delete some role
     * @param model
     * @param roleId
     * @return
     */
    @RequestMapping("role/deleteRole")
    @RequiresPermissions("role:delete")
    public String deleteRole(Model model,@RequestParam Integer... roleId) {
    	int count = 0;
    	for (Integer longId : roleId) {
    		//this role can`t delete if any user related with it 
    		if( userService.getUserByRoleId(longId)<=0 ){
    			count++;
    			roleService.deleteRole(longId);
    		}
		}
    	Notification notification =  new Notification();
    	if( count>0 ){
    		notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
            notification.setTitle(ConfigUtil.getConfig().getString("msg.title.delete"));
            notification.setMessage(ConfigUtil.getConfig().getString("msg.message.delete"));
    	}else{
    		notification.setClassType(ConfigUtil.getConfig().getString("css.class.tip"));
            notification.setTitle(ConfigUtil.getConfig().getString("msg.title.role.delete.tip"));
            notification.setMessage(ConfigUtil.getConfig().getString("msg.message.role.delete.tip"));
    	}
        model.addAttribute("Notification",notification);
        return "forward:/s/role/manageRoles";
    }
    
    @RequestMapping(value="role/searchRoles",method= RequestMethod.POST)
    @RequiresPermissions("role:view")
    public String searchRoleForm(Model model,HttpServletRequest request, @ModelAttribute RoleCommand command) {
    	
    	//get the currentPage number from request
        int currentPage = Page.getCurrentPage(request);
        //get the pageSize number from cookies
        int pageSize = CookieUtils.getPageSize(request);
        //put Page object to request scope
    	model.addAttribute("Page", roleService.searchPageRoles(
    			currentPage, pageSize, command));
    	return "role/manageRoles";
    }
}

