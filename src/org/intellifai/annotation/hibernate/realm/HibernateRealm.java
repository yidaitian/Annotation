/**
 * @(#)HibernateRealm.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.hibernate.realm;

import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.intellifai.annotation.dao.UserDAO;
import org.intellifai.annotation.model.Role;
import org.intellifai.annotation.model.User;

/**
 * Configured Apache Shiro Realm.
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 ����12:23:42
 * @version V2.0
 */
@Component
public class HibernateRealm extends AuthorizingRealm {

	@Autowired
	private SessionDAO sessionDAO;    
	
	@Autowired
    protected UserDAO userDAO;

	@SuppressWarnings("deprecation")
	public HibernateRealm() {
        setName("HibernateRealm"); //This name must match the name in the User class's getPrincipals() method
        setCredentialsMatcher(new Sha256CredentialsMatcher());
        setCacheManager(new MemoryConstrainedCacheManager());
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        
        User user = userDAO.findUser(token.getUsername());
        if( user != null ) {
        	String loginName = token.getUsername();
        	Session currentSession = null;
        	Collection<Session> sessions = sessionDAO.getActiveSessions();
        	for(Session session:sessions){
        		if(loginName.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))){
        			session.setTimeout(0);  //����session����ʧЧ�����������ϵͳ
        			break;
        		}
        	}
        	try{
            return new SimpleAuthenticationInfo(user.getId(), user.getPassword(), getName());
        	}catch(Exception ex){
        		System.out.println(ex);
        		return null;
        	}
        } else {
            return null;
        }
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {   	
        Long userId = (Long) principals.fromRealm(getName()).iterator().next();
    	//String username = (String)principals.getPrimaryPrincipal(); 
        User user = userDAO.getUser(userId);
        if( user != null ) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            for( Role role : user.getRoles()) {
                info.addRole(role.getName());
                info.addStringPermissions( role.getPermissions() );
            }
            return info;
        } else {
            return null;
        }
    }
}

