package com.swashtech.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.json.JSONObject;
import org.primefaces.PrimeFaces;

import com.swashtech.model.ReportModel;
import com.swashtech.model.Slots;
import com.swashtech.utils.CommonUtils;
import com.swashtech.utils.RestClient;

import lombok.Data;

@Data
@Named
@ViewScoped
public class SlotReportBean implements Serializable {

	private Date frdate;
	private Date todate;
	private String mode;
	private boolean enter;
	private boolean exit;

	private List<ReportModel> lstReport;

	public SlotReportBean() {
		System.err.println("SlotReportBean Constructor");
	}

	@PostConstruct
	public void init() {

	}

	public void retrieveSlots() {
		try {
			JSONObject request = new JSONObject();
			request.put("orgId", "ORG001");
			request.put("oprId", "ORG001-OPR001");
			request.put("frDate", frdate.getTime());
			request.put("toDate", todate.getTime());
			request.put("isEnter", enter);
			request.put("isExit", exit);
			if(mode !=null && !"".equals(mode)) {
				request.put("mode", mode);
			}
			RestClient restClient = new RestClient();
			String response = restClient.callPOSTApi(CommonUtils.getValueFromConfig("availableSlots2"),
					request.toString());
			JSONObject responseObject = new JSONObject(response);
			lstReport = new ArrayList<ReportModel>();
			responseObject.getJSONArray("availableSlots").forEach(item -> {				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				JSONObject items = (JSONObject) item;
				Date exitdate = new Date(items.getLong("exitDateTime"));
				Date entrydate = new Date(items.getLong("entryDateTime"));
				lstReport.add(new ReportModel(items.getString("orgId"), items.getString("oprId"),
						items.getString("name"), items.getInt("mobile"), items.getString("tokenNo"),
						items.getString("mode"), items.getBoolean("isEnter"), items.getBoolean("isExit"),
						sdf.format(entrydate), sdf.format(exitdate), items.getLong("createdOn")));
			});
			
			PrimeFaces current = PrimeFaces.current();
			current.executeScript("PF('dlg5').show();");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Date getFrdate() {
		return frdate;
	}

	public void setFrdate(Date frdate) {
		this.frdate = frdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public boolean isEnter() {
		return enter;
	}

	public void setEnter(boolean enter) {
		this.enter = enter;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public List<ReportModel> getLstReport() {
		return lstReport;
	}

	public void setLstReport(List<ReportModel> lstReport) {
		this.lstReport = lstReport;
	}

}
