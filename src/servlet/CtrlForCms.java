package servlet;

import java.io.IOException;
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
import model.GetMemberListLogic;
import model.Holiday;
import model.Member;

/**
 * Servlet implementation class CtrlForCms
 */
@WebServlet("/CtrlForCms")
public class CtrlForCms extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrlForCms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		//ログインしている状態を確認する
		HttpSession session = request.getSession();
		String loginUser_name = (String) session.getAttribute("loginUser_name");

		//リクエストパラメータを取得する
		//ページ番号
		String para1 = request.getParameter("pge_id");

		String forward = "";
		int pge_id = 0;

		//入力値チェック
		if (para1 != null && para1.length() != 0) {
			pge_id = Integer.parseInt(para1);
		}

		//リクエストスコープに各ページ用情報保存、フォワード先を設定
		if (loginUser_name == null) {
			// 実行のトップページ
			forward = movetoLogin(request);
		} else if (pge_id==0) {
			// CMS top page
			forward = movetoMypage(request);
		} else if (pge_id==1) {
			// メンバー管理
			forward = movetoMemberListManagement(request);
		} else if (pge_id==2) {
			// メンバー管理
			forward = movetoHolidayListManagement(request);
		}

		//メイン画面にフォーワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);
	}

	/**
	 * セッションにユーザー情報ない場合、CMSへのログイン処理
	 * @param request
	 * @return
	 */
	private String movetoLogin(HttpServletRequest request) {
		// フォーワード先 "
		String forward = "SeatChangeServlet?pge_id=1";
		return forward;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}


	private String movetoMypage(HttpServletRequest request) {

		// メンバー一覧を取得する
		GetMemberListLogic getMemberListLogic = new GetMemberListLogic();
		List<Member> memberList =  getMemberListLogic.selectAll();

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

		// リクエストスコープへの保存
		request.setAttribute("id_s", id_s);
		request.setAttribute("members", members);
		request.setAttribute("seat_id_s", seat_id_s);
		request.setAttribute("enrolled", enrolled);
		request.setAttribute("gender_s", gender_s);
		request.setAttribute("position_request_s", position_request_s);

		//全祝日情報を読み込み
		GetHolidayListLogic getHolidayListLogic = new GetHolidayListLogic();
		List<Holiday> holidayList = getHolidayListLogic.execute();
		// フォワード用配列に代入
		int n_holidayList = holidayList.size();
		String[] names = new String[n_holidayList];
		Date[] date_s = new Date[n_holidayList];

		for(int i=0; i<n_holidayList; i++) {
			// 並べ替えた後の名前リストの配列データ
			names[i] = holidayList.get(i).getName();
			date_s[i] = holidayList.get(i).getDate();
		}
		request.setAttribute("names", names);
		request.setAttribute("date_s", date_s);


		// フォーワード先 "
		String forward = "/WEB-INF/jsp/mypage.jsp";
		return forward;
	}

	private String movetoMemberListManagement(HttpServletRequest request) {
		// フォーワード先 "
		String forward = "MemberEdit?post_id=0";
		return forward;
	}

	private String movetoHolidayListManagement(HttpServletRequest request) {
		// フォーワード先 "
		String forward = "HolidayEdit?post_id=0";
		return forward;
	}
}
