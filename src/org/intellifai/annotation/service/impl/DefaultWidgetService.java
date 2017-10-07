package org.intellifai.annotation.service.impl;

import java.util.List;
import java.util.Map;

import org.intellifai.annotation.dao.WidgetDAO;
import org.intellifai.annotation.service.WidgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Default implementation of the {@link WidgetService} interface.  
 * This service implements operations related to select input widget data.
 * 
 */
@Transactional
@Service
public class DefaultWidgetService implements WidgetService {
	
    private WidgetDAO widgetDAO;
	/**
	 * @param widgetDAO the widgetDAO to set
	 */
	public void setWidgetDAO(WidgetDAO widgetDAO) {
		this.widgetDAO = widgetDAO;
	}

	@Override
	public List<Map<String, String>> getSelectOptions(String selectId) {
		return widgetDAO.getSelectOptions(selectId);
	}

}
