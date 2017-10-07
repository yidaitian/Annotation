/**
 * @(#)PermissionService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service;

import java.util.List;
import java.util.Set;

import org.intellifai.annotation.command.PermissionCommand;
import org.intellifai.annotation.model.Permission;
import org.intellifai.annotation.web.util.Page;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午10:28:35
 * @version V2.0
 */
public interface PermissionService {
	
	Set<String> getCurrentUserPermissions();

    void createPermission(String name, String description);

    List<Permission> getAllPermissions();


	Integer createPermission(PermissionCommand command);


    void deletePermission(Integer permissionId);


    Permission getPermission(Integer permissionId);


    void updatePermission(Permission permission);


    Page<Permission> getPagePermissions(int currentPage,int pageSize);


    Page<Permission> searchPagePermissions(int currentPage,int pageSize, PermissionCommand command);
}

