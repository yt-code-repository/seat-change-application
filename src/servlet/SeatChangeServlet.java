package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CountLogic;
import model.GetHolidayListLogic;
import model.GetMemberListLogic;
import model.Holiday;
import model.HolidayList;
import model.Member;
import model.MemberList;
import model.SortLogic;
import model.StartDateLogic;

/**
 * Servlet implementation class SeatChangeServlet
 */
@WebServlet("/SeatChangeServlet")
public class SeatChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeatChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		// ログインの状態をチェックする
		HttpSession session = request.getSession();
		String user_name = (String) session.getAttribute("user_name");

		// セッションにuser_nameない場合 CtrlForFront?pge_id=0 へリダイレクト
		if(user_name == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("CtrlForFront?pge_id=0");
			dispatcher.forward(request, response);

		// セッションにuser_nameある場合 処理を実行
		} else {
			//リクエストパラメータを取得する
			//ページ番号
			String para1 = request.getParameter("pge_id");

			// forward変数の初期化
			String forward = "";
			// pge_id変数の初期化
			int pge_id = 0;

			//入力値チェック
			if (para1 != null && para1.length() != 0) {
				pge_id = Integer.parseInt(para1);
			}

			// pge_idの値によって処理を分岐
			if (pge_id==0) {
				// 実行のトップページ
				forward = movetoReady(request);
			} else if (pge_id==1) {
				// CMS ログイン
				forward = movetoLogin();
			}

			//メイン画面にフォーワード
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		}
	}

	/**
	 *  CMSへのログイン画面
	 * @return フォワード用パス
	 */
	private String movetoLogin() {
		// フォーワード先 "
		String forward = "WEB-INF/jsp/login.jsp";
		return forward;
	}

	/**
	 * トップページフォワード前処理
	 *
	 * @return フォワード用パス
	 */
	private String movetoReady(HttpServletRequest request) {

		// 実行回数のカウント変数
		int count = 0;
		request.setAttribute("count", count);

		// 処理クラスをインスタンス化
		GetMemberListLogic logic = new GetMemberListLogic();
		// すべてのメンバーのデータを memberList として取得
		List<Member> memberList = logic.execute();
		// MemberListクラスへmemberListを保存
		MemberList.setMemberList(memberList);

		// フォワード用配列に代入
		int n_memberList = memberList.size();
		int[] id_s =  new int[n_memberList];
		String[] members = new String[n_memberList];
		int[] seat_id_s = new int[n_memberList];
		boolean[] enrolled = new boolean[n_memberList];
		int[] gender_s = new int[n_memberList];
		int[] position_request_s = new int[n_memberList];

		for(int i=0; i<n_memberList; i++) {
			// 並べ替えた後の名前リストの配列データ
			id_s[i] =  memberList.get(i).getId();
			members[i] = memberList.get(i).getName();
			seat_id_s[i] =  i + 1;
			enrolled[i] = memberList.get(i).isEnrolled();
			gender_s[i] = memberList.get(i).getGender();
			position_request_s[i] = memberList.get(i).getPosition_request();
		}

		// フォワードの準備

//		// リクエストスコープの場合
		request.setAttribute("id_s", id_s);
		request.setAttribute("members", members);
		request.setAttribute("seat_id_s", seat_id_s);
		request.setAttribute("gender_s", gender_s);
		request.setAttribute("position_request_s", position_request_s);

		// 処理クラスをインスタンス化
		GetHolidayListLogic holidaylogic = new GetHolidayListLogic();
		// すべての祝日のデータを holidayList として取得
		List<Holiday> holidayList = holidaylogic.execute();
		// HolidayListクラスへholidayListを保存
		HolidayList.setHolidayList(holidayList);

		// StartDateLogicインスタンスを生成
		StartDateLogic startLogic = new StartDateLogic();
		// int配列 0:year, 1:month, 2:date, 3:曜日のインデックスを保持
		int start_date[] = new int[5];
		// 次の週のスタート日を決定
		start_date = startLogic.executeByWeek(1);
		// intに代入
		int current_year = start_date[0];
		int start_date_year = start_date[1];
		int start_date_month = start_date[2];
		int start_date_day = start_date[3];
		// 曜日のString生成
		String day_of_week = "";
		// 曜日取得
		day_of_week = startLogic.day_of_week(start_date[4]);
		// リクエストスコープに保存
		request.setAttribute("start_date_mode", 0);
		request.setAttribute("current_year", current_year);
		request.setAttribute("start_date_year", start_date_year);
		request.setAttribute("start_date_month", start_date_month);
		request.setAttribute("start_date_day", start_date_day);
		request.setAttribute("day_of_week", day_of_week);
		int selected_week = 1;
		request.setAttribute("selected_week", selected_week);

		// フォーワード先 "
		String forward = "/WEB-INF/jsp/seatChange.jsp";
		return forward;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);

		// ログインの状態をチェックする
		HttpSession session = request.getSession();
		String user_name = (String) session.getAttribute("user_name");

		// セッションにuser_nameない場合 CtrlForFront?pge_id=0 へリダイレクト
		if(user_name == null) {
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("CtrlForFront?pge_id=0");
			dispatcher.forward(request, response);

		// セッションにuser_nameある場合 処理を実行
		} else {
			// すべてのメンバーのデータを memberList として取得
			List<Member> memberList = MemberList.getMemberList();
			// GetMemberListLogic インスタンスを生成
			GetMemberListLogic getMemberListLogic = new GetMemberListLogic();

			// 前希望のメンバーの番号を追加
			List<Member> frontMemberList = getMemberListLogic.getByEnrolledPosition_request(1);
			// 後希望のメンバーの番号を追加
			List<Member> backMemberList = getMemberListLogic.getByEnrolledPosition_request(3);
			// 中間メンバーの番号を確定
			List<Member> middleMemberList = getMemberListLogic.getByEnrolledPosition_request(2);

			// SortLogic 実行
			int front_size = frontMemberList.size();
			int middle_size = middleMemberList.size();
			SortLogic frontSortLogic = new SortLogic();
			frontSortLogic.execute(frontMemberList, 0);
			SortLogic middleSortLogic = new SortLogic();
			middleSortLogic.execute(middleMemberList, front_size);
			SortLogic backSortLogic = new SortLogic();
			backSortLogic.execute(backMemberList, front_size + middle_size);

//			System.out.println("frontMemberList");
//			for(int i=0; i<frontMemberList.size(); i++) {
//				System.out.println(frontMemberList.get(i).getName());
//			}
//			System.out.println("backMemberList");
//			for(int i=0; i<backMemberList.size(); i++) {
//				System.out.println(backMemberList.get(i).getName());
//			}
//			System.out.println("middleMemberList");
//			for(int i=0; i<middleMemberList.size(); i++) {
//				System.out.println(middleMemberList.get(i).getName());
//			}

			// フォワード用配列に代入
			int n_memberList = memberList.size();
			int[] id_s =  new int[n_memberList];
			String[] members = new String[n_memberList];
			int[] seat_id_s = new int[n_memberList];
			int[] gender_s = new int[n_memberList];
			int[] position_request_s = new int[n_memberList];

			int n_frontList = frontMemberList.size();
			for(int i=0; i<n_frontList; i++) {
				// Seat_idの番号取得
				int index = frontMemberList.get(i).getSeat_id() - 1;

				// 並べ替えた後の名前リストの配列データ
				id_s[index] =  frontMemberList.get(i).getId();
				members[index] = frontMemberList.get(i).getName();
				seat_id_s[index] =  frontMemberList.get(i).getSeat_id();
				gender_s[index] = frontMemberList.get(i).getGender();
				position_request_s[i] = frontMemberList.get(i).getPosition_request();
			}
			int n_middleList = middleMemberList.size();
			for(int i=0; i<n_middleList; i++) {
				// Seat_idの番号取得
				int index = middleMemberList.get(i).getSeat_id() - 1;

				// 並べ替えた後の名前リストの配列データ
				id_s[index] =  middleMemberList.get(i).getId();
				members[index] = middleMemberList.get(i).getName();
				seat_id_s[index] =  middleMemberList.get(i).getSeat_id();
				gender_s[index] = middleMemberList.get(i).getGender();
				position_request_s[index] = middleMemberList.get(i).getPosition_request();
			}
			int n_backList = backMemberList.size();
			for(int i=0; i<n_backList; i++) {
				// Seat_idの番号取得
				int index = backMemberList.get(i).getSeat_id() - 1;

				// 並べ替えた後の名前リストの配列データ
				id_s[index] = backMemberList.get(i).getId();
				members[index] = backMemberList.get(i).getName();
				seat_id_s[index] =  backMemberList.get(i).getSeat_id();
				gender_s[index] = backMemberList.get(i).getGender();
				position_request_s[index] = backMemberList.get(i).getPosition_request();
			}

			// 並べ替え後のメンバーのコンソール出力
//			for(int i=0; i<n_memberList; i++) {
//				System.out.println("");
//				System.out.println("id_s : " + id_s[i]);
//				System.out.println("members : " + members[i]);
//				System.out.println("seat_id_s : " + seat_id_s[i]);
//				System.out.println("gender_s : " + gender_s[i]);
//			}

			// パラメーター取得
			String str_week  = request.getParameter("selected_week");
			String str_year  = request.getParameter("selected_year");
			String str_month = request.getParameter("selected_month");
			String str_day	 = request.getParameter("selected_day");
			// int変換
			int selected_week = Integer.parseInt(str_week);
			int selected_year = Integer.parseInt(str_year);
			int selected_month = Integer.parseInt(str_month);
			int selected_day = Integer.parseInt(str_day);

			// 年月日選択していない時→週スタート
			if(selected_year==0 || selected_month==0 || selected_day==0) {

				// StartDateLogicインスタンスを生成
				StartDateLogic startLogic = new StartDateLogic();
				// int配列 0:year, 1:month, 2:date, 3:曜日のインデックスを保持
				int start_date[] = new int[5];
				// 次の週のスタート日を決定
				start_date = startLogic.executeByWeek(selected_week);
				// intに代入
				int current_year =  start_date[0];
				int start_date_year = start_date[1];
				int start_date_month = start_date[2];
				int start_date_day = start_date[3];
				// 曜日のString生成
				String day_of_week = startLogic.day_of_week(start_date[4]);
				// リクエストスコープに保存
				request.setAttribute("start_date_mode", 0);
				request.setAttribute("current_year", current_year);
				request.setAttribute("start_date_year", start_date_year);
				request.setAttribute("start_date_month", start_date_month);
				request.setAttribute("start_date_day", start_date_day);
				request.setAttribute("day_of_week", day_of_week);
				request.setAttribute("selected_week", selected_week);

			// 年月日選択している時→年月日スタート
			} else {

				// StartDateLogicインスタンスを生成
				StartDateLogic startLogic = new StartDateLogic();
				// int配列 0:今年の年数, 1:year, 2:month, 3:date, 4:曜日のインデックスを保持
				int start_date[] = new int[5];
				// 次の週のスタート日を決定
				start_date = startLogic.executeByDate(selected_year, selected_month, selected_day);
				// intに代入
				int current_year = start_date[0];
				int start_date_year = start_date[1];
				int start_date_month = start_date[2];
				int start_date_day = start_date[3];
				// 曜日のString生成
				String day_of_week = startLogic.day_of_week(start_date[4]);
				// リクエストスコープに保存
				request.setAttribute("start_date_mode", 1);
				request.setAttribute("current_year", current_year);
				request.setAttribute("start_date_year", start_date_year);
				request.setAttribute("start_date_month", start_date_month);
				request.setAttribute("start_date_day", start_date_day);
				request.setAttribute("day_of_week", day_of_week);
				request.setAttribute("selected_week", selected_week);
			}


			// フォワード
			request.setAttribute("id_s", id_s);
			request.setAttribute("members", members);
			request.setAttribute("seat_id_s", seat_id_s);
			request.setAttribute("gender_s", gender_s);
			request.setAttribute("position_request_s", position_request_s);

			// CountLogic 実行
			CountLogic.CountUp();
			int count = CountLogic.getX();
			request.setAttribute("count", count);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/seatChange.jsp");
			dispatcher.forward(request, response);
		}
	}
}
