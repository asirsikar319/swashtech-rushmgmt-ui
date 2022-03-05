package com.swashtech.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.json.JSONObject;
import org.primefaces.PrimeFaces;

import com.swashtech.utils.CommonUtils;
import com.swashtech.utils.RestClient;

import lombok.Data;

@Data
@Named
@ViewScoped
public class BookSlotOnlineBean implements Serializable {

	private String onName;
	private Integer onMob;
	private Date booktime;
	private String tokenNo;

	@Inject
	RushMgmtView rushMgmtView;

	public BookSlotOnlineBean() {
		System.err.println("BookSlotOnlineBean Constructor");
	}

	public void bookSlot() {
		try {
			JSONObject request = new JSONObject();
			request.put("orgId", "ORG001");
			request.put("oprId", "ORG001-OPR001");
			request.put("name", onName);
			request.put("mobile", onMob);
			request.put("mode", "online");
			request.put("entryDateTime", booktime.getTime());
			System.err.println("booktime : "+booktime);
			System.err.println(TimeZone.getAvailableIDs());
			RestClient restClient = new RestClient();
			String response = restClient.callPOSTApi(CommonUtils.getValueFromConfig("bookSlotOnline"),
					request.toString());
			JSONObject responseObject = new JSONObject(response);
			FacesMessage message;
			if ("Success".equals(responseObject.getString("status"))) {
				tokenNo = responseObject.getString("tokenNo");
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, tokenNo,
						"Please use above token number at the time of visit!");
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", responseObject.getString("message"));
			}
			rushMgmtView.addMessage(message);
			PrimeFaces current = PrimeFaces.current();
			current.executeScript("PF('dlg3').hide();");
			onName = null;
			onMob = null;
			booktime = null;
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

	public Date getBooktime() {
		return booktime;
	}

	public void setBooktime(Date booktime) {
		this.booktime = booktime;
	}

	public String getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

}
