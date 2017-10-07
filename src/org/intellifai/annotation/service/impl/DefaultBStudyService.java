/**
 * @(#)DefaultBStudyService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service.impl;

import org.intellifai.annotation.command.BStudyCommand;
import org.intellifai.annotation.dao.BStudyDAO;
import org.intellifai.annotation.model.BStudy;
import org.intellifai.annotation.service.BStudyService;
import org.intellifai.annotation.web.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 下午2:10:50
 * @version V2.0
 */
@Transactional
@Service
public class DefaultBStudyService implements BStudyService{


    @Autowired
    private BStudyDAO bStudyDAO;


    @Override
    public String createBStudy(BStudyCommand bStudyCommand){
        BStudy bStudy = new BStudy();
        bStudy.setStudyTID(bStudyCommand.getStudyTID());
        bStudy.setStudyInstanceUID(bStudyCommand.getStudyInstanceUID());
        bStudy.setPatientID(bStudyCommand.getPatientID());
        bStudy.setPatientAge(bStudyCommand.getPatientAge());
        bStudy.setPatientSex(bStudyCommand.getPatientSex());
        bStudy.setPath(bStudyCommand.getPath());
        bStudy.setAssigned(bStudyCommand.getAssigned());
        bStudy.setAssignNum(bStudyCommand.getAssignNum());
        bStudy.setCreateTime(bStudyCommand.getCreateTime());
        return bStudyDAO.createBStudy(bStudy);
    }


    @Override
    public void deleteBStudy(String bStudyId){
        bStudyDAO.deleteBStudy(bStudyId);
    }


    @Override
    public BStudy getBStudy(String bStudyId){
        return bStudyDAO.getBStudy(bStudyId);
    }


    @Override
    public void updateBStudy(BStudy bStudy){
        bStudyDAO.updateBStudy(bStudy);
    }


    @Override
    public Page<BStudy> getPageBStudys(int currentPage,int pageSize){
        return bStudyDAO.getPageBStudys(currentPage, pageSize);
    }


    @Override
    public Page<BStudy> searchPageBStudys(int currentPage,int pageSize, BStudyCommand bStudyCommand){
        return bStudyDAO.searchPageBStudys(currentPage, pageSize, bStudyCommand);
    }
}


