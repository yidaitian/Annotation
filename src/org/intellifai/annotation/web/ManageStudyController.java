/**
 * @(#)ManageUsersController.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.intellifai.annotation.command.*;
import org.intellifai.annotation.model.*;
import org.intellifai.annotation.service.*;
import org.intellifai.annotation.util.CookieUtils;
import org.intellifai.annotation.web.util.Notification;
import org.intellifai.annotation.web.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 下午8:12:32
 * @version V2.0
 */
@Controller
public class ManageStudyController {

private static final Logger log = LoggerFactory.getLogger(ManageStudyController.class);
	
    private EditUserValidator editUserValidator = new EditUserValidator();

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrganizationService orgService;
    
    @Autowired
    private BStudyService studyService;

    @RequestMapping("study/manageStudy")
    @RequiresPermissions("study:-")
    public void manageUsers(Model model, HttpServletRequest request) {
    	//get the currentPage number from request
        int currentPage = Page.getCurrentPage(request);
        //get the pageSize number from cookies
        int pageSize = CookieUtils.getPageSize(request);
        //put Page object to request scope
    	model.addAttribute("Page", studyService.getPageBStudys(currentPage, pageSize));
    	log.info("Go to manageStudy.....");
    }
}

