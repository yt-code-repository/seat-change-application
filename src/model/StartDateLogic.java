package model;

import java.util.Calendar;
import java.util.List;

public class StartDateLogic {

	/**
	 *
	 * @param int year, int month, int day
	 * @return
	 * start_date[0]:今年の年数
	 * start_date[1]:年数
	 * start_date[2]:月数
 	 * start_date[3]:日数
 	 * start_date[4]:曜日番号
	 */
	public int[] executeByWeek(int selected_week) {

		// カレンダー日付代入
		Calendar cal = Calendar.getInstance();

		// 今年の年数取得
		int current_year = cal.get(Calendar.YEAR);

		// 今日の日付の曜日を取得
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK);

		// 曜日によって、来週の月曜日になるようにスタートする日を変える
		// 日曜日
		if(day_of_week == 1) {cal.add(Calendar.DATE, 8);}
		// 月曜日
		if(day_of_week == 2) {cal.add(Calendar.DATE, 7);}
		// 火曜日
		if(day_of_week == 3) {cal.add(Calendar.DATE, 6);}
		// 水曜日
		if(day_of_week == 4) {cal.add(Calendar.DATE, 5);}
		// 木曜日
		if(day_of_week == 5) {cal.add(Calendar.DATE, 4);}
		// 金曜日
		if(day_of_week == 6) {cal.add(Calendar.DATE, 3);}
		// 土曜日
		if(day_of_week == 7) {cal.add(Calendar.DATE, 2);}

		// すべての祝日のデータを holidayList として取得
		List<Holiday> holidayList = HolidayList.getHolidayList();

		// holidayインスタンスして、n_holidayサイズの取得
		Calendar holiday = Calendar.getInstance();
		int n_holiday = holidayList.size();

		// calとholidayを比較
		for(int i=0; i<n_holiday; i++) {
	        holiday.setTime(holidayList.get(i).getDate());

	        int cal_year = cal.get(Calendar.YEAR);
	        int cal_month = cal.get(Calendar.MONTH)+1;
	        int cal_date = cal.get(Calendar.DATE);
	        int holiday_year = holiday.get(Calendar.YEAR);
	        int holiday_month = holiday.get(Calendar.MONTH)+1;
	        int holiday_date = holiday.get(Calendar.DATE);

			if (cal_year==holiday_year && cal_month==holiday_month && cal_date==holiday_date) {
				cal.add(Calendar.DATE, 1);
			}
		}

		// 選択された週によって平行移動
		// 今週スタート
		if(selected_week == 0) {cal.add(Calendar.DATE, -7);}
		// 来週スタート
		if(selected_week == 1) {cal.add(Calendar.DATE,  0);}
		// 再来週スタート
		if(selected_week == 2) {cal.add(Calendar.DATE, +7);}

		int start_date[] = new int[5];

		start_date[0] = current_year;
		start_date[1] = cal.get(Calendar.YEAR);
		start_date[2] = cal.get(Calendar.MONTH) + 1;
		start_date[3] = cal.get(Calendar.DATE);
		start_date[4] = cal.get(Calendar.DAY_OF_WEEK);

		return start_date;
	}

	/**
	 *
	 * @param int year, int month, int day
	 * @return
	 * start_date[0]:今年の年数
	 * start_date[1]:年数
	 * start_date[2]:月数
	 * start_date[3]:日数
	 * start_date[4]:曜日番号
	 */
	public int[] executeByDate(int year, int month, int day) {

		// Calendar型のmonthに修正する
		int month_cal = month - 1;

		// Calendarインスタンスを作成
		Calendar cal_i = Calendar.getInstance();
		// 渡された日時の年月の初日に設定
		cal_i.set(year, month_cal, 1);
    	// その月の日数の最大を取得
    	int lastDayOfMonth = cal_i.getActualMaximum(Calendar.DATE);
    	// 日時のインスタンスを生成
    	Calendar cal = Calendar.getInstance();
		// 今年の年数取得
		int current_year = cal.get(Calendar.YEAR);
		// 日時を確定
    	if(day >= lastDayOfMonth) {
	        cal.set(year, month_cal, lastDayOfMonth);
    	} else {
	        cal.set(year, month_cal, day);
    	}

		// すべての祝日のデータを holidayList として取得
		List<Holiday> holidayList = HolidayList.getHolidayList();

		// holidayインスタンスして、n_holidayサイズの取得
		Calendar holiday = Calendar.getInstance();
		int n_holiday = holidayList.size();

		// calとholidayを比較
		for(int i=0; i<n_holiday; i++) {
			// holidayインスタンスをholidayListのi番目の日付に設定
	        holiday.setTime(holidayList.get(i).getDate());

	        // calとholidayを比較する
	        int cal_year = cal.get(Calendar.YEAR);
	        int cal_month = cal.get(Calendar.MONTH)+1;
	        int cal_date = cal.get(Calendar.DATE);
	        int holiday_year = holiday.get(Calendar.YEAR);
	        int holiday_month = holiday.get(Calendar.MONTH)+1;
	        int holiday_date = holiday.get(Calendar.DATE);

	        // 等しい時、calを+1する
			if (cal_year==holiday_year && cal_month==holiday_month && cal_date==holiday_date) {
				cal.add(Calendar.DATE, 1);
			}
		}

		// calの曜日を取得
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK);

		// 曜日によって、来週の月曜日になるようにスタートする日を変える
		// 土曜日
		if(day_of_week == 7) {cal.add(Calendar.DATE, 2);}
		// 日曜日
		if(day_of_week == 1) {cal.add(Calendar.DATE, 1);}



		// 戻り値に代入
		int start_date[] = new int[5];
		// 今年の年数取得
		start_date[0] = current_year;
		start_date[1] = cal.get(Calendar.YEAR);
		start_date[2] = cal.get(Calendar.MONTH) + 1;
		start_date[3] = cal.get(Calendar.DATE);
		start_date[4] = cal.get(Calendar.DAY_OF_WEEK);

		// リターン
		return start_date;
	}


	/**
	 * Servletから使用
	 * @param day
	 * @return 曜日のString
	 */
	public String day_of_week(int day) {

		String day_of_week = "";

		if (day == 1) { day_of_week = "日";}
		if (day == 2) { day_of_week = "月";}
		if (day == 3) { day_of_week = "火";}
		if (day == 4) { day_of_week = "水";}
		if (day == 5) { day_of_week = "木";}
		if (day == 6) { day_of_week = "金";}
		if (day == 7) { day_of_week = "土";}

		return day_of_week;
	}
}
