package com.mbrdi.gsp.test.dto;

public class TestCaseDefinitionDTO {

	private String testCaseId;
	private String pathToTestCase;
	private boolean enabled;
	private String result; 
	private String remarks;
	private String browser;

	public String getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	public String getPathToTestCase() {
		return pathToTestCase;
	}
	public void setPathToTestCase(String pathToTestCase) {
		this.pathToTestCase = pathToTestCase;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String remarks) {
		this.remarks = browser;
	}
	@Override
	public String toString() {
		return "TestCaseDefinitionDTO [testCaseId=" + testCaseId + ", pathToTestCase=" + pathToTestCase + ", enabled="
				+ enabled + ", result=" + result + ", remarks=" + remarks + " browser=" + browser + "]";
	}
}
