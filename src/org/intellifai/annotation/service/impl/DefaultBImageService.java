/**
 * @(#)DefaultBImageService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service.impl;

import org.intellifai.annotation.command.BImageCommand;
import org.intellifai.annotation.dao.BImageDAO;
import org.intellifai.annotation.model.BImage;
import org.intellifai.annotation.service.BImageService;
import org.intellifai.annotation.web.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 下午2:14:04
 * @version V2.0
 */
@Transactional
@Service
public class DefaultBImageService implements BImageService{


    @Autowired
    private BImageDAO bImageDAO;


    @Override
    public String createBImage(BImageCommand bImageCommand){
        BImage bImage = new BImage();
        bImage.setImageTID(bImageCommand.getImageTID());
        bImage.setStudyTID(bImageCommand.getStudyTID());
        bImage.setStudyInstanceUID(bImageCommand.getStudyInstanceUID());
        bImage.setSeriseTID(bImageCommand.getSeriseTID());
        bImage.setSeriseInstanceUID(bImageCommand.getSeriseInstanceUID());
        bImage.setSopInstanceUID(bImageCommand.getSopInstanceUID());
        bImage.setPath(bImageCommand.getPath());
        bImage.setJson(bImageCommand.getJson());
        bImage.setJsonType(bImageCommand.getJsonType());
        bImage.setIsProcessed(bImageCommand.getIsProcessed());
        bImage.setAiJson(bImageCommand.getAiJson());
        bImage.setAiJsonType(bImageCommand.getAiJsonType());
        bImage.setCreateTime(bImageCommand.getCreateTime());
        return bImageDAO.createBImage(bImage);
    }


    @Override
    public void deleteBImage(String bImageId){
        bImageDAO.deleteBImage(bImageId);
    }


    @Override
    public BImage getBImage(String bImageId){
        return bImageDAO.getBImage(bImageId);
    }


    @Override
    public void updateBImage(BImage bImage){
        bImageDAO.updateBImage(bImage);
    }


    @Override
    public Page<BImage> getPageBImages(int currentPage,int pageSize){
        return bImageDAO.getPageBImages(currentPage, pageSize);
    }


    @Override
    public Page<BImage> searchPageBImages(int currentPage,int pageSize, BImageCommand bImageCommand){
        return bImageDAO.searchPageBImages(currentPage, pageSize, bImageCommand);
    }
}


