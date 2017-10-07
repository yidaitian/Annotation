/**
 * @(#)BImageService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service;

import org.intellifai.annotation.command.BImageCommand;
import org.intellifai.annotation.model.BImage;
import org.intellifai.annotation.web.util.Page;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 下午1:59:40
 * @version V2.0
 */
public interface BImageService {


    String createBImage(BImageCommand bImageCommand);


    void deleteBImage(String bImageId);


    BImage getBImage(String bImageId);


    void updateBImage(BImage bImage);


    Page<BImage> getPageBImages(int currentPage,int pageSize);


    Page<BImage> searchPageBImages(int currentPage,int pageSize, BImageCommand bImageCommand);
}


