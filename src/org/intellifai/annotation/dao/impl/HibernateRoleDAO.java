/**
 * @(#)HibernateRoleDAO.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.dao.impl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.intellifai.annotation.command.RoleCommand;
import org.intellifai.annotation.dao.RoleDAO;
import org.intellifai.annotation.hibernate.BaseDaoImpl;
import org.intellifai.annotation.model.Role;
import org.intellifai.annotation.web.util.Page;
import org.springframework.stereotype.Repository;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午10:01:02
 * @version V2.0
 */
@Repository
public class HibernateRoleDAO extends BaseDaoImpl implements RoleDAO {

	@Override
    public Integer createRole(Role role){
        return (Integer) getTemplate().save(role);
    }


    @Override
    public void deleteRole(Integer roleId){
    	Role role = getRole(roleId);
        if(role != null)
            getTemplate().delete(role);
    }


    @Override
    public Role getRole(Integer roleId){
        return getTemplate().get(Role.class, roleId);
    }


    @Override
    public void updateRole(Role role){
        getTemplate().update(role);
    }


    @Override
    public Page<Role> getPageRoles(int currentPage,int pageSize){
        Page<Role> pg = new Page<Role>();
        StringBuffer strSql = new StringBuffer();
        strSql.append("from Role order by id desc");
        ArrayList<Role> roleList=(ArrayList<Role>) listByPage(strSql.toString(),currentPage,pageSize,null);
        Long totalSize = (Long)get("select count(*) from Role");
        pg.setResultList(roleList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalSize.intValue());
        return pg;
    }


    @Override
    public Page<Role> searchPageRoles(int currentPage,int pageSize, RoleCommand command){
        Page<Role> pg = new Page<Role>();
        //Criteria query model
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Role.class);
        if(command.getId() != null)
            detachedCriteria.add(Restrictions.eq("id", command.getId()));
        if(command.getName() != null)
            detachedCriteria.add(Restrictions.like("name", command.getName().trim(), MatchMode.ANYWHERE));
        if(command.getDescription() != null)
            detachedCriteria.add(Restrictions.like("description", command.getDescription().trim(), MatchMode.ANYWHERE));
        ArrayList<Role> roleList=(ArrayList<Role>) getTemplate().findByCriteria(detachedCriteria, currentPage * pageSize, pageSize);


        Criteria criteria = detachedCriteria.getExecutableCriteria(getTemplate().getSessionFactory().openSession());
        int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();


        pg.setResultList(roleList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalCount);
        return pg;
    }
}

