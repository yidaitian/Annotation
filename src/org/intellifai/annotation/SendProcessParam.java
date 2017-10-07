package org.intellifai.annotation;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-7 下午3:04:10
 * @version V2.0
 */
public class SendProcessParam {

	private String CaseUID;
	private String StudyInstanceUID;
	private String FileType;
	private String ImagesPath;
	
	public String getCaseUID(){
		return this.CaseUID;
	}
	public void setCaseUID(String caseUID){
		this.CaseUID = caseUID;
	}
	
	public String getStudyInstanceUID(){
		return this.StudyInstanceUID;
	}
	public void setStudyInstanceUID(String studyInstanceUID){
		this.StudyInstanceUID = studyInstanceUID;
	}
	
	public String getFileType(){
		return this.FileType;
	}
	public void setFileType(String fileType){
		this.FileType = fileType;
	}
	
	public String getImagesPath(){
		return this.ImagesPath;
	}
	public void setImagesPath(String imagesPath){
		this.ImagesPath = imagesPath;
	}
	
}

