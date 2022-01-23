package com.swashtech.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.json.JSONObject;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import com.swashtech.model.Slots;
import com.swashtech.utils.CommonUtils;
import com.swashtech.utils.RestClient;

import lombok.Data;

@Data
@Named
@ViewScoped
public class RushMgmtView implements Serializable {

	private DashboardModel model;
	private Date slottime;
	private String onName;
	private Integer onMob;
	private Date booktime;
	private String onToken;
	private List<Slots> availableSlots;

	public RushMgmtView() {
		System.err.println("RushMgmtView Constructor");
	}

	@PostConstruct
	public void init() {
		model = new DefaultDashboardModel();
		DashboardColumn column1 = new DefaultDashboardColumn();
		DashboardColumn column2 = new DefaultDashboardColumn();
		DashboardColumn column3 = new DefaultDashboardColumn();

		column1.addWidget("availableSlots");

		column2.addWidget("bookSlotOnline");
		column2.addWidget("cancelSlot");

		column3.addWidget("bookSlotOffline");
		column3.addWidget("updateSlot");
		column3.addWidget("releaseSlot");

		model.addColumn(column1);
		model.addColumn(column2);
		model.addColumn(column3);
	}

	public void handleReorder(DashboardReorderEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("Reordered: " + event.getWidgetId());
		message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex()
				+ ", Sender index: " + event.getSenderColumnIndex());

		addMessage(message);
	}

	public void handleClose(CloseEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed",
				"Closed panel id:'" + event.getComponent().getId() + "'");

		addMessage(message);
	}

	public void handleToggle(ToggleEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled",
				"Status:" + event.getVisibility().name());

		addMessage(message);
	}

	public void retrieveSlots() {
		try {
			System.out.println("Retrieve Slots : " + slottime);

			String str = "{\r\n" + "	\"orgId\" : \"ORG001\",\r\n" + "	\"oprId\" : \"ORG001-OPR001\",\r\n"
					+ "	\"timestamp\" : 1642354380000\r\n" + "}";
			RestClient restClient = new RestClient();
			String str2 = restClient.callRestApi(CommonUtils.getValueFromConfig("availableslotEndpoint"), "POST", str);
			JSONObject jsonObject = new JSONObject(str2);
			System.err.println("jsonObject : " + jsonObject);
			availableSlots = new ArrayList<Slots>();
			jsonObject.getJSONArray("availableInDuration").forEach(item -> {
				availableSlots.add(new Slots(new Date(Long.parseLong(String.valueOf(item)))));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void bookSlot(ActionEvent event) {
		System.out.println("test");
		System.err.println(event.getComponent().getId());
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public DashboardModel getModel() {
		return model;
	}

	public Date getSlottime() {
		return slottime;
	}

	public void setSlottime(Date slottime) {
		this.slottime = slottime;
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

	public String getOnToken() {
		return onToken;
	}

	public void setOnToken(String onToken) {
		this.onToken = onToken;
	}

	public List<Slots> getAvailableSlots() {
		return availableSlots;
	}

	public void setAvailableSlots(List<Slots> availableSlots) {
		this.availableSlots = availableSlots;
	}
}
