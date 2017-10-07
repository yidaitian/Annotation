/**
 * @(#)HibernateUserDAO.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.intellifai.annotation.command.UserCommand;
import org.intellifai.annotation.dao.UserDAO;
import org.intellifai.annotation.hibernate.BaseDaoImpl;
import org.intellifai.annotation.model.User;
import org.intellifai.annotation.web.util.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午10:09:15
 * @version V2.0
 */
@Repository
public class HibernateUserDAO extends BaseDaoImpl implements UserDAO {

	@Override
	public User getUser(Long userId){
		return getTemplate().get(User.class, userId);
	}

	@Override
    public int getUserByRoleId(Integer roleId){
		String sql = "select count(*) from users_roles where roles_id=?";
		return countBySQL(sql, roleId);
    }
    
    @Override
    public User findUser(String username){
    	Assert.hasText(username);
        String hql = "from User u where u.account = ?";
		return (User) get(hql, username);
    }
    
    @Override
    public List<User> getAllUser(){
    	StringBuffer strSql = new StringBuffer();
		strSql.append("from User order by id desc");
		return getTemplate().find(strSql.toString());
    }
    
    @Override
    public List<User> getUserByBelongOrg(Integer orglId){
    	StringBuffer strSql = new StringBuffer();
		strSql.append("from User u where u.belongOrg.id = ");
		strSql.append(orglId.toString());
		return getTemplate().find(strSql.toString());
    }

    @Override
    public Long createUser(User user){
        return (Long) getTemplate().save(user);
    }


    @Override
    public void deleteUser(Long userId){
    	User user = getUser(userId);
        if(user != null)
            getTemplate().delete(user);
    }

    @Override
    public void updateUser(User user){
        getTemplate().update(user);
    }


    @Override
    public Page<User> getPageUsers(int currentPage,int pageSize){
        Page<User> pg = new Page<User>();
        StringBuffer strSql = new StringBuffer();
        strSql.append("from User order by id desc");
        ArrayList<User> userList=(ArrayList<User>) listByPage(strSql.toString(),currentPage,pageSize,null);
        Long totalSize = (Long)get("select count(*) from User");
        pg.setResultList(userList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalSize.intValue());
        return pg;
    }


    @Override
    public Page<User> searchPageUsers(int currentPage,int pageSize, UserCommand command){
        Page<User> pg = new Page<User>();
        //Criteria query model
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        if(command.getId() != null)
            detachedCriteria.add(Restrictions.eq("id", command.getId()));
        if(command.getAccount() != null)
            detachedCriteria.add(Restrictions.like("account", command.getAccount().trim(), MatchMode.ANYWHERE));
        if(command.getRealName() != null)
            detachedCriteria.add(Restrictions.like("realName", command.getRealName().trim(), MatchMode.ANYWHERE));
        /*if(command.getPassword() != null)
            detachedCriteria.add(Restrictions.like("password", command.getPassword().trim(), MatchMode.ANYWHERE));
        if(command.getOrgId() != null)
            detachedCriteria.add(Restrictions.eq("orgId", command.getOrgId()));
        if(command.getCreator() != null)
            detachedCriteria.add(Restrictions.eq("creator", command.getCreator()));
        if(command.getCreateTime() != null)
            detachedCriteria.add(Restrictions.eq("createTime", command.getCreateTime()));
        if(command.getDeleted() != null)
            detachedCriteria.add(Restrictions.eq("deleted", command.getDeleted()));
        if(command.getDeletor() != null)
            detachedCriteria.add(Restrictions.eq("deletor", command.getDeletor()));*/    
        ArrayList<User> userList=(ArrayList<User>) getTemplate().findByCriteria(detachedCriteria, currentPage * pageSize, pageSize);


        Criteria criteria = detachedCriteria.getExecutableCriteria(getTemplate().getSessionFactory().openSession());
        int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();


        pg.setResultList(userList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalCount);
        return pg;
    }
}

