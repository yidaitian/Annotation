/**
 * @(#)OrganizationCommand.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.command;

import java.util.Date;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午9:38:15
 * @version V2.0
 */
public class OrganizationCommand {

	private Integer id;
    private String name;
    private String address;
    private Long creator;
    private Date createTime;
    private Integer deleted;
    private String deletor;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


    public Long getCreator() {
        return creator;
    }
    public void setCreator(Long creator) {
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


    public String getDeletor() {
        return deletor;
    }
    public void setDeletor(String deletor) {
        this.deletor = deletor;
    }
}

