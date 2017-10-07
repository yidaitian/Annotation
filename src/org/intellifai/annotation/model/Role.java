/**
 * @(#)Role.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.model;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CollectionOfElements;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午9:05:44
 * @version V2.0
 */
@Entity
@Table(name="roles")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Role {

	private Integer id;  //id
    private String name;  //name
    private String description;  //description
    
    private Set<String> permissions;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    @Basic(optional=false)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }


    @Basic(optional=false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @Basic(optional=false)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    @CollectionOfElements
    @JoinTable(name="roles_permissions")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}

