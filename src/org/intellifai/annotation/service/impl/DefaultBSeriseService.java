/**
 * @(#)DefaultBSeriseService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service.impl;

import org.intellifai.annotation.command.BSeriseCommand;
import org.intellifai.annotation.dao.BSeriseDAO;
import org.intellifai.annotation.model.BSerise;
import org.intellifai.annotation.service.BSeriseService;
import org.intellifai.annotation.web.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 下午2:12:11
 * @version V2.0
 */
@Transactional
@Service
public class DefaultBSeriseService implements BSeriseService{


    @Autowired
    private BSeriseDAO bSeriseDAO;


    @Override
    public String createBSerise(BSeriseCommand bSeriseCommand){
        BSerise bSerise = new BSerise();
        bSerise.setSeriseTID(bSeriseCommand.getSeriseTID());
        bSerise.setStudyTID(bSeriseCommand.getStudyTID());
        bSerise.setStudyInstanceUID(bSeriseCommand.getStudyInstanceUID());
        bSerise.setSeriseInstanceUID(bSeriseCommand.getSeriseInstanceUID());
        bSerise.setCreateTime(bSeriseCommand.getCreateTime());
        return bSeriseDAO.createBSerise(bSerise);
    }


    @Override
    public void deleteBSerise(String bSeriseId){
        bSeriseDAO.deleteBSerise(bSeriseId);
    }


    @Override
    public BSerise getBSerise(String bSeriseId){
        return bSeriseDAO.getBSerise(bSeriseId);
    }


    @Override
    public void updateBSerise(BSerise bSerise){
        bSeriseDAO.updateBSerise(bSerise);
    }


    @Override
    public Page<BSerise> getPageBSerises(int currentPage,int pageSize){
        return bSeriseDAO.getPageBSerises(currentPage, pageSize);
    }


    @Override
    public Page<BSerise> searchPageBSerises(int currentPage,int pageSize, BSeriseCommand bSeriseCommand){
        return bSeriseDAO.searchPageBSerises(currentPage, pageSize, bSeriseCommand);
    }
}


