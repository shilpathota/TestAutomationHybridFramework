package com.mbrdi.gsp.test.dto;

public class TestCaseDTO {

	private String reusableName;
	private String testCaseId;
	private long stepNumber;
	private String screen;
	private String controlName;
	private String action;
	private String data;
	private boolean optional;
	private boolean enabled;

	private ControlDTO controlDTO;

	public String getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	public long getStepNumber() {
		return stepNumber;
	}
	public void setStepNumber(long stepNumber) {
		this.stepNumber = stepNumber;
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public boolean isOptional() {
		return optional;
	}
	public void setOptional(boolean optional) {
		this.optional = optional;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getReusableName() {
		return reusableName;
	}
	public void setReusableName(String reusableName) {
		this.reusableName = reusableName;
	}
	public ControlDTO getControlDTO() {
		return controlDTO;
	}
	public void setControlDTO(ControlDTO mappingsDTO) {
		this.controlDTO = mappingsDTO;
	}
	@Override
	public String toString() {
		return "TestCaseDTO [reusableName=" + reusableName + ", testCaseId=" + testCaseId + ", stepNumber=" + stepNumber
				+ ", screen=" + screen + ", controlName=" + controlName + ", action=" + action + ", data=" + data
				+ ", optional=" + optional + ", enabled=" + enabled + ", controlDTO=" + controlDTO + "]";
	}
}
