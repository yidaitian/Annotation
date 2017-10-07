/**
 * @(#)HomeController.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.web;

import javax.servlet.http.HttpServletRequest;

import org.intellifai.annotation.model.User;
import org.intellifai.annotation.service.UserService;
import org.intellifai.annotation.util.CookieUtils;
import org.intellifai.annotation.web.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-4 上午7:22:24
 * @version V2.0
 */
@Controller
public class HomeController {

	@Autowired
    private UserService userService;

    @RequestMapping("user/home")
    public void viewHome(Model model, HttpServletRequest request) {
    	//get the currentPage number from request
        int currentPage = Page.getCurrentPage(request);
        //get the pageSize number from cookies
        int pageSize = CookieUtils.getPageSize(request);
        Page<User> page = userService.getPageUsers(currentPage, pageSize);
        model.addAttribute("currentUser", userService.getCurrentUser());
        //put Page object to request scope
    	model.addAttribute("Page", page);
    }
}

