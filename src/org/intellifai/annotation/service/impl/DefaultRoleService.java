/**
 * @(#)DefaultRoleService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service.impl;

import org.intellifai.annotation.command.RoleCommand;
import org.intellifai.annotation.dao.RoleDAO;
import org.intellifai.annotation.model.Role;
import org.intellifai.annotation.service.RoleService;
import org.intellifai.annotation.web.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午10:38:06
 * @version V2.0
 */
@Transactional
@Service
public class DefaultRoleService implements RoleService {

	@Autowired
    private RoleDAO roleDAO;


    @Override
    public Integer createRole(RoleCommand command){
        Role role = new Role();
        role.setId(command.getId());
        role.setName(command.getName());
        role.setDescription(command.getDescription());
        return roleDAO.createRole(role);
    }


    @Override
    public void deleteRole(Integer roleId){
    	roleDAO.deleteRole(roleId);
    }


    @Override
    public Role getRole(Integer roleId){
        return roleDAO.getRole(roleId);
    }


    @Override
    public void updateRole(Role role){
    	roleDAO.updateRole(role);
    }


    @Override
    public Page<Role> getPageRoles(int currentPage,int pageSize){
        return roleDAO.getPageRoles(currentPage, pageSize);
    }


    @Override
    public Page<Role> searchPageRoles(int currentPage,int pageSize, RoleCommand command){
        return roleDAO.searchPageRoles(currentPage, pageSize, command);
    }
}

