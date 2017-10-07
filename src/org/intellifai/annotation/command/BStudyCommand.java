/**
 * @(#)BStudyCommand.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.command;

import java.util.Date;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 上午11:39:06
 * @version V2.0
 */
public class BStudyCommand {
    private String studyTID;
    private String studyInstanceUID;
    private String patientID;
    private String patientAge;
    private String patientSex;
    private String path;
    private Integer assigned;
    private Integer assignNum;
    private Date createTime;


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


