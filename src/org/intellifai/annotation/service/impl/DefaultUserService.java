/**
 * @(#)DefaultUserService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.intellifai.annotation.command.UserCommand;
import org.intellifai.annotation.dao.OrganizationDAO;
import org.intellifai.annotation.dao.RoleDAO;
import org.intellifai.annotation.dao.UserDAO;
import org.intellifai.annotation.model.Organization;
import org.intellifai.annotation.model.Role;
import org.intellifai.annotation.model.User;
import org.intellifai.annotation.service.UserService;
import org.intellifai.annotation.web.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午10:44:50
 * @version V2.0
 */
@Transactional
@Service
public class DefaultUserService implements UserService {

	@Autowired
    private UserDAO userDAO;
    
    @Autowired
    private RoleDAO roleDAO;
    
    @Autowired
    private OrganizationDAO orgnizationDAO;


    @Override
    public User getCurrentUser(){
    	final Long currentUserId = (Long) SecurityUtils.getSubject().getPrincipal();
        if( currentUserId != null ) {
            return getUser(currentUserId);
        } else {
            return null;
        }
    }
	
    @Override
	public int getUserByRoleId(Integer roleId){
		return userDAO.getUserByRoleId(roleId);
	}
	
    @Override
	public List<User> getAllUser(){
		return userDAO.getAllUser();
	}
    
    @Override
    public List<User> getUserByBelongOrg(Integer orgId){
    	List<User> list = userDAO.getUserByBelongOrg(orgId);
    	return list;
    }
    
    @Override
    public Long createUser(String account, String realName, String password, String roleId, String belongOrg) {
    	User user = new User();
        //user.setId(command.getId());
        user.setAccount(account);
        user.setRealName(realName);
        user.setPassword(new Sha256Hash(password).toHex());
        Set<Role> roles = new HashSet<Role>();
        String[] rid = roleId.split(",");
        for (String string : rid) {
        	Role role = roleDAO.getRole(Integer.parseInt(string));
        	roles.add(role);
		}
        user.setRoles(roles);
        
        Organization orgnization = orgnizationDAO.getOrganization(Integer.parseInt(belongOrg));
        user.setBelongOrg(orgnization);
        
        return userDAO.createUser( user );
    }
    
    @Override
    public Long createUser(UserCommand command){
        User user = new User();
        //user.setId(command.getId());
        user.setAccount(command.getAccount());
        user.setRealName(command.getRealName());
        user.setPassword(new Sha256Hash(command.getPassword()).toHex());
        user.setBelongOrg(orgnizationDAO.getOrganization(command.getOrgId()));
        //user.setRoles(roles);
        //user.setOrgId(command.getOrgId());
        user.setCreator(getCurrentUser().getId());
        user.setCreateTime(new Date());
        user.setDeleted(0);
        return userDAO.createUser(user);
    }


    @Override
    public void deleteUser(Long userId){
    	userDAO.deleteUser(userId);
    }


    @Override
    public User getUser(Long userId){
        return userDAO.getUser(userId);
    }


    @Override
    public void updateUser(User user){
    	userDAO.updateUser(user);
    }


    @Override
    public Page<User> getPageUsers(int currentPage,int pageSize){
        return userDAO.getPageUsers(currentPage, pageSize);
    }


    @Override
    public Page<User> searchPageUsers(int currentPage,int pageSize, UserCommand command){
        return userDAO.searchPageUsers(currentPage, pageSize, command);
    }
}

