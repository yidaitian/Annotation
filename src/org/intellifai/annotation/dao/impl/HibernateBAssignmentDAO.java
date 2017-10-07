/**
 * @(#)HibernateBAssignmentDAO.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.dao.impl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.intellifai.annotation.command.BAssignmentCommand;
import org.intellifai.annotation.dao.BAssignmentDAO;
import org.intellifai.annotation.hibernate.BaseDaoImpl;
import org.intellifai.annotation.model.BAssignment;
import org.intellifai.annotation.web.util.Page;
import org.springframework.stereotype.Repository;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 下午12:16:55
 * @version V2.0
 */
@Repository
public class HibernateBAssignmentDAO extends BaseDaoImpl implements BAssignmentDAO{


    @Override
    public String createBAssignment(BAssignment bAssignment){
        return (String) getTemplate().save(bAssignment);
    }


    @Override
    public void deleteBAssignment(String bAssignmentId){
        BAssignment bAssignment = getBAssignment(bAssignmentId);
        if(bAssignment != null)
            getTemplate().delete(bAssignment);
    }


    @Override
    public BAssignment getBAssignment(String bAssignmentId){
        return getTemplate().get(BAssignment.class, bAssignmentId);
    }


    @Override
    public void updateBAssignment(BAssignment bAssignment){
        getTemplate().update(bAssignment);
    }


    @Override
    public Page<BAssignment> getPageBAssignments(int currentPage,int pageSize){
        Page<BAssignment> pg = new Page<BAssignment>();
        StringBuffer strSql = new StringBuffer();
        strSql.append("from BAssignment order by id desc");
        ArrayList<BAssignment> bAssignmentList=(ArrayList<BAssignment>) listByPage(strSql.toString(),currentPage,pageSize,null);
        Long totalSize = (Long)get("select count(*) from BAssignment");
        pg.setResultList(bAssignmentList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalSize.intValue());
        return pg;
    }


    @Override
    public Page<BAssignment> searchPageBAssignments(int currentPage,int pageSize, BAssignmentCommand command){
        Page<BAssignment> pg = new Page<BAssignment>();
        //Criteria query model
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BAssignment.class);
        if(command.getAssignmentTID() != null)
            detachedCriteria.add(Restrictions.like("assignmentTID", command.getAssignmentTID().trim(), MatchMode.ANYWHERE));
        if(command.getStudyTID() != null)
            detachedCriteria.add(Restrictions.like("studyTID", command.getStudyTID().trim(), MatchMode.ANYWHERE));
        if(command.getStudyInstanceUID() != null)
            detachedCriteria.add(Restrictions.like("studyInstanceUID", command.getStudyInstanceUID().trim(), MatchMode.ANYWHERE));
        if(command.getUserId() != null)
            detachedCriteria.add(Restrictions.eq("userId", command.getUserId()));
        if(command.getAssignTime() != null)
            detachedCriteria.add(Restrictions.eq("assignTime", command.getAssignTime()));
        if(command.getCreator() != null)
            detachedCriteria.add(Restrictions.eq("creator", command.getCreator()));
        if(command.getDeleted() != null)
            detachedCriteria.add(Restrictions.eq("deleted", command.getDeleted()));
        if(command.getDeletor() != null)
            detachedCriteria.add(Restrictions.eq("deletor", command.getDeletor()));
        ArrayList<BAssignment> bAssignmentList=(ArrayList<BAssignment>) getTemplate().findByCriteria(detachedCriteria, currentPage * pageSize, pageSize);


        Criteria criteria = detachedCriteria.getExecutableCriteria(getTemplate().getSessionFactory().openSession());
        int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();


        pg.setResultList(bAssignmentList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalCount);
        return pg;
    }
}


