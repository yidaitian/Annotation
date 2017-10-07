/**
 * @(#)HibernateBImageDAO.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.dao.impl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.intellifai.annotation.command.BImageCommand;
import org.intellifai.annotation.dao.BImageDAO;
import org.intellifai.annotation.hibernate.BaseDaoImpl;
import org.intellifai.annotation.model.BImage;
import org.intellifai.annotation.web.util.Page;
import org.springframework.stereotype.Repository;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 下午12:21:32
 * @version V2.0
 */
@Repository
public class HibernateBImageDAO extends BaseDaoImpl implements BImageDAO{


    @Override
    public String createBImage(BImage bImage){
        return (String) getTemplate().save(bImage);
    }


    @Override
    public void deleteBImage(String bImageId){
        BImage bImage = getBImage(bImageId);
        if(bImage != null)
            getTemplate().delete(bImage);
    }


    @Override
    public BImage getBImage(String bImageId){
        return getTemplate().get(BImage.class, bImageId);
    }


    @Override
    public void updateBImage(BImage bImage){
        getTemplate().update(bImage);
    }


    @Override
    public Page<BImage> getPageBImages(int currentPage,int pageSize){
        Page<BImage> pg = new Page<BImage>();
        StringBuffer strSql = new StringBuffer();
        strSql.append("from BImage order by id desc");
        ArrayList<BImage> bImageList=(ArrayList<BImage>) listByPage(strSql.toString(),currentPage,pageSize,null);
        Long totalSize = (Long)get("select count(*) from BImage");
        pg.setResultList(bImageList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalSize.intValue());
        return pg;
    }


    @Override
    public Page<BImage> searchPageBImages(int currentPage,int pageSize, BImageCommand command){
        Page<BImage> pg = new Page<BImage>();
        //Criteria query model
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BImage.class);
        if(command.getImageTID() != null)
            detachedCriteria.add(Restrictions.like("imageTID", command.getImageTID().trim(), MatchMode.ANYWHERE));
        if(command.getStudyTID() != null)
            detachedCriteria.add(Restrictions.like("studyTID", command.getStudyTID().trim(), MatchMode.ANYWHERE));
        if(command.getStudyInstanceUID() != null)
            detachedCriteria.add(Restrictions.like("studyInstanceUID", command.getStudyInstanceUID().trim(), MatchMode.ANYWHERE));
        if(command.getSeriseTID() != null)
            detachedCriteria.add(Restrictions.like("seriseTID", command.getSeriseTID().trim(), MatchMode.ANYWHERE));
        if(command.getSeriseInstanceUID() != null)
            detachedCriteria.add(Restrictions.like("seriseInstanceUID", command.getSeriseInstanceUID().trim(), MatchMode.ANYWHERE));
        if(command.getSopInstanceUID() != null)
            detachedCriteria.add(Restrictions.like("sopInstanceUID", command.getSopInstanceUID().trim(), MatchMode.ANYWHERE));
        if(command.getPath() != null)
            detachedCriteria.add(Restrictions.like("path", command.getPath().trim(), MatchMode.ANYWHERE));
        if(command.getJson() != null)
            detachedCriteria.add(Restrictions.like("json", command.getJson().trim(), MatchMode.ANYWHERE));
        if(command.getJsonType() != null)
            detachedCriteria.add(Restrictions.eq("jsonType", command.getJsonType()));
        if(command.getIsProcessed() != null)
            detachedCriteria.add(Restrictions.eq("isProcessed", command.getIsProcessed()));
        if(command.getAiJson() != null)
            detachedCriteria.add(Restrictions.like("aiJson", command.getAiJson().trim(), MatchMode.ANYWHERE));
        if(command.getAiJsonType() != null)
            detachedCriteria.add(Restrictions.eq("aiJsonType", command.getAiJsonType()));
        if(command.getCreateTime() != null)
            detachedCriteria.add(Restrictions.eq("createTime", command.getCreateTime()));
        ArrayList<BImage> bImageList=(ArrayList<BImage>) getTemplate().findByCriteria(detachedCriteria, currentPage * pageSize, pageSize);


        Criteria criteria = detachedCriteria.getExecutableCriteria(getTemplate().getSessionFactory().openSession());
        int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();


        pg.setResultList(bImageList);
        pg.setPageSize(pageSize);
        pg.setResultTotalSize(totalCount);
        return pg;
    }
}


