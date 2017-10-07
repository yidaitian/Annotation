/**
 * @(#)BImageCommand.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.command;

import java.util.Date;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 上午11:40:26
 * @version V2.0
 */
public class BImageCommand {
    private String imageTID;
    private String studyTID;
    private String studyInstanceUID;
    private String seriseTID;
    private String seriseInstanceUID;
    private String sopInstanceUID;
    private String path;
    private String json;
    private Integer jsonType;
    private Integer isProcessed;
    private String aiJson;
    private Integer aiJsonType;
    private Date createTime;


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


