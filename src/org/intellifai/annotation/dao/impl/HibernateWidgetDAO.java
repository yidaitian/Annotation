package org.intellifai.annotation.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import org.intellifai.annotation.hibernate.BaseDaoImpl;
import org.intellifai.annotation.dao.WidgetDAO;
import org.intellifai.annotation.model.SelectTag;

/**
 * A dao interface for generate select input widget.
 */
@Repository
@SuppressWarnings("unchecked")
public class HibernateWidgetDAO extends BaseDaoImpl implements WidgetDAO{

	@Override
	public List<Map<String, String>> getSelectOptions(String selectId) {
		String queryString = "from SelectTag where selectId=?";
		List<SelectTag> selectTag = getTemplate().find(queryString, selectId);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < selectTag.size() ; i++) {
			SelectTag st = selectTag.get(i);
			Map<String, String> map = new HashMap<String, String>();
			map.put(st.getLabel(), st.getValue());
			list.add(map);
		}
		return list;
	}
}
