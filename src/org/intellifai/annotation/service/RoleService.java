/**
 * @(#)RoleService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service;

import org.intellifai.annotation.command.RoleCommand;
import org.intellifai.annotation.model.Role;
import org.intellifai.annotation.web.util.Page;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午10:35:50
 * @version V2.0
 */
public interface RoleService {

	Integer createRole(RoleCommand command);


    void deleteRole(Integer roleId);


    Role getRole(Integer roleId);


    void updateRole(Role role);


    Page<Role> getPageRoles(int currentPage,int pageSize);


    Page<Role> searchPageRoles(int currentPage,int pageSize, RoleCommand command);
}

