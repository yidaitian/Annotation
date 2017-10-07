/**
 * @(#)HibernatePermissionDAO.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.intellifai.annotation.command.PermissionCommand;
import org.intellifai.annotation.dao.PermissionDAO;
import org.intellifai.annotation.hibernate.BaseDaoImpl;
import org.intellifai.annotation.model.Permission;
import org.intellifai.annotation.web.util.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午9:51:52
 * @version V2.0
 */
@Repository
public class HibernatePermissionDAO extends BaseDaoImpl implements PermissionDAO {

	@Override
	public Permission findPermission(String permissionName){
		Assert.hasText(permissionName);
        String hql = "from Permission p where p.name = ?";
		return (Permission) get(hql, permissionName);
	}
	
	@Override
	public List<Permission> getAllPermissions(){
		return getTemplate().find("from Permission order by name");
	}
	
	@Override
    public Integer createPermission(Permission permission){
        return (Integer) getTemplate().save(permission);
    }


    @Override
    public void deletePermission(Integer permissionId){
    	Permission permission = getPermission(permissionId);
        if(permission != null)
            getTemplate().delete(permission);
    }


    @Override
    public Permission getPermission(Integer permissionId){
        return getTemplate().get(Permission.class, permissionId);
    }


    @Override
    public void updatePermission(Permission permission){
        getTemplate().update(permission);
    }


    @Override
    public Page<Permission> getPagePermissions(int currentPage,int pageSize){
        Page<Permission> pg = new Page<Permission>();
        StringBuffer strSql = new StringBuffer();
        strSql.append("from Permission order by id desc");
        ArrayList<Permission> permissionList=(ArrayList<Permission>) listByPage(strSql.toString(),currentPage,pageSize,null);
        Long totalSize = (Long)get("select count(*) from Permission");
        pg.setResultList(permissionList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalSize.intValue());
        return pg;
    }


    @Override
    public Page<Permission> searchPagePermissions(int currentPage,int pageSize, PermissionCommand command){
        Page<Permission> pg = new Page<Permission>();
        //Criteria query model
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Permission.class);
        if(command.getId() != null)
            detachedCriteria.add(Restrictions.eq("id", command.getId()));
        if(command.getParentId() != null)
            detachedCriteria.add(Restrictions.eq("parentId", command.getParentId()));
        if(command.getName() != null)
            detachedCriteria.add(Restrictions.like("name", command.getName().trim(), MatchMode.ANYWHERE));
        if(command.getElement() != null)
            detachedCriteria.add(Restrictions.like("element", command.getElement().trim(), MatchMode.ANYWHERE));
        if(command.getDescription() != null)
            detachedCriteria.add(Restrictions.like("description", command.getDescription().trim(), MatchMode.ANYWHERE));
        ArrayList<Permission> permissionList=(ArrayList<Permission>) getTemplate().findByCriteria(detachedCriteria, currentPage * pageSize, pageSize);


        Criteria criteria = detachedCriteria.getExecutableCriteria(getTemplate().getSessionFactory().openSession());
        int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();


        pg.setResultList(permissionList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalCount);
        return pg;
    }
}

