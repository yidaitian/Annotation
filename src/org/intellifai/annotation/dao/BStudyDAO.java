/**
 * @(#)BStudyDAO.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.dao;

import org.intellifai.annotation.command.BStudyCommand;
import org.intellifai.annotation.model.BStudy;
import org.intellifai.annotation.web.util.Page;

/**
 * ����Ϣ����
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 ����11:48:12
 * @version V2.0
 */
public interface BStudyDAO {


    String createBStudy(BStudy bStudy);


    void deleteBStudy(String bStudyId);


    BStudy getBStudy(String bStudyId);


    void updateBStudy(BStudy bStudy);


    Page<BStudy> getPageBStudys(int currentPage,int pageSize);


    Page<BStudy> searchPageBStudys(int currentPage,int pageSize, BStudyCommand bStudyCommand);
}


