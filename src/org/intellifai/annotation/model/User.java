/**
 * @(#)User.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午9:03:30
 * @version V2.0
 */
@Entity
@Table(name="users")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class User {

	private Long id;  //id
    private String account;  //account
    private String realName;  //realName
    private String password;  //password
    //private Integer orgId;  //orgId
    private Long creator;  //creator
    private Date createTime;  //createTime
    private Integer deleted;  //deleted
    private Integer deletor;  //deletor
    
    private Organization belongOrg;
    
    private Set<Role> roles = new HashSet<Role>();


    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    @Basic(optional=false)
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


    @Basic(optional=false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "belongOrg")
    public Organization getBelongOrg(){
    	return this.belongOrg;
    }
    public void setBelongOrg(Organization belongOrg){
    	this.belongOrg = belongOrg;
    }
    /*
    public Integer getOrgId() {
        return orgId;
    }
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }*/


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


    public Integer getDeletor() {
        return deletor;
    }
    public void setDeletor(Integer deletor) {
        this.deletor = deletor;
    }
    
    @ManyToMany
    @JoinTable(name="users_roles")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

