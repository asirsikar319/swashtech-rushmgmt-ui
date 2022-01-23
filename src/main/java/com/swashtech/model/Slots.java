package com.swashtech.model;

import java.util.Date;

public class Slots {

	private Date availSlot;

	public Slots(Date availSlot) {
		this.availSlot = availSlot;
	}

	public Date getAvailSlot() {
		return availSlot;
	}

	public void setAvailSlot(Date availSlot) {
		this.availSlot = availSlot;
	}

}
