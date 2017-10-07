/**
 * @(#)BaseDaoImpl.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.hibernate;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.intellifai.annotation.hibernate.IBaseDao;

/**
 * Encapsulated base database operations, most base database operations already encapsulated in the class HibernateDaoSupport
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 ����11:54:49
 * @version V2.0
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl extends HibernateDaoSupport implements IBaseDao {

	@Autowired  
    public void setSessionFactoryOverride(SessionFactory sessionFactory){  
        super.setSessionFactory(sessionFactory);  
    } 
	
	@Override
	public int countBySQL(String sql) {
		BigInteger intg = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
		return intg.intValue();             
	}
	
	@Override
	public int countBySQL(String sql, Object... values) {
		Query queryObject = this.getSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		BigInteger intg = (BigInteger) queryObject.uniqueResult();
		return intg.intValue();             
	}
	
    @Override
	public int executeBySQL(String sql) {
    	return this.getSession().createSQLQuery(sql).executeUpdate();             
	}

	@Override
	public int executeBySQL(String sql, Object value) {
		return executeBySQL(sql, new Object[] {value});
	}

	@Override
	public int executeBySQL(String sql, Object... values) {
		Query queryObject = this.getSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject.executeUpdate();       
	}  
      
	@Override
	public <T> T get(String hql){  
		 return (T) this.getSession().createQuery(hql).setMaxResults(1).uniqueResult();         
    } 
	
	@Override
	public <T> T get(String hql, Object value){  
		return get(hql, new Object[] {value});      
	} 
	
	@Override
	public <T> T get(String hql, Object... values){  
		try{
		Query queryObject = this.getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return (T) queryObject.setMaxResults(1).uniqueResult();  
		}catch(Exception ex){
			System.out.println(ex);
			return null;
		}
	} 
	
	@SuppressWarnings("rawtypes")
	@Override
	public List listByPage(final String queryString,final int currentPage,final int pageSize, final Object... values) throws DataAccessException {
		return getTemplate().executeWithNativeSession(new HibernateCallback<List>() {
			public List doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(queryString);
				prepareQuery(queryObject);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
				return queryObject.setFirstResult(currentPage * pageSize).setMaxResults(pageSize).list();
			}
		});
	}
	
	/**
	 * Prepare the given Query object, applying cache settings and/or
	 * a transaction timeout.
	 * @param queryObject the Query object to prepare
	 * @see #setCacheQueries
	 * @see #setQueryCacheRegion
	 * @see SessionFactoryUtils#applyTransactionTimeout
	 */
	protected void prepareQuery(Query queryObject) {
		if (getTemplate().isCacheQueries()) {
			queryObject.setCacheable(true);
			if (getTemplate().getQueryCacheRegion() != null) {
				queryObject.setCacheRegion(getTemplate().getQueryCacheRegion());
			}
		}
		if (getTemplate().getFetchSize() > 0) {
			queryObject.setFetchSize(getTemplate().getFetchSize());
		}
		if (getTemplate().getMaxResults() > 0) {
			queryObject.setMaxResults(getTemplate().getMaxResults());
		}
		SessionFactoryUtils.applyTransactionTimeout(queryObject, getSessionFactory());
	}
	
	@Override
    public HibernateTemplate getTemplate() {  
        return this.getHibernateTemplate();  
    }
}

