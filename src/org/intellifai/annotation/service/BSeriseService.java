/**
 * @(#)BSeriseService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service;

import org.intellifai.annotation.command.BSeriseCommand;
import org.intellifai.annotation.model.BSerise;
import org.intellifai.annotation.web.util.Page;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 下午1:58:40
 * @version V2.0
 */
public interface BSeriseService {


    String createBSerise(BSeriseCommand bSeriseCommand);


    void deleteBSerise(String bSeriseId);


    BSerise getBSerise(String bSeriseId);


    void updateBSerise(BSerise bSerise);


    Page<BSerise> getPageBSerises(int currentPage,int pageSize);


    Page<BSerise> searchPageBSerises(int currentPage,int pageSize, BSeriseCommand bSeriseCommand);
}


