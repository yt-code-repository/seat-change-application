package model;

import java.util.List;

public class HolidayList {
	private static List<Holiday> HolidayList;

	public static List<Holiday> getHolidayList() {
		return HolidayList;
	}

	public static void setHolidayList(List<Holiday> holidayList) {
		HolidayList = holidayList;
	}



}
