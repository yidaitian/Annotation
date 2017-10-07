/**
 * @(#)BAssignmentCommand.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.command;

import java.util.Date;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 上午11:37:15
 * @version V2.0
 */
public class BAssignmentCommand {
    private String assignmentTID;
    private String studyTID;
    private String studyInstanceUID;
    private Long userId;
    private Date assignTime;
    private Long creator;
    private Integer deleted;
    private Long deletor;


    public String getAssignmentTID() {
        return assignmentTID;
    }
    public void setAssignmentTID(String assignmentTID) {
        this.assignmentTID = assignmentTID;
    }


    public String getStudyTID() {
        return studyTID;
    }
    public void setStudyTID(String studyTID) {
        this.studyTID = studyTID;
    }


    public String getStudyInstanceUID() {
        return studyInstanceUID;
    }
    public void setStudyInstanceUID(String studyInstanceUID) {
        this.studyInstanceUID = studyInstanceUID;
    }


    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Date getAssignTime() {
        return assignTime;
    }
    public void setAssignTime(Date assignTime) {
        this.assignTime = assignTime;
    }


    public Long getCreator() {
        return creator;
    }
    public void setCreator(Long creator) {
        this.creator = creator;
    }


    public Integer getDeleted() {
        return deleted;
    }
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }


    public Long getDeletor() {
        return deletor;
    }
    public void setDeletor(Long deletor) {
        this.deletor = deletor;
    }
}


