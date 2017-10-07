/**
 * @(#)ConfigUtil.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.web;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 上午11:28:20
 * @version V2.0
 */
public class ConfigUtil {

	private static final Logger log = LoggerFactory.getLogger(ConfigUtil.class);

	/**
	 * @return configuration of Notification.properties
	 */
	public static CompositeConfiguration getConfig() {
		CompositeConfiguration config = new CompositeConfiguration(); 
		try {
			config.addConfiguration(new PropertiesConfiguration("notification.properties"));
		} catch (ConfigurationException e) {
			log.error("manageUsers.....",e.getMessage());
		}
		return config;
	}
}

