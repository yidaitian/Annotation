/**
 * @(#)DefaultBAssignmentService.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.service.impl;

import org.intellifai.annotation.command.BAssignmentCommand;
import org.intellifai.annotation.dao.BAssignmentDAO;
import org.intellifai.annotation.model.BAssignment;
import org.intellifai.annotation.service.BAssignmentService;
import org.intellifai.annotation.web.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 下午2:09:48
 * @version V2.0
 */
@Transactional
@Service
public class DefaultBAssignmentService implements BAssignmentService{


    @Autowired
    private BAssignmentDAO bAssignmentDAO;


    @Override
    public String createBAssignment(BAssignmentCommand bAssignmentCommand){
        BAssignment bAssignment = new BAssignment();
        bAssignment.setAssignmentTID(bAssignmentCommand.getAssignmentTID());
        bAssignment.setStudyTID(bAssignmentCommand.getStudyTID());
        bAssignment.setStudyInstanceUID(bAssignmentCommand.getStudyInstanceUID());
        bAssignment.setUserId(bAssignmentCommand.getUserId());
        bAssignment.setAssignTime(bAssignmentCommand.getAssignTime());
        bAssignment.setCreator(bAssignmentCommand.getCreator());
        bAssignment.setDeleted(bAssignmentCommand.getDeleted());
        bAssignment.setDeletor(bAssignmentCommand.getDeletor());
        return bAssignmentDAO.createBAssignment(bAssignment);
    }


    @Override
    public void deleteBAssignment(String bAssignmentId){
        bAssignmentDAO.deleteBAssignment(bAssignmentId);
    }


    @Override
    public BAssignment getBAssignment(String bAssignmentId){
        return bAssignmentDAO.getBAssignment(bAssignmentId);
    }


    @Override
    public void updateBAssignment(BAssignment bAssignment){
        bAssignmentDAO.updateBAssignment(bAssignment);
    }


    @Override
    public Page<BAssignment> getPageBAssignments(int currentPage,int pageSize){
        return bAssignmentDAO.getPageBAssignments(currentPage, pageSize);
    }


    @Override
    public Page<BAssignment> searchPageBAssignments(int currentPage,int pageSize, BAssignmentCommand bAssignmentCommand){
        return bAssignmentDAO.searchPageBAssignments(currentPage, pageSize, bAssignmentCommand);
    }
}


