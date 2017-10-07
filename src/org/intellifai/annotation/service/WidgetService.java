package org.intellifai.annotation.service;

import java.util.List;
import java.util.Map;

/**
 * A service interface for generate select input widget.
 */
public interface WidgetService {

    List<Map<String, String>> getSelectOptions(String selectId);
}
