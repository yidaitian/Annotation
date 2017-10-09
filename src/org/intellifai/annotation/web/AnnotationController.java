package org.intellifai.annotation.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
public class AnnotationController {

	private static final Logger log = LoggerFactory.getLogger(AnnotationController.class);
	
	@RequestMapping(value="annotation/annotation", method=RequestMethod.GET)
	@RequiresPermissions("assignjob:-")
	public String showAnnotation(Model model) {
		
		
		return "annotation/annotation";
	}

}
