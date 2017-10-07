/**
 * @(#)BStudy.java Copyright 2017-2018 carson. All rights reserved.
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
 * @date 2017-9-8 上午11:07:54
 * @version V2.0
 */
@Entity
@Table(name="B_Study")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class BStudy implements Serializable {
    private String studyTID;  //studyTID
    private String studyInstanceUID;  //studyInstanceUID
    private String patientID;  //patientID
    private String patientAge;  //patientAge
    private String patientSex;  //patientSex
    private String path;  //path
    private Integer assigned;  //assigned
    private Integer assignNum;  //assignNum
    private Date createTime;  //createTime


    @Id
    @GeneratedValue
    @Basic(optional=false)
    @Column(length=64)
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


    public String getPatientID() {
        return patientID;
    }
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }


    public String getPatientAge() {
        return patientAge;
    }
    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }


    public String getPatientSex() {
        return patientSex;
    }
    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }


    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }


    public Integer getAssigned() {
        return assigned;
    }
    public void setAssigned(Integer assigned) {
        this.assigned = assigned;
    }


    public Integer getAssignNum() {
        return assignNum;
    }
    public void setAssignNum(Integer assignNum) {
        this.assignNum = assignNum;
    }


    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}


