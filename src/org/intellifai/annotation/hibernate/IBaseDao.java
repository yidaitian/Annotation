/**
 * @(#)IBaseDao.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Data Access Object interface encapsulating for basic operations.
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 ÉÏÎç11:54:04
 * @version V2.0
 */
public interface IBaseDao {

	/**
	 * Execute a SQL query
	 * @param sql a query expressed in Structured Query Language
	 * @return the records` count after executed
	 * @see org.hibernate.Session#createSQLQuery
	 */
	int countBySQL(String sql);  
	
	/**
	 * Execute a SQL query, support parameter
	 * @param sql a query expressed in Structured Query Language
	 * @return the records` count after executed
	 * @see org.hibernate.Session#createSQLQuery
	 */
	int countBySQL(String sql, Object... values);  
	
	/**
	 * Execute a SQL query
	 * @param sql a query expressed in Structured Query Language
	 * @return the records affected number after executed
	 * @see org.hibernate.Session#createSQLQuery
	 */
	int executeBySQL(String sql);  
	
	/**
	 * Execute a SQL query, binding one value to a "?" parameter in the query string.
	 * @param sql a query expressed in Structured Query Language
	 * @return the records affected number after executed
	 * @see org.hibernate.Session#createSQLQuery
	 */
	int executeBySQL(String sql, Object value);  
	
    /**
     * Execute a SQL query, binding a number of values to "?" parameters in the query string.
     * @param sql a query expressed in Structured Query Language
     * @return the records affected number after executed
     * @see org.hibernate.Session#createSQLQuery
     */
    int executeBySQL(String sql, Object... values);  
      
    /**
	 * Execute an HQL query.
	 * @param hql a query expressed in Hibernate's query language
	 * @return an {@link Object} containing the results of the query execution
	 * @see org.hibernate.Session#createQuery
	 */
    <T> T get(String hql);
    
    /**
	 * Execute an HQL query, binding one value to a "?" parameter in the query string.
	 * @param hql a query expressed in Hibernate's query language
	 * @param value the value of the parameter
	 * @return an {@link Object} containing the results of the query execution
	 * @see org.hibernate.Session#createQuery
	 */
    <T> T get(String hql, Object value);
    
    /**
	 * Execute an HQL query, binding a number of values to "?" parameters in the query string.
	 * @param queryString a query expressed in Hibernate's query language
	 * @param values the values of the parameters
	 * @return an {@link Object} containing the results of the query execution
	 * @see org.hibernate.Session#createQuery
	 */
    <T> T get(String hql, Object... values);
    
    
    /**
	 * Execute an HQL query, binding a number of values to "?" parameters
	 * in the query string.
	 * @param queryString a query expressed in Hibernate's query language
	 * @param pageSize the values of the each page size parameters
	 * @param currentPage the values of the current page parameters
	 * @param values the values of the parameters
	 * @return a {@link List} containing the results of the query execution
	 * @throws org.springframework.dao.DataAccessException in case of Hibernate errors
	 * @see org.hibernate.Session#createQuery
	 */
    List<?> listByPage(final String queryString, final int currentPage, final int pageSize, final Object... values);
    
    /**
	 * Return the HibernateTemplate for this DAO,
	 * pre-initialized with the SessionFactory or set explicitly.
	 * <p><b>Note: The returned HibernateTemplate is a shared instance.</b>
	 * You may introspect its configuration, but not modify the configuration
	 * (other than from within an {@link #initDao} implementation).
	 * Consider creating a custom HibernateTemplate instance via
	 * <code>new HibernateTemplate(getSessionFactory())</code>, in which
	 * case you're allowed to customize the settings on the resulting instance.
	 */  
    HibernateTemplate getTemplate();  
}

