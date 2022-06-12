package com.swashtech.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.json.JSONObject;
import org.primefaces.PrimeFaces;
import org.primefaces.component.commandbutton.CommandButton;

import com.swashtech.model.Slots;
import com.swashtech.utils.CommonUtils;
import com.swashtech.utils.RestClient;

import lombok.Data;

@Data
@Named
@ViewScoped
public class AvailableSlotBean implements Serializable {

	private Date timestamp;
	private List<Slots> lstAvailableSlots;
	private Integer totalSlots;
	private Integer availableSlots;
	private Date minDate;
	private Date maxDate;

	@Inject
	BookSlotOnlineBean bookSlotOnlineBean;

	public AvailableSlotBean() {
		System.err.println("AvailableSlotBean Constructor");
	}
	
	@PostConstruct
	public void init() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -5);
		minDate = new Date(cal.getTimeInMillis());
		cal.add(Calendar.DAY_OF_MONTH, 2);
		maxDate = new Date(cal.getTimeInMillis());
	}

	public void retrieveSlots() {
		try {
			JSONObject request = new JSONObject();
			request.put("orgId", "ORG001");
			request.put("oprId", "ORG001-OPR001");
			request.put("timestamp", timestamp.getTime());
			RestClient restClient = new RestClient();
			String response = restClient.callPOSTApi(CommonUtils.getValueFromConfig("availableSlots"),
					request.toString());
			JSONObject responseObject = new JSONObject(response);
			availableSlots = responseObject.getInt("availableSlots");
			totalSlots = responseObject.getInt("totalSlots");
			lstAvailableSlots = new ArrayList<Slots>();
			responseObject.getJSONArray("availableInDuration").forEach(item -> {
				Date date = new Date(Long.parseLong(String.valueOf(item)));
				SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm");
//				sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
				lstAvailableSlots.add(new Slots(sdf.format(date)));
			});
			PrimeFaces current = PrimeFaces.current();
			current.executeScript("PF('dlg4').show();");
			
			String response2 = restClient.callGETApi(null);
			System.err.println("response2 : "+response2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void bookSlot(ActionEvent event) {
		try {
			CommandButton commandButton = (CommandButton) event.getComponent();
			System.err.println(commandButton.getValue());
			SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm");
			Date date = sdf.parse(commandButton.getValue().toString());
			date.setYear(new Date().getYear());
			System.err.println("date : " + date);
			bookSlotOnlineBean.setBooktime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<Slots> getLstAvailableSlots() {
		return lstAvailableSlots;
	}

	public void setLstAvailableSlots(List<Slots> lstAvailableSlots) {
		this.lstAvailableSlots = lstAvailableSlots;
	}

	public Integer getTotalSlots() {
		return totalSlots;
	}

	public void setTotalSlots(Integer totalSlots) {
		this.totalSlots = totalSlots;
	}

	public Integer getAvailableSlots() {
		return availableSlots;
	}

	public void setAvailableSlots(Integer availableSlots) {
		this.availableSlots = availableSlots;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

}
