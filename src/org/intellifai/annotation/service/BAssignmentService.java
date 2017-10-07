/**
 * @(#)BAssignmentService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service;

import org.intellifai.annotation.command.BAssignmentCommand;
import org.intellifai.annotation.model.BAssignment;
import org.intellifai.annotation.web.util.Page;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 下午1:56:49
 * @version V2.0
 */
public interface BAssignmentService {


    String createBAssignment(BAssignmentCommand bAssignmentCommand);


    void deleteBAssignment(String bAssignmentId);


    BAssignment getBAssignment(String bAssignmentId);


    void updateBAssignment(BAssignment bAssignment);


    Page<BAssignment> getPageBAssignments(int currentPage,int pageSize);


    Page<BAssignment> searchPageBAssignments(int currentPage,int pageSize, BAssignmentCommand bAssignmentCommand);
}


