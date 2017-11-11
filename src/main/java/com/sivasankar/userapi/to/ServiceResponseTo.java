package com.sivasankar.userapi.to;

import java.util.List;

public class ServiceResponseTo {
	private String resMsg;
	private String userId;
	private List<ValidationError> valErrors;

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ValidationError> getValErrors() {
		return valErrors;
	}

	public void setValErrors(List<ValidationError> valErrors) {
		this.valErrors = valErrors;
	}
}
