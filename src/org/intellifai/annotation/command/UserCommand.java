/**
 * @(#)UserCommand.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.command;

import java.util.Date;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午9:39:04
 * @version V2.0
 */
public class UserCommand {

	private Long id;
    private String account;
    private String realName;
    private String password;
    private Integer orgId;
    private String belongOrg;
    private Integer creator;
    private Date createTime;
    private Integer deleted;
    private Integer deletor;

    public String getBelongOrg(){
    	return belongOrg;
    }
    public void setBelongOrg(String belongOrg){
    	this.belongOrg = belongOrg;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }


    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getOrgId() {
        return orgId;
    }
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }


    public Integer getCreator() {
        return creator;
    }
    public void setCreator(Integer creator) {
        this.creator = creator;
    }


    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Integer getDeleted() {
        return deleted;
    }
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }


    public Integer getDeletor() {
        return deletor;
    }
    public void setDeletor(Integer deletor) {
        this.deletor = deletor;
    }
}

