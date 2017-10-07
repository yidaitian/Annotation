/**
 * @(#)HibernateBStudyDAO.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.dao.impl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.intellifai.annotation.command.BStudyCommand;
import org.intellifai.annotation.dao.BStudyDAO;
import org.intellifai.annotation.hibernate.BaseDaoImpl;
import org.intellifai.annotation.model.BStudy;
import org.intellifai.annotation.web.util.Page;
import org.springframework.stereotype.Repository;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 下午12:18:44
 * @version V2.0
 */
@Repository
public class HibernateBStudyDAO extends BaseDaoImpl implements BStudyDAO{


    @Override
    public String createBStudy(BStudy bStudy){
        return (String) getTemplate().save(bStudy);
    }


    @Override
    public void deleteBStudy(String bStudyId){
        BStudy bStudy = getBStudy(bStudyId);
        if(bStudy != null)
            getTemplate().delete(bStudy);
    }


    @Override
    public BStudy getBStudy(String bStudyId){
        return getTemplate().get(BStudy.class, bStudyId);
    }


    @Override
    public void updateBStudy(BStudy bStudy){
        getTemplate().update(bStudy);
    }


    @Override
    public Page<BStudy> getPageBStudys(int currentPage,int pageSize){
        Page<BStudy> pg = new Page<BStudy>();
        StringBuffer strSql = new StringBuffer();
        strSql.append("from BStudy order by id desc");
        ArrayList<BStudy> bStudyList=(ArrayList<BStudy>) listByPage(strSql.toString(),currentPage,pageSize,null);
        Long totalSize = (Long)get("select count(*) from BStudy");
        pg.setResultList(bStudyList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalSize.intValue());
        return pg;
    }


    @Override
    public Page<BStudy> searchPageBStudys(int currentPage,int pageSize, BStudyCommand command){
        Page<BStudy> pg = new Page<BStudy>();
        //Criteria query model
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BStudy.class);
        if(command.getStudyTID() != null)
            detachedCriteria.add(Restrictions.like("studyTID", command.getStudyTID().trim(), MatchMode.ANYWHERE));
        if(command.getStudyInstanceUID() != null)
            detachedCriteria.add(Restrictions.like("studyInstanceUID", command.getStudyInstanceUID().trim(), MatchMode.ANYWHERE));
        if(command.getPatientID() != null)
            detachedCriteria.add(Restrictions.like("patientID", command.getPatientID().trim(), MatchMode.ANYWHERE));
        if(command.getPatientAge() != null)
            detachedCriteria.add(Restrictions.like("patientAge", command.getPatientAge().trim(), MatchMode.ANYWHERE));
        if(command.getPatientSex() != null)
            detachedCriteria.add(Restrictions.like("patientSex", command.getPatientSex().trim(), MatchMode.ANYWHERE));
        if(command.getPath() != null)
            detachedCriteria.add(Restrictions.like("path", command.getPath().trim(), MatchMode.ANYWHERE));
        if(command.getAssigned() != null)
            detachedCriteria.add(Restrictions.eq("assigned", command.getAssigned()));
        if(command.getAssignNum() != null)
            detachedCriteria.add(Restrictions.eq("assignNum", command.getAssignNum()));
        if(command.getCreateTime() != null)
            detachedCriteria.add(Restrictions.eq("createTime", command.getCreateTime()));
        ArrayList<BStudy> bStudyList=(ArrayList<BStudy>) getTemplate().findByCriteria(detachedCriteria, currentPage * pageSize, pageSize);


        Criteria criteria = detachedCriteria.getExecutableCriteria(getTemplate().getSessionFactory().openSession());
        int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();


        pg.setResultList(bStudyList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalCount);
        return pg;
    }
}


