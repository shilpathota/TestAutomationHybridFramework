package com.mbrdi.gsp.test.dto;

public class TestDataDTO {

	private String dataReference;
	private String data;

	public String getDataReference() {
		return dataReference;
	}
	public void setDataReference(String dataReference) {
		this.dataReference = dataReference;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "TestDataDTO [dataReference=" + dataReference + ", data=" + data + "]";
	}

}
