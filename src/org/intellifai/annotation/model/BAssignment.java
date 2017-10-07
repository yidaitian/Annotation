/**
 * @(#)BAssignment.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 上午11:06:23
 * @version V2.0
 */
@Entity
@Table(name="B_Assignment")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class BAssignment implements Serializable {
    private String assignmentTID;  //assignmentTID
    private String studyTID;  //studyTID
    private String studyInstanceUID;  //studyInstanceUID
    private Long userId;  //userId
    private Date assignTime;  //assignTime
    private Long creator;  //creator
    private Integer deleted;  //deleted
    private Long deletor;  //deletor


    @Id
    @GeneratedValue
    @Basic(optional=false)
    @Column(length=64)
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


