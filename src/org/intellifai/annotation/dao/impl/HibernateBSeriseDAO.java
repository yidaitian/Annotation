/**
 * @(#)HibernateBSeriseDAO.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.dao.impl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.intellifai.annotation.command.BSeriseCommand;
import org.intellifai.annotation.dao.BSeriseDAO;
import org.intellifai.annotation.hibernate.BaseDaoImpl;
import org.intellifai.annotation.model.BSerise;
import org.intellifai.annotation.web.util.Page;
import org.springframework.stereotype.Repository;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 下午12:20:02
 * @version V2.0
 */
@Repository
public class HibernateBSeriseDAO extends BaseDaoImpl implements BSeriseDAO{


    @Override
    public String createBSerise(BSerise bSerise){
        return (String) getTemplate().save(bSerise);
    }


    @Override
    public void deleteBSerise(String bSeriseId){
        BSerise bSerise = getBSerise(bSeriseId);
        if(bSerise != null)
            getTemplate().delete(bSerise);
    }


    @Override
    public BSerise getBSerise(String bSeriseId){
        return getTemplate().get(BSerise.class, bSeriseId);
    }


    @Override
    public void updateBSerise(BSerise bSerise){
        getTemplate().update(bSerise);
    }


    @Override
    public Page<BSerise> getPageBSerises(int currentPage,int pageSize){
        Page<BSerise> pg = new Page<BSerise>();
        StringBuffer strSql = new StringBuffer();
        strSql.append("from BSerise order by id desc");
        ArrayList<BSerise> bSeriseList=(ArrayList<BSerise>) listByPage(strSql.toString(),currentPage,pageSize,null);
        Long totalSize = (Long)get("select count(*) from BSerise");
        pg.setResultList(bSeriseList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalSize.intValue());
        return pg;
    }


    @Override
    public Page<BSerise> searchPageBSerises(int currentPage,int pageSize, BSeriseCommand command){
        Page<BSerise> pg = new Page<BSerise>();
        //Criteria query model
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BSerise.class);
        if(command.getSeriseTID() != null)
            detachedCriteria.add(Restrictions.like("seriseTID", command.getSeriseTID().trim(), MatchMode.ANYWHERE));
        if(command.getStudyTID() != null)
            detachedCriteria.add(Restrictions.like("studyTID", command.getStudyTID().trim(), MatchMode.ANYWHERE));
        if(command.getStudyInstanceUID() != null)
            detachedCriteria.add(Restrictions.like("studyInstanceUID", command.getStudyInstanceUID().trim(), MatchMode.ANYWHERE));
        if(command.getSeriseInstanceUID() != null)
            detachedCriteria.add(Restrictions.like("seriseInstanceUID", command.getSeriseInstanceUID().trim(), MatchMode.ANYWHERE));
        if(command.getCreateTime() != null)
            detachedCriteria.add(Restrictions.eq("createTime", command.getCreateTime()));
        ArrayList<BSerise> bSeriseList=(ArrayList<BSerise>) getTemplate().findByCriteria(detachedCriteria, currentPage * pageSize, pageSize);


        Criteria criteria = detachedCriteria.getExecutableCriteria(getTemplate().getSessionFactory().openSession());
        int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();


        pg.setResultList(bSeriseList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalCount);
        return pg;
    }
}

