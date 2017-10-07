/**
 * @(#)Organization.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.model;

import java.util.Date;

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
 * @date 2017-10-5 上午9:09:16
 * @version V2.0
 */
@Entity
@Table(name="organizations")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Organization {

	private Integer id;  //id
    private String name;  //name
    private String address;  //address
    private Long creator;  //creator
    private Date createTime;  //createTime
    private Integer deleted;  //deleted
    private String deletor;  //deletor


    @Id
    @GeneratedValue
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

