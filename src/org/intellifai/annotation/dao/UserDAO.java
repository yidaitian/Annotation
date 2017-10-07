/**
 * @(#)UserDAO.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.dao;

import java.util.List;

import org.intellifai.annotation.command.UserCommand;
import org.intellifai.annotation.model.User;
import org.intellifai.annotation.web.util.Page;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午10:06:06
 * @version V2.0
 */
public interface UserDAO {

	User getUser(Long userId);

    int getUserByRoleId(Integer roleId);
    
    User findUser(String username);
    
    List<User> getAllUser();
    
    List<User> getUserByBelongOrg(Integer orglId);
    
    Long createUser(User user);


    void deleteUser(Long userId);


    void updateUser(User user);


    Page<User> getPageUsers(int currentPage,int pageSize);


    Page<User> searchPageUsers(int currentPage,int pageSize, UserCommand command);
}

