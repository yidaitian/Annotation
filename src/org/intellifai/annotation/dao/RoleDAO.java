/**
 * @(#)RoleDAO.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.dao;

import org.intellifai.annotation.command.RoleCommand;
import org.intellifai.annotation.model.Role;
import org.intellifai.annotation.web.util.Page;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午9:59:01
 * @version V2.0
 */
public interface RoleDAO {

	Integer createRole(Role role);


    void deleteRole(Integer roleId);


    Role getRole(Integer roleId);


    void updateRole(Role role);


    Page<Role> getPageRoles(int currentPage,int pageSize);


    Page<Role> searchPageRoles(int currentPage,int pageSize, RoleCommand command);
}

