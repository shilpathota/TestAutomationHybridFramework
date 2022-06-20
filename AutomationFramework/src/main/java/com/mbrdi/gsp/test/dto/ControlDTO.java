package com.mbrdi.gsp.test.dto;

public class ControlDTO {

	private String id;
	private String screen;
	private String controlName;
	private String controlTag;
	private String controlDetails;
	private String parentDetails;
	private String windowTag;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public String getControlName() {
		return controlName;
	}
	public void setControlName(String controlName) {
		this.controlName = controlName;
	}
	public String getControlTag() {
		return controlTag;
	}
	public void setControlTag(String controlTag) {
		this.controlTag = controlTag;
	}
	public String getControlDetails() {
		return controlDetails;
	}
	public void setControlDetails(String controlDetails) {
		this.controlDetails = controlDetails;
	}
	public String getParentDetails() {
		return parentDetails;
	}
	public void setParentDetails(String parentDetails) {
		this.parentDetails = parentDetails;
	}
	public String getWindowTag() {
		return windowTag;
	}
	public void setWindowTag(String windowTag) {
		this.windowTag = windowTag;
	}
	@Override
	public String toString() {
		return "ControlDTO [id=" + id + ", screen=" + screen + ", controlName=" + controlName + ", controlTag="
				+ controlTag + ", controlDetails=" + controlDetails + ", parentDetails=" + parentDetails
				+ ", windowTag=" + windowTag + "]";
	}
}
