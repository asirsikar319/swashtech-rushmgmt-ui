package com.swashtech.bean;

import java.io.Serializable;
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
public class BookSlotOfflineBean implements Serializable {

	private String onName;
	private Integer onMob;
	private String tokenNo;

	@Inject
	RushMgmtView rushMgmtView;

	public BookSlotOfflineBean() {
		System.err.println("BookSlotOfflineBean Constructor");
	}

	public void bookSlot() {
		try {
			JSONObject request = new JSONObject();
			request.put("orgId", "ORG001");
			request.put("oprId", "ORG001-OPR001");
			request.put("name", onName);
			request.put("mobile", onMob);
			request.put("mode", "offline");
			RestClient restClient = new RestClient();
			String response = restClient.callPOSTApi(CommonUtils.getValueFromConfig("bookSlotOffline"),
					request.toString());
			JSONObject responseObject = new JSONObject(response);
			FacesMessage message;
			if ("Success".equals(responseObject.getString("status"))) {
				tokenNo = responseObject.getString("tokenNo");
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, tokenNo,
						"Please share token number to visitor!");
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, tokenNo!=null?tokenNo:"Error", responseObject.getString("message"));
			}
			rushMgmtView.addMessage(message);
			onName = null;
			onMob = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getOnName() {
		return onName;
	}

	public void setOnName(String onName) {
		this.onName = onName;
	}

	public Integer getOnMob() {
		return onMob;
	}

	public void setOnMob(Integer onMob) {
		this.onMob = onMob;
	}

	public String getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

}
