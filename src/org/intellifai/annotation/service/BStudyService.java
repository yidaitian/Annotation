/**
 * @(#)BStudyService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service;

import org.intellifai.annotation.command.BStudyCommand;
import org.intellifai.annotation.model.BStudy;
import org.intellifai.annotation.web.util.Page;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 下午1:57:50
 * @version V2.0
 */
public interface BStudyService {


    String createBStudy(BStudyCommand bStudyCommand);


    void deleteBStudy(String bStudyId);


    BStudy getBStudy(String bStudyId);


    void updateBStudy(BStudy bStudy);


    Page<BStudy> getPageBStudys(int currentPage,int pageSize);


    Page<BStudy> searchPageBStudys(int currentPage,int pageSize, BStudyCommand bStudyCommand);
}


