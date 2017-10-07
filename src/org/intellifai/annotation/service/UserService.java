/**
 * @(#)UserService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service;

import java.util.List;

import org.intellifai.annotation.command.UserCommand;
import org.intellifai.annotation.model.User;
import org.intellifai.annotation.web.util.Page;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午10:42:10
 * @version V2.0
 */
public interface UserService {

	User getCurrentUser();
	
	int getUserByRoleId(Integer roleId);
	
	List<User> getAllUser();
    
    List<User> getUserByBelongOrg(Integer orgId);
    
    
    Long createUser(UserCommand command);

    Long createUser(String account, String realName, String password, String roleId, String belongOrg);

    void deleteUser(Long userId);


    User getUser(Long userId);


    void updateUser(User user);


    Page<User> getPageUsers(int currentPage,int pageSize);


    Page<User> searchPageUsers(int currentPage,int pageSize, UserCommand command);
}

