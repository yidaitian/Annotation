package org.intellifai.annotation.command;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.intellifai.annotation.model.User;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Command binding object for editing a user.
 */
public class EditUserCommand {

	private Long id;
	private String account;  //account
    private String realName;  //realName
    private String password;  //password
    private String belongOrg;
    
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

    public void updateUser(User user) {
        //Assert.isTrue( id.equals( user.getId() ), "User ID of command must match the user being updated." );
    	id = user.getId();
        user.setAccount(getAccount());
        //user.setEmail( getEmail() );
        if( StringUtils.hasText(getPassword()) ) {
            user.setPassword( new Sha256Hash(getPassword()).toHex() );
        }
    }
}
