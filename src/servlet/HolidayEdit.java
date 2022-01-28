package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetHolidayListLogic;
import model.Holiday;

/**
 * Servlet implementation class HolidayEdit
 */
@WebServlet("/HolidayEdit")
public class HolidayEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HolidayEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		// ログインの状態をチェックする
		HttpSession session = request.getSession();
		String loginUser_name = (String) session.getAttribute("loginUser_name");

		// セッションにloginUser_nameない場合 CtrlForCms へリダイレクト
		if(loginUser_name == null) {
			response.sendRedirect("CtrlForCms");
		// セッションにloginUser_nameある場合 以下の処理を実行
		} else {
			String forward = "";
			int holiday_id = 0;
			int fnc = 0;

			//リクエストパラメータを取得する
			String str = null;
			str = request.getParameter("holiday_id");
			if(str != null) {
				holiday_id = Integer.parseInt(str);
			}
			str = request.getParameter("fnc");
			if(str != null) {
				fnc = Integer.parseInt(str);
			}
			request.setAttribute("fnc", fnc);

			// HolidayLogicのオブジェクトを生成
			GetHolidayListLogic getHolidayListLogic = new GetHolidayListLogic();

			// 祝日情報の新規登録
			if(fnc==1) {
				// Calendar インスタンスを生成
				Calendar calendar = Calendar.getInstance();
				// 現在の年月日取得
				int current_year = calendar.get(Calendar.YEAR);
				int current_month = calendar.get(Calendar.MONTH) + 1;
				int current_date = calendar.get(Calendar.DATE);
				// リクエストスコープ保存
				request.setAttribute("current_year", current_year);
				request.setAttribute("current_month", current_month);
				request.setAttribute("current_date", current_date);
				request.setAttribute("errorMsg", null);
				//記事の新規登録
				forward = "/WEB-INF/jsp/holidayEdit.jsp";

			// fnc==0の場合
			} else {
				// 個別祝日編集ページ
				if(holiday_id > 0) {
					//指定したholiday_idで該当祝日情報を読み込む
					Holiday holiday = getHolidayListLogic.getHolidayById(holiday_id);

					if(holiday != null) {
						int holiday_year = getHolidayListLogic.getHolidayYear(holiday);
						int holiday_month = getHolidayListLogic.getHolidayMonth(holiday);
						int holiday_day = getHolidayListLogic.getHolidayDay(holiday);
						request.setAttribute("holiday", holiday);
						request.setAttribute("holiday_year", holiday_year);
						request.setAttribute("holiday_month", holiday_month);
						request.setAttribute("holiday_day", holiday_day);
					}else {
						request.setAttribute("errorMsg", "祝日を読み込むのにエラーが発生しました。");
					}
					//編集画面に移動
					forward = "/WEB-INF/jsp/holidayEdit.jsp";

				// 一覧編集ページ
				}else if(holiday_id == 0){
					// カレンダー日付代入
					Calendar cal = Calendar.getInstance();
					// 今年の年数取得
					int current_year = cal.get(Calendar.YEAR);

					//全祝日情報を読み込み
					List<Holiday> holidayList = getHolidayListLogic.execute();
					//リクエストスコープにpostListを保存する
					if(holidayList != null) {
						// フォワード用配列に代入
						int n_holidayList = holidayList.size();
						int[] id_s =  new int[n_holidayList];
						String[] names = new String[n_holidayList];
						Date[] date_s = new Date[n_holidayList];

						for(int i=0; i<n_holidayList; i++) {
							// 並べ替えた後の名前リストの配列データ
							id_s[i] =  holidayList.get(i).getId();
							names[i] = holidayList.get(i).getName();
							date_s[i] = holidayList.get(i).getDate();
						}
						request.setAttribute("current_year", current_year);
						request.setAttribute("id_s", id_s);
						request.setAttribute("names", names);
						request.setAttribute("date_s", date_s);
					}else {
						request.setAttribute("errorMsg", "祝日一覧を読み込むのにエラーが発生しました。");
					}
					// 祝日一覧画面へ移動
					forward = "/WEB-INF/jsp/holidayList.jsp";
				}
			}

			RequestDispatcher dispatcher =
					request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);

		// ログインの状態をチェックする
		HttpSession session = request.getSession();
		String loginUser_name = (String) session.getAttribute("loginUser_name");

		// セッションにloginUser_nameない場合 CtrlForCms へリダイレクト
		if(loginUser_name == null) {
			response.sendRedirect("CtrlForCms");
		// セッションにloginUser_nameある場合 以下の処理を実行
		} else {
			// 取得するパラメーター用変数
			String str;
			// forward先の変数
			String forward = "";
			/**
			 * 操作キー
			 * 0=なし
			 * 1=新規登録
			 * 2=更新
			 * 3=削除
			 * 10=新規一括フォーム
			 * 11=新規一括登録
			 */
			int fnc = 0;
			str = request.getParameter("fnc");
			if(str != null) {
				fnc = Integer.parseInt(str);
			}
			// fncをリクエストスコープに保存
			request.setAttribute("fnc", fnc);

			// 新規一括登録以外の処理
			if(fnc < 10) {
				int holiday_id = 0;	//post_id　0=指定なし　Null=新規　>0　更新

				str = request.getParameter("holiday_id");
				if(str != null) {
					holiday_id = Integer.parseInt(str);
				}

				// GetHolidayListLogicのオブジェクトを生成
				GetHolidayListLogic getHolidayListLogic = new GetHolidayListLogic();
				//入力された記事データを取得してpostに代入する
				String name = request.getParameter("name");
				// 取得した日付からDateを取得
				int year = Integer.parseInt(request.getParameter("year"));
				int month = Integer.parseInt(request.getParameter("month"));
				int day = Integer.parseInt(request.getParameter("day"));
				// Date変換
				Date date = getHolidayListLogic.getDateByYMD(year, month, day);
				// holidayインスタンスを生成
				Holiday holiday = new Holiday(
						holiday_id,
						name,
						date);

				//新規記事挿入
				if(fnc == 1) {
					//データベースに挿入する
					boolean bret = getHolidayListLogic.createHoliday(holiday);
					if(bret == false) {
						// エラーが出た場合、個別祝日情報取得して、holidayEditへ戻る
						request.setAttribute("errorMsg", "祝日の新規作成にエラーが発生しました");
						request.setAttribute("holiday", holiday);
						int holiday_year = getHolidayListLogic.getHolidayYear(holiday);
						int holiday_month = getHolidayListLogic.getHolidayMonth(holiday);
						int holiday_day = getHolidayListLogic.getHolidayDay(holiday);
						request.setAttribute("holiday_year", holiday_year);
						request.setAttribute("holiday_month", holiday_month);
						request.setAttribute("holiday_day", holiday_day);
						forward = "/WEB-INF/jsp/holidayEdit.jsp";

					}else {
						// カレンダー日付代入
						Calendar cal = Calendar.getInstance();
						// 今年の年数取得
						int current_year = cal.get(Calendar.YEAR);

						//データベースからすべての祝日を読み込む
						List<Holiday> holidayList = getHolidayListLogic.execute();
						// フォワード用配列に代入
						int n_holidayList = holidayList.size();
						int[] id_s =  new int[n_holidayList];
						String[] names = new String[n_holidayList];
						Date[] date_s = new Date[n_holidayList];
						for(int i=0; i<n_holidayList; i++) {
							// 並べ替えた後の名前リストの配列データ
							id_s[i] =  holidayList.get(i).getId();
							names[i] = holidayList.get(i).getName();
							date_s[i] = holidayList.get(i).getDate();
						}
						request.setAttribute("current_year", current_year);
						request.setAttribute("id_s", id_s);
						request.setAttribute("names", names);
						request.setAttribute("date_s", date_s);
						forward = "/WEB-INF/jsp/holidayList.jsp";
					}
					//更新
				} else if(fnc == 2){
					//データベースを更新する
					boolean bret = getHolidayListLogic.updateHoliday(holiday);
					if(bret == false) {
						// エラーが出た場合、個別祝日情報取得して、holidayEditへ戻る
						request.setAttribute("errorMsg", "祝日の更新にエラーが発生しました");
						request.setAttribute("holiday", holiday);
						int holiday_year = getHolidayListLogic.getHolidayYear(holiday);
						int holiday_month = getHolidayListLogic.getHolidayMonth(holiday);
						int holiday_day = getHolidayListLogic.getHolidayDay(holiday);
						request.setAttribute("holiday_year", holiday_year);
						request.setAttribute("holiday_month", holiday_month);
						request.setAttribute("holiday_day", holiday_day);
						forward = "/WEB-INF/jsp/holidayEdit.jsp";
					}else {

						// カレンダー日付代入
						Calendar cal = Calendar.getInstance();
						// 今年の年数取得
						int current_year = cal.get(Calendar.YEAR);

						//データベースからすべての祝日を読み込む
						List<Holiday> holidayList = getHolidayListLogic.execute();
						// フォワード用配列に代入
						int n_holidayList = holidayList.size();
						int[] id_s =  new int[n_holidayList];
						String[] names = new String[n_holidayList];
						Date[] date_s = new Date[n_holidayList];
						for(int i=0; i<n_holidayList; i++) {
							// 並べ替えた後の名前リストの配列データ
							id_s[i] =  holidayList.get(i).getId();
							names[i] = holidayList.get(i).getName();
							date_s[i] = holidayList.get(i).getDate();
						}
						request.setAttribute("current_year", current_year);
						request.setAttribute("id_s", id_s);
						request.setAttribute("names", names);
						request.setAttribute("date_s", date_s);
						forward = "/WEB-INF/jsp/holidayList.jsp";
					}

					//削除
				} else if(fnc == 3){
					if(holiday_id > 0) {
						// 指定したholiday_idで該当アカウントを削除する
						boolean bret = getHolidayListLogic.deleteHoliday(holiday_id);

						if(bret == false) {
							// エラーが出た場合、個別祝日情報取得して、holidayEditへ戻る
							request.setAttribute("errorMsg", "祝日を削除する時にエラーが発生しました。");
							request.setAttribute("holiday", holiday);
							int holiday_year = getHolidayListLogic.getHolidayYear(holiday);
							int holiday_month = getHolidayListLogic.getHolidayMonth(holiday);
							int holiday_day = getHolidayListLogic.getHolidayDay(holiday);
							request.setAttribute("holiday_year", holiday_year);
							request.setAttribute("holiday_month", holiday_month);
							request.setAttribute("holiday_day", holiday_day);
							forward = "/WEB-INF/jsp/holidayEdit.jsp";
						}else{

							// カレンダー日付代入
							Calendar cal = Calendar.getInstance();
							// 今年の年数取得
							int current_year = cal.get(Calendar.YEAR);

							//データベースからすべての祝日を読み込む
							List<Holiday> holidayList = getHolidayListLogic.execute();
							// フォワード用配列に代入
							int n_holidayList = holidayList.size();
							int[] id_s =  new int[n_holidayList];
							String[] names = new String[n_holidayList];
							Date[] date_s = new Date[n_holidayList];
							for(int i=0; i<n_holidayList; i++) {
								// 並べ替えた後の名前リストの配列データ
								id_s[i] =  holidayList.get(i).getId();
								names[i] = holidayList.get(i).getName();
								date_s[i] = holidayList.get(i).getDate();
							}
							request.setAttribute("current_year", current_year);
							request.setAttribute("id_s", id_s);
							request.setAttribute("names", names);
							request.setAttribute("date_s", date_s);
							forward = "/WEB-INF/jsp/holidayList.jsp";
						}
					}
				}

			// 新規一括登録
			} else {
				// パラメーターのint変換
				int number_of_registrations = 0;
				str = request.getParameter("number_of_registrations");
				if(str != null) {
					number_of_registrations = Integer.parseInt(str);
				}
				// リクエストスコープに保存
				request.setAttribute("number_of_registrations", number_of_registrations);

				// GetHolidayListLogicのオブジェクトを生成
				GetHolidayListLogic getHolidayListLogic = new GetHolidayListLogic();

				// 新規一括登録 フォームへ移動
				if(fnc == 10) {
					// パラメーターのint変換
					int registration = 0;
					str = request.getParameter("registration");
					if(str != null) {
						registration = Integer.parseInt(str);
					}

					// 新規一括登録のフォーム画面 初回
					if(registration==1) {
						//データベースからすべての祝日を読み込む
						List<Holiday> holidayList = getHolidayListLogic.execute();
						// フォワード用配列に代入
						int n_holidayList = holidayList.size();
						int[] id_s =  new int[n_holidayList];
						String[] names = new String[n_holidayList];
						int[] years = new int[n_holidayList];
						int[] months = new int[n_holidayList];
						int[] days = new int[n_holidayList];

						for(int i=0; i<n_holidayList; i++) {
							// 並べ替えた後の名前リストの配列データ
							id_s[i] =  holidayList.get(i).getId();
							names[i] = holidayList.get(i).getName();
							years[i] = getHolidayListLogic.getHolidayYear(holidayList.get(i)) + 1;
							months[i] = getHolidayListLogic.getHolidayMonth(holidayList.get(i));
							days[i] = getHolidayListLogic.getHolidayDay(holidayList.get(i));
						}
						request.setAttribute("id_s", id_s);
						request.setAttribute("names", names);
						request.setAttribute("years", years);
						request.setAttribute("months", months);
						request.setAttribute("days", days);

						// カレンダー日付代入
						Calendar cal = Calendar.getInstance();
						// 今年の年数取得
						int current_year = cal.get(Calendar.YEAR);

						request.setAttribute("current_year", current_year);


					// 新規一括登録のフォーム画面 件数変更後
					}else if(registration==2) {

						// name配列の内容取得
						String[] str_arr = request.getParameterValues("name");
						// name配列初期化
						String[] name = new String[str_arr.length];
						// name配列作成
						for(int i=0; i<str_arr.length; i++) {
							name[i] = str_arr[i];
						}str_arr = null;

						// holiday_id配列の内容取得
						str_arr = request.getParameterValues("holiday_id");
						// member_id配列初期化
						int[] holiday_id = new int[name.length];
						// member_id配列作成
						for(int i=0; i<name.length; i++) {
							if(str_arr[i] != null) {
								holiday_id[i] = Integer.parseInt(str_arr[i]);
							}
						}str_arr = null;

						// years, months, days 配列の内容取得
						String[] str_years_arr = request.getParameterValues("years");
						String[] str_months_arr = request.getParameterValues("months");
						String[] str_days_arr = request.getParameterValues("days");
						int[] years = new int[name.length];
						int[] months = new int[name.length];
						int[] days = new int[name.length];

						// dates配列作成
						for(int i=0; i<name.length; i++) {
							years[i] = Integer.parseInt(str_years_arr[i]);
							months[i] = Integer.parseInt(str_months_arr[i]);
							days[i] = Integer.parseInt(str_days_arr[i]);
						}

						// 未登録のデータを渡す
						request.setAttribute("id_s", holiday_id);
						request.setAttribute("names", name);
						request.setAttribute("years", years);
						request.setAttribute("months", months);
						request.setAttribute("days", days);

						// カレンダー日付代入
						Calendar cal = Calendar.getInstance();
						// 今年の年数取得
						int current_year = cal.get(Calendar.YEAR);

						request.setAttribute("current_year", current_year);
					}
					// 祝日の新規一括登録
					forward = "/WEB-INF/jsp/holidayEdit.jsp";

				// 新規一括登録後の画面
				} else if(fnc==11) {

					// name配列の内容取得
					String[] str_arr = request.getParameterValues("name");
					// name配列初期化
					String[] name = new String[str_arr.length];
					// name配列作成
					for(int i=0; i<str_arr.length; i++) {
						if(str_arr[i] != null || str_arr[i].equals("")){
							name[i] = str_arr[i];
						} else {
							name[i] = "名前未入力";
						}
					}str_arr = null;

					// holiday_id配列の内容取得
					str_arr = request.getParameterValues("holiday_id");
					// member_id配列初期化
					int[] holiday_id = new int[name.length];
					// member_id配列作成
					for(int i=0; i<name.length; i++) {
						if(str_arr[i] != null) {
							holiday_id[i] = Integer.parseInt(str_arr[i]);
						}
					}str_arr = null;

					// years, months, days 配列の内容取得
					String[] str_years_arr = request.getParameterValues("years");
					String[] str_months_arr = request.getParameterValues("months");
					String[] str_days_arr = request.getParameterValues("days");
					int[] years = new int[name.length];
					int[] months = new int[name.length];
					int[] days = new int[name.length];

					// dates配列初期化
					Date[] dates = new Date[name.length];
					// dates配列作成
					for(int i=0; i<name.length; i++) {
						years[i] = Integer.parseInt(str_years_arr[i]);
						months[i] = Integer.parseInt(str_months_arr[i]);
						days[i] = Integer.parseInt(str_days_arr[i]);

						if(years[i]!=0 && months[i]!=0 && days[i]!=0) {
							dates[i] = getHolidayListLogic.getDateByYMD(years[i], months[i], days[i]);
						} else {
							dates[i] = null;
						}
					}

					// holidayListのオブジェクトを生成
					List<Holiday> holidayList_new = new ArrayList<Holiday>();

					// holidayListに値を代入
					for(int i=0; i<name.length; i++) {
						Holiday holiday = new Holiday(
								holiday_id[i],
								name[i],
								dates[i]);
						holidayList_new.add(holiday);
					}

					// holidaysデータベースの最大idを取得。(Create後に、前回までの最大idで削除するため)
					int max_id = getHolidayListLogic.getMaxId();
					//データベースに挿入する
					boolean bret = getHolidayListLogic.createHolidayList(holidayList_new);

					// 新規一括登録でデータベースへの登録が失敗した場合
					if(bret == false) {
						// エラーメッセージ用意
						request.setAttribute("errorMsg", "祝日リストの新規一括登録にエラーが発生しました。祝日名、年月日は全て埋める必要があります。");

						// 未登録のデータを渡す
						request.setAttribute("id_s", holiday_id);
						request.setAttribute("names", name);
						request.setAttribute("years", years);
						request.setAttribute("months", months);
						request.setAttribute("days", days);

						// カレンダー日付代入
						Calendar cal = Calendar.getInstance();
						// 今年の年数取得
						int current_year = cal.get(Calendar.YEAR);

						request.setAttribute("current_year", current_year);

						// フォワード
						forward = "/WEB-INF/jsp/holidayEdit.jsp";


					// 新規一括登録でデータベースへの登録が成功した場合
					} else {
						// 前回までのデータを前回までの最大idで削除
						boolean aret = getHolidayListLogic.deleteLastTimeHolidayList(max_id);

						if(aret == false) {
							// エラーメッセージ用意
							request.setAttribute("errorMsg", "前回までの祝日情報の削除にエラーが発生しました");
						} else {
							//データベースからすべての祝日情報を読み込む
							List<Holiday> holidayList = getHolidayListLogic.execute();
							// フォワード用配列に代入
							int n_holidayList = holidayList.size();
							int[] id_s =  new int[n_holidayList];
							String[] names = new String[n_holidayList];
							Date[] date_s = new Date[n_holidayList];

							for(int i=0; i<n_holidayList; i++) {
								// 並べ替えた後の名前リストの配列データ
								id_s[i] =  holidayList.get(i).getId();
								names[i] = holidayList.get(i).getName();
								date_s[i] = holidayList.get(i).getDate();
							}
							request.setAttribute("id_s", id_s);
							request.setAttribute("names", names);
							request.setAttribute("date_s", date_s);
						}
						// フォワード
						forward = "/WEB-INF/jsp/holidayList.jsp";
					}


				}
			}



			RequestDispatcher dispatcher =
					request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		}
	}
}
