/**
 * @(#)Permission.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.model;

import javax.persistence.Basic;
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
 * @date 2017-10-5 上午9:10:57
 * @version V2.0
 */
@Entity
@Table(name="permissions")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Permission {

	private Integer id;  //id
    private Integer parentId;  //parentId
    private String name;  //name
    private String element;  //element
    private String description;  //description

    public Permission() {
    }

    public Permission(String name) {
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


    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getElement() {
        return element;
    }
    public void setElement(String element) {
        this.element = element;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

