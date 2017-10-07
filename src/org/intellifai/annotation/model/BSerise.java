/**
 * @(#)BSerise.java Copyright 2017-2018 carson. All rights reserved.
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
 * @date 2017-9-8 上午11:10:45
 * @version V2.0
 */
@Entity
@Table(name="B_Serise")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class BSerise implements Serializable {
    private String seriseTID;  //seriseTID
    private String studyTID;  //studyTID
    private String studyInstanceUID;  //studyInstanceUID
    private String seriseInstanceUID;  //seriseInstanceUID
    private Date createTime;  //createTime


    @Id
    @GeneratedValue
    @Basic(optional=false)
    @Column(length=64)
    public String getSeriseTID() {
        return seriseTID;
    }
    public void setSeriseTID(String seriseTID) {
        this.seriseTID = seriseTID;
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


    public String getSeriseInstanceUID() {
        return seriseInstanceUID;
    }
    public void setSeriseInstanceUID(String seriseInstanceUID) {
        this.seriseInstanceUID = seriseInstanceUID;
    }


    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}


