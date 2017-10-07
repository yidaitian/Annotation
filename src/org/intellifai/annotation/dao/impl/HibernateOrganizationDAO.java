/**
 * @(#)HibernateOrganizationDAO.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.dao.impl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.intellifai.annotation.command.OrganizationCommand;
import org.intellifai.annotation.dao.OrganizationDAO;
import org.intellifai.annotation.hibernate.BaseDaoImpl;
import org.intellifai.annotation.model.Organization;
import org.intellifai.annotation.web.util.Page;
import org.springframework.stereotype.Repository;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午9:42:34
 * @version V2.0
 */
@Repository
public class HibernateOrganizationDAO extends BaseDaoImpl implements OrganizationDAO {

	@Override
    public Integer createOrganization(Organization organization){
        return (Integer) getTemplate().save(organization);
    }


    @Override
    public void deleteOrganization(Integer organizationId){
    	Organization organization = getOrganization(organizationId);
        if(organization != null)
            getTemplate().delete(organization);
    }


    @Override
    public Organization getOrganization(Integer organizationId){
        return getTemplate().get(Organization.class, organizationId);
    }


    @Override
    public void updateOrganization(Organization organization){
        getTemplate().update(organization);
    }


    @Override
    public Page<Organization> getPageOrganizations(int currentPage,int pageSize){
        Page<Organization> pg = new Page<Organization>();
        StringBuffer strSql = new StringBuffer();
        strSql.append("from Organization order by id desc");
        ArrayList<Organization> organizationList=(ArrayList<Organization>) listByPage(strSql.toString(),currentPage,pageSize,null);
        Long totalSize = (Long)get("select count(*) from Organization");
        pg.setResultList(organizationList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalSize.intValue());
        return pg;
    }


    @Override
    public Page<Organization> searchPageOrganizations(int currentPage,int pageSize, OrganizationCommand command){
        Page<Organization> pg = new Page<Organization>();
        //Criteria query model
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Organization.class);
        if(command.getId() != null)
            detachedCriteria.add(Restrictions.eq("id", command.getId()));
        if(command.getName() != null)
            detachedCriteria.add(Restrictions.like("name", command.getName().trim(), MatchMode.ANYWHERE));
        if(command.getAddress() != null)
            detachedCriteria.add(Restrictions.like("address", command.getAddress().trim(), MatchMode.ANYWHERE));
        if(command.getCreator() != null)
            detachedCriteria.add(Restrictions.eq("creator", command.getCreator()));
        if(command.getCreateTime() != null)
            detachedCriteria.add(Restrictions.eq("createTime", command.getCreateTime()));
        if(command.getDeleted() != null)
            detachedCriteria.add(Restrictions.eq("deleted", command.getDeleted()));
        if(command.getDeletor() != null)
            detachedCriteria.add(Restrictions.like("deletor", command.getDeletor().trim(), MatchMode.ANYWHERE));
        ArrayList<Organization> organizationList=(ArrayList<Organization>) getTemplate().findByCriteria(detachedCriteria, currentPage * pageSize, pageSize);


        Criteria criteria = detachedCriteria.getExecutableCriteria(getTemplate().getSessionFactory().openSession());
        int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();


        pg.setResultList(organizationList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalCount);
        return pg;
    }
}

