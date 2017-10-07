/**
 * @(#)OrganizationService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service;

import java.util.List;

import org.intellifai.annotation.command.OrganizationCommand;
import org.intellifai.annotation.model.Organization;
import org.intellifai.annotation.web.util.Page;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午10:19:51
 * @version V2.0
 */
public interface OrganizationService {

	List<Organization> getAllOrganization();
	
	Integer createOrganization(OrganizationCommand command);


	boolean deleteOrganization(Integer organizationId);


    Organization getOrganization(Integer organizationId);


    void updateOrganization(Organization organization);


    Page<Organization> getPageOrganizations(int currentPage,int pageSize);


    Page<Organization> searchPageOrganizations(int currentPage,int pageSize, OrganizationCommand command);
}

