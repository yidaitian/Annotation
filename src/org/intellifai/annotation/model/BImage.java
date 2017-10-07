/**
 * @(#)BImage.java Copyright 2017-2018 carson. All rights reserved.
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
 * @date 2017-9-8 上午11:15:04
 * @version V2.0
 */
@Entity
@Table(name="B_Image")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class BImage implements Serializable {
    private String imageTID;  //imageTID
    private String studyTID;  //studyTID
    private String studyInstanceUID;  //studyInstanceUID
    private String seriseTID;  //seriseTID
    private String seriseInstanceUID;  //seriseInstanceUID
    private String sopInstanceUID;  //sopInstanceUID
    private String path;  //path
    private String json;  //json
    private Integer jsonType;  //jsonType
    private Integer isProcessed;  //isProcessed
    private String aiJson;  //aiJson
    private Integer aiJsonType;  //aiJsonType
    private Date createTime;  //createTime


    @Id
    @GeneratedValue
    @Basic(optional=false)
    @Column(length=64)
    public String getImageTID() {
        return imageTID;
    }
    public void setImageTID(String imageTID) {
        this.imageTID = imageTID;
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


    public String getSeriseTID() {
        return seriseTID;
    }
    public void setSeriseTID(String seriseTID) {
        this.seriseTID = seriseTID;
    }


    public String getSeriseInstanceUID() {
        return seriseInstanceUID;
    }
    public void setSeriseInstanceUID(String seriseInstanceUID) {
        this.seriseInstanceUID = seriseInstanceUID;
    }


    public String getSopInstanceUID() {
        return sopInstanceUID;
    }
    public void setSopInstanceUID(String sopInstanceUID) {
        this.sopInstanceUID = sopInstanceUID;
    }


    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }


    public String getJson() {
        return json;
    }
    public void setJson(String json) {
        this.json = json;
    }


    public Integer getJsonType() {
        return jsonType;
    }
    public void setJsonType(Integer jsonType) {
        this.jsonType = jsonType;
    }


    public Integer getIsProcessed() {
        return isProcessed;
    }
    public void setIsProcessed(Integer isProcessed) {
        this.isProcessed = isProcessed;
    }


    public String getAiJson() {
        return aiJson;
    }
    public void setAiJson(String aiJson) {
        this.aiJson = aiJson;
    }


    public Integer getAiJsonType() {
        return aiJsonType;
    }
    public void setAiJsonType(Integer aiJsonType) {
        this.aiJsonType = aiJsonType;
    }


    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}


