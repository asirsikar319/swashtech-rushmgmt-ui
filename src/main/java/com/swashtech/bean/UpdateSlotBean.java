package com.swashtech.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.json.JSONObject;

import com.swashtech.model.Slots;
import com.swashtech.utils.CommonUtils;
import com.swashtech.utils.RestClient;

import lombok.Data;

@Data
@Named
@ViewScoped
public class UpdateSlotBean implements Serializable {

	private String tokenNo;

	@Inject
	RushMgmtView rushMgmtView;

	public UpdateSlotBean() {
		System.err.println("UpdateSlotBean Constructor");
	}

	public void updateSlot() {
		try {
			JSONObject request = new JSONObject();
			request.put("tokenNo", tokenNo);
			RestClient restClient = new RestClient();
			String response = restClient.callPUTApi(CommonUtils.getValueFromConfig("updateSlot"), request.toString());
			System.err.println(response);
			JSONObject responseObject = new JSONObject(response);
			FacesMessage message;
			if ("Success".equals(responseObject.getString("status"))) {
				tokenNo = responseObject.getString("tokenNo");
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, tokenNo, "Token Updated!");
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, tokenNo, responseObject.getString("message"));
			}
			rushMgmtView.addMessage(message);
			tokenNo = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

}
