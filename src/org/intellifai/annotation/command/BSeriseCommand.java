/**
 * @(#)BSeriseCommand.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.command;

import java.util.Date;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 上午11:39:46
 * @version V2.0
 */
public class BSeriseCommand {
    private String seriseTID;
    private String studyTID;
    private String studyInstanceUID;
    private String seriseInstanceUID;
    private Date createTime;


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


