package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.HolidayDAO;

/**
 *  処理を担当するクラス
 *
 */
public class GetHolidayListLogic {
	/**
	 * アイテム一覧を取得して返却する
	 *
	 * @return beanList
	 */

	public List<Holiday> execute() {

		// Mutterのインスタンスを生成する
		HolidayDAO dao = new HolidayDAO();

		// daoを通して全てのつぶやき履歴を取得して返す
		List<Holiday> holidayList = dao.findAll();


		return holidayList;
	}

	/**
	 *  祝日を取得して返却する
	 *
	 * @return Holiday
	 */
	public Holiday getHolidayById(int holiday_id) {
		//DAOオブジェクト生成
		HolidayDAO dao = new HolidayDAO();
		//DBから全レコードを取得する
		Holiday holiday = dao.findHolidayById(holiday_id);
		return holiday;
	}

	/**
	 * 最大idの取得
	 * @param id
	 * @return
	 */
	public int getMaxId() {
		HolidayDAO dao = new HolidayDAO();
		int id = dao.findMaxId();
		if(id > 0) {
			return id;
		}else {
			return 0;
		}
	}

	/**
	 * 前回のデータの削除
	 * @param max_id
	 * @return
	 */
	public boolean deleteLastTimeHolidayList(int max_id) {
		HolidayDAO dao = new HolidayDAO();
		if(dao.deleteLastTime(max_id) != false) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 新規一括登録
	 * @param holidayList
	 * @return
	 */
	public boolean createHolidayList(List<Holiday> holidayList) {
		// 入力項目のチェック
		for(int i=0; i<holidayList.size(); i++) {
			boolean flg = inputValidation(holidayList.get(i));
			// flg==falseの時、return flg
			if(!flg) {
				return flg;
			}
		}
		HolidayDAO dao = new HolidayDAO();
		if(dao.createAtOnce(holidayList) != false) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 新規祝日情報登録
	 * @param holiday
	 * @return
	 */
	public boolean createHoliday(Holiday holiday) {
		// 入力項目のチェック
		boolean flg = inputValidation(holiday);
		// flg==falseの時、return flg
		if(!flg) {
			return flg;
		// それ以外、処理を実行
		} else {
			HolidayDAO dao = new HolidayDAO();
			if(dao.insert(holiday) != false) {
				return true;
			}else {
				return false;
			}
		}
	}

	/**
	 *  祝日の内容を更新
	 * @param holiday
	 * @return
	 */
	public boolean updateHoliday(Holiday holiday) {
		// 入力項目のチェック
		boolean flg = inputValidation(holiday);
		// flg==falseの時、return flg
		if(!flg) {
			return flg;
		// それ以外、処理を実行
		} else {
			HolidayDAO dao = new HolidayDAO();
			if(dao.update(holiday) != false) {
				return true;
			}else {
				return false;
			}
		}
	}

	/**
	 * 指定祝日情報を削除
	 * @param id
	 * @return
	 */
	public boolean deleteHoliday(int id) {
		return false;

//		HolidayDAO dao = new HolidayDAO();
//		if(dao.delete(id) != false) {
//			return true;
//		}else {
//			return false;
//		}
	}

	/**
	 *  祝日の年を取得して返却する
	 *
	 * @return int
	 */
	public int getHolidayYear(Holiday holiday) {
		// holidayの年数を取得
		Date dt = holiday.getDate();
		// SimpleDateFormatインスタンスを生成 "yyyy"出力
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		// String 変換
		String datey = ysdf.format(dt);
		// int 変換
		int year = Integer.parseInt(datey);
		return year;
	}

	/**
	 *  祝日の月を取得して返却する
	 *
	 * @return int
	 */
	public int getHolidayMonth(Holiday holiday) {
		// holidayの月を取得
		Date dt = holiday.getDate();
		// SimpleDateFormatインスタンスを生成 "MM"出力
		SimpleDateFormat msdf = new SimpleDateFormat("MM");
		// String 変換
		String datem = msdf.format(dt);
		// int 変換
		int month = Integer.parseInt(datem);
		return month;
	}

	/**
	 *  祝日の日を取得して返却する
	 *
	 * @return int
	 */
	public int getHolidayDay(Holiday holiday) {
		// holidayの月を取得
		Date dt = holiday.getDate();
		// SimpleDateFormatインスタンスを生成 "dd"出力
		SimpleDateFormat dsdf = new SimpleDateFormat("dd");
		// String 変換
		String dated = dsdf.format(dt);
		// int 変換
		int day = Integer.parseInt(dated);
		return day;
	}

	/**
	 *  year, month, day からDateを返却する
	 *
	 * @return Date
	 */
	public Date getDateByYMD(int year, int month, int day) {

		// Calendar型のmonthに修正する
		int month_cal = month-1;

		// Calendarインスタンスを作成
		Calendar cal_i = Calendar.getInstance();
		// 渡された日時の年月の初日に設定
		cal_i.set(year, month_cal, 1);
    	// その月の日数の最大を取得
    	int lastDayOfMonth = cal_i.getActualMaximum(Calendar.DATE);
    	// 日時を確定
    	Calendar cal = Calendar.getInstance();
    	if(day >= lastDayOfMonth) {
	        cal.set(year, month_cal, lastDayOfMonth);
    	} else {
	        cal.set(year, month_cal, day);
    	}

    	// Calendar→String
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = sdf.format(cal.getTime());
        // String→Date
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
			date = sdFormat.parse(strDate);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

//		// Date変換
//    	Date date = cl.getTime();

//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//
//        System.out.println(df.format(date));

    	return date;
	}

	/**
	 * 入力のチェック関数
	 * @param holiday
	 * @return boolean
	 */
	public boolean inputValidation(Holiday holiday) {
		// name 取得
		String name = holiday.getName();
		Date date = holiday.getDate();

		// 入力不備の時、return false
		if(name.isEmpty()) {
			return false;
		} else if(name.matches(".*<.*") && name.matches(".*>.*")) {
			return false;
		} else if(date==null) {
			return false;
		}
		// 入力OKの時、
		return true;
	}
}
