package com.kronos.io.config.configbuilder.node;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.kronos.io.config.configbuilder.Block;

public class DateTimeBlock extends Block {
	LocalDateTime dateTimeValue;

	public DateTimeBlock(String name, LocalDateTime dateTimeValue) {
		super(false, name, new String[] { dateTimeValue.toString() });
		this.dateTimeValue = dateTimeValue;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// Extracting date-time value
		int dateTimeStartIndex = val.indexOf("\"") + 1;
		int dateTimeEndIndex = val.lastIndexOf("\"");
		String dateTimeStr = val.substring(dateTimeStartIndex, dateTimeEndIndex);
		this.dateTimeValue = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	@Override
	public String blockOut(String pref, String app, int l) {
		return pref + getName() + "=\"" + dateTimeValue.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\";";
	}

	public LocalDateTime getDateTimeValue() {
		return dateTimeValue;
	}

	public void setDateTimeValue(LocalDateTime dateTimeValue) {
		this.dateTimeValue = dateTimeValue;
	}
}
