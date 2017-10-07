package org.intellifai.annotation.dao;

import java.util.List;
import java.util.Map;

/** 
 * A dao interface for generate select input widget.
 */
public interface WidgetDAO {

	List<Map<String, String>> getSelectOptions(String selectId);
}
