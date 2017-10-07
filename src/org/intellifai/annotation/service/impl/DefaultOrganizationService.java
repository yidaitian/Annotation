/**
 * @(#)DefaultOrganizationService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service.impl;

import java.util.Date;
import java.util.List;

import org.intellifai.annotation.command.OrganizationCommand;
import org.intellifai.annotation.dao.OrganizationDAO;
import org.intellifai.annotation.model.Organization;
import org.intellifai.annotation.model.User;
import org.intellifai.annotation.service.OrganizationService;
import org.intellifai.annotation.service.UserService;
import org.intellifai.annotation.web.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午10:22:44
 * @version V2.0
 */
@Transactional
@Service
public class DefaultOrganizationService implements OrganizationService {

	@Autowired
    private OrganizationDAO organizationDAO;
	@Autowired
	private UserService userService;


    @Override
    public Integer createOrganization(OrganizationCommand command){
        Organization organization = new Organization();
        organization.setId(command.getId());
        organization.setName(command.getName());
        organization.setAddress(command.getAddress());
        organization.setCreator(userService.getCurrentUser().getId());
        organization.setCreateTime(new Date());
        organization.setDeleted(0);
        return organizationDAO.createOrganization(organization);
    }


    @Override
    public boolean deleteOrganization(Integer organizationId){
    	List<User> userlist = userService.getUserByBelongOrg(organizationId);
		if(userlist != null)
			if(userlist.size() > 0)
				return false;
		try{
			organizationDAO.deleteOrganization(organizationId);
			return true;
		}catch(Exception e){
			return false;
		}
    }


    @Override
    public Organization getOrganization(Integer organizationId){
        return organizationDAO.getOrganization(organizationId);
    }

    @Override
    public List<Organization> getAllOrganization(){
    	Page<Organization> pg = getPageOrganizations(0, 1000);
		return pg.getResultList();
    }

    @Override
    public void updateOrganization(Organization organization){
    	organizationDAO.updateOrganization(organization);
    }


    @Override
    public Page<Organization> getPageOrganizations(int currentPage,int pageSize){
        return organizationDAO.getPageOrganizations(currentPage, pageSize);
    }


    @Override
    public Page<Organization> searchPageOrganizations(int currentPage,int pageSize, OrganizationCommand command){
        return organizationDAO.searchPageOrganizations(currentPage, pageSize, command);
    }
}

