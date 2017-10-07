/**
 * @(#)DefaultPermissionService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.intellifai.annotation.command.PermissionCommand;
import org.intellifai.annotation.dao.PermissionDAO;
import org.intellifai.annotation.dao.UserDAO;
import org.intellifai.annotation.model.Permission;
import org.intellifai.annotation.model.Role;
import org.intellifai.annotation.model.User;
import org.intellifai.annotation.service.PermissionService;
import org.intellifai.annotation.web.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午10:30:56
 * @version V2.0
 */
@Transactional
@Service
public class DefaultPermissionService implements PermissionService {

	@Autowired
    private UserDAO userDAO;
	
	@Autowired
    private PermissionDAO permissionDAO;

	@Override
	public Set<String> getCurrentUserPermissions(){
		final Long currentUserId = (Long) SecurityUtils.getSubject().getPrincipal();
        if( currentUserId != null ) {
       	 User user = userDAO.getUser(currentUserId);
       	 for( Role role : user.getRoles() ) {
       		 return role.getPermissions();
            }
        }
		return null;
	}

	@Override
	public void createPermission(String name, String description){
		Permission permission = new Permission(name);
        permission.setDescription(description);
        permissionDAO.createPermission( permission );
	}

	@Override
	public List<Permission> getAllPermissions(){
		return permissionDAO.getAllPermissions();
	}
    
    @Override
    public Integer createPermission(PermissionCommand command){
        Permission permission = new Permission();
        permission.setId(command.getId());
        permission.setParentId(command.getParentId());
        permission.setName(command.getName());
        permission.setElement(command.getElement());
        permission.setDescription(command.getDescription());
        return permissionDAO.createPermission(permission);
    }


    @Override
    public void deletePermission(Integer permissionId){
    	permissionDAO.deletePermission(permissionId);
    }


    @Override
    public Permission getPermission(Integer permissionId){
        return permissionDAO.getPermission(permissionId);
    }


    @Override
    public void updatePermission(Permission permission){
    	permissionDAO.updatePermission(permission);
    }


    @Override
    public Page<Permission> getPagePermissions(int currentPage,int pageSize){
        return permissionDAO.getPagePermissions(currentPage, pageSize);
    }


    @Override
    public Page<Permission> searchPagePermissions(int currentPage,int pageSize, PermissionCommand command){
        return permissionDAO.searchPagePermissions(currentPage, pageSize, command);
    }
}

