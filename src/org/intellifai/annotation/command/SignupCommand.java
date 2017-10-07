/**
 * @(#)SignupCommand.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.command;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-10-5 下午9:16:29
 * @version V2.0
 */
public class SignupCommand {

	private String account;

    private String realName;

    private String password;
    
    private String roleId;

    private String belongOrg;
    
    public String getBelongOrg(){
    	return belongOrg;
    }
    public void setBelongOrg(String belongOrg){
    	this.belongOrg = belongOrg;
    }
    
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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
}

