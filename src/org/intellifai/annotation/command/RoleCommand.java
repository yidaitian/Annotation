/**
 * @(#)RoleCommand.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.command;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.intellifai.annotation.model.Role;
import org.springframework.util.Assert;


/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 上午9:39:36
 * @version V2.0
 */
public class RoleCommand {

	private Integer id;
    private String name;
    private String description;
    private String permissions;


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


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
	public void updateRole(Role role) {
        Assert.isTrue( id.equals( role.getId() ), "Role ID of command must match the user being updated." );
        
		role.setName(getName());
        role.setDescription(getDescription());
        Set<String> rSet = new HashSet<String>(Arrays.asList(getPermissions().split(",")));
        role.setPermissions(rSet);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
			+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
			+ ((permissions == null) ? 0 : permissions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleCommand other = (RoleCommand) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (permissions == null) {
			if (other.permissions != null)
				return false;
		} else if (!permissions.equals(other.permissions))
			return false;
		return true;
	}
}

