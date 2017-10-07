package org.intellifai.annotation.web.util;

import org.springframework.ui.Model;
import org.intellifai.annotation.web.ConfigUtil;


public class Notification {

	private String classType;
	private String title;
	private String message;
	
	public Notification(){
		
	}///~~~
	
	public Notification(String classType, String title, String message) {
		this.classType = classType;
		this.title = title;
		this.message = message;
	}
	
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the classType
	 */
	public String getClassType() {
		return classType;
	}
	/**
	 * @param classType the classType to set
	 */
	public void setClassType(String classType) {
		this.classType = classType;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static void setMsg(Model model,String type) {
		Notification notification =  new Notification();
		if(type.equalsIgnoreCase("success")){
			notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
	        notification.setTitle(ConfigUtil.getConfig().getString("msg.title.delete"));
	        notification.setMessage(ConfigUtil.getConfig().getString("msg.message.delete"));
		}else if(type.equalsIgnoreCase("error")){
			notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
	        notification.setTitle(ConfigUtil.getConfig().getString("msg.title.delete"));
	        notification.setMessage(ConfigUtil.getConfig().getString("msg.message.delete"));
		}else if(type.equalsIgnoreCase("warning")){
			notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
	        notification.setTitle(ConfigUtil.getConfig().getString("msg.title.delete"));
	        notification.setMessage(ConfigUtil.getConfig().getString("msg.message.delete"));
		}else if(type.equalsIgnoreCase("info")){
			notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
	        notification.setTitle(ConfigUtil.getConfig().getString("msg.title.delete"));
	        notification.setMessage(ConfigUtil.getConfig().getString("msg.message.delete"));
		}else{
			notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
	        notification.setTitle(ConfigUtil.getConfig().getString("msg.title.delete"));
	        notification.setMessage(ConfigUtil.getConfig().getString("msg.message.delete"));
		}
        model.addAttribute("Notification",notification);
	}
	
	public static Notification createAddNotification(){
		Notification notification =  new Notification();
        notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
        notification.setTitle(ConfigUtil.getConfig().getString("msg.title.add"));
        notification.setMessage(ConfigUtil.getConfig().getString("msg.message.add"));
        return notification;
	}
	public static Notification createUpdateNotification(){
    	Notification notification =  new Notification();
        notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
        notification.setTitle(ConfigUtil.getConfig().getString("msg.title.update"));
        notification.setMessage(ConfigUtil.getConfig().getString("msg.message.update"));
        return notification;
	}
	public static Notification createDeleteNotification(){
		Notification notification =  new Notification();
        notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
        notification.setTitle(ConfigUtil.getConfig().getString("msg.title.delete"));
        notification.setMessage(ConfigUtil.getConfig().getString("msg.message.delete"));
        return notification;
	}
	public static Notification createDeletePartNotification(){
		Notification notification =  new Notification();
        notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
        notification.setTitle(ConfigUtil.getConfig().getString("msg.title.delete"));
        notification.setMessage(ConfigUtil.getConfig().getString("msg.message.deletePart"));
        return notification;
	}
	public static Notification createCreateNotification(){
		Notification notification =  new Notification();
        notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
        notification.setTitle(ConfigUtil.getConfig().getString("msg.title.create"));
        notification.setMessage(ConfigUtil.getConfig().getString("msg.message.create"));
        
        return notification;
	}
	public static Notification createSendNotification(){
		Notification notification =  new Notification();
        notification.setClassType(ConfigUtil.getConfig().getString("css.class.success"));
        notification.setTitle(ConfigUtil.getConfig().getString("msg.title.send"));
        notification.setMessage(ConfigUtil.getConfig().getString("msg.message.sendSuccess"));
        
        return notification;
	}
	
	public static Notification createNoUpHospitalNotification(){
		String classType = ConfigUtil.getConfig().getString("css.class.info");
		String title = ConfigUtil.getConfig().getString("msg.title.send");
		String msg = ConfigUtil.getConfig().getString("msg.message.noHospital");
        
        return new Notification(classType,title,msg);
	}
	
	public static Notification createNoTransferHospitalNotification(){
		String classType = ConfigUtil.getConfig().getString("css.class.info");
		String title = ConfigUtil.getConfig().getString("msg.title.send");
		String msg = ConfigUtil.getConfig().getString("msg.message.noHospital");
        
        return new Notification(classType,title,msg);
	}
	
	public static Notification createNotSamePatientNotification(){
		String classType = ConfigUtil.getConfig().getString("css.class.info");
		String title = ConfigUtil.getConfig().getString("msg.title.send");
		String msg = ConfigUtil.getConfig().getString("msg.message.notSamePatient");
        
        return new Notification(classType,title,msg);
	}
	
	public static Notification createHaveFinishedNotification(){
		String classType = ConfigUtil.getConfig().getString("css.class.info");
		String title = ConfigUtil.getConfig().getString("msg.title.report");
		String msg = ConfigUtil.getConfig().getString("msg.message.haveFinished");
        
        return new Notification(classType,title,msg);
	}
	
	public static Notification createIsReportingNotification(){
		String classType = ConfigUtil.getConfig().getString("css.class.info");
		String title = ConfigUtil.getConfig().getString("msg.title.report");
		String msg = ConfigUtil.getConfig().getString("msg.message.isReporting");
        
        return new Notification(classType,title,msg);
	}
	
	public static Notification createNoInfoNotification(){
		String classType = ConfigUtil.getConfig().getString("css.class.info");
		String title = ConfigUtil.getConfig().getString("msg.title.report");
		String msg = ConfigUtil.getConfig().getString("msg.message.noInfo");
        
        return new Notification(classType,title,msg);
	}
	
}
