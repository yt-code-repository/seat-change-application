package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMemberListLogic;
import model.Member;

/**
 * Servlet implementation class MemberEdit
 */
@WebServlet("/MemberEdit")
public class MemberEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEdit() {
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
			int member_id = 0;
			int fnc = 0;

			//リクエストパラメータを取得する
			String str = null;
			str = request.getParameter("member_id");
			if(str != null) {
				member_id = Integer.parseInt(str);
			}
			str = request.getParameter("fnc");
			if(str != null) {
				fnc = Integer.parseInt(str);
			}
			// リクエストスコープにfnc、errorMsg保存
			request.setAttribute("fnc", fnc);
			request.setAttribute("errorMsg", null);

			// GetMemberListLogicのオブジェクトを生成
			GetMemberListLogic getMemberListLogic = new GetMemberListLogic();


			// メンバーの更新画面へ
			if(member_id > 0) {
				//指定したmember_idで該当記事を読み込む
				Member member = getMemberListLogic.getMemberById(member_id);

				if(member != null) {
					request.setAttribute("member", member);
				}else {
					request.setAttribute("errorMsg", "メンバーを読み込むのにエラーが発生しました。");
				}
				//編集画面に移動
				forward = "/WEB-INF/jsp/memberEdit.jsp";

			// メンバー一覧画面
			}else if(member_id == 0){

				//全記事を読み込み
				List<Member> memberList = getMemberListLogic.selectAll();
				//リクエストスコープにmemberListを保存する
				if(memberList != null) {
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
					request.setAttribute("id_s", id_s);
					request.setAttribute("members", members);
					request.setAttribute("enrolled", enrolled);
					request.setAttribute("gender_s", gender_s);
					request.setAttribute("position_request_s", position_request_s);
				}else {
					request.setAttribute("errorMsg", "メンバー一覧を読み込むのにエラーが発生しました。");
				}
				//記事一覧画面へ移動
				forward = "/WEB-INF/jsp/memberList.jsp";
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
			 * 2=更新
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

			// メンバーの更新の処理(メンバーは更新のみ)
			if(fnc < 10) {
				int member_id = 0;	//member_id　0=指定なし　個別番号=新規一括　個別番号=更新

				str = request.getParameter("member_id");
				if(str != null) {
					member_id = Integer.parseInt(str);
				}

				// MemberLogicのオブジェクトを生成
				GetMemberListLogic getMemberListLogic = new GetMemberListLogic();

				Member member;

				// 入力されたメンバーデータを取得してmemberに代入する
				String name = request.getParameter("name");
				int seat_id = Integer.parseInt(request.getParameter("seat_id"));
				String enrolled_str = request.getParameter("enrolled");

				boolean enrolled_flg;
				if (enrolled_str.equals("true")) {
					enrolled_flg = true;
				} else {
					enrolled_flg = false;
				}
				int gender = Integer.parseInt(request.getParameter("gender"));
				int position_request = Integer.parseInt(request.getParameter("position_request"));

				// 更新するmemberのインスタンスを生成
				member = new Member(
						member_id,
						name,
						seat_id,
						enrolled_flg,
						gender,
						position_request);

				//更新
				if(fnc == 2){
					//データベースに挿入する
					boolean bret = getMemberListLogic.updateMember(member);
					if(bret == false) {
						request.setAttribute("errorMsg", "メンバーの更新にエラーが発生しました");
						request.setAttribute("member", member);
						forward = "/WEB-INF/jsp/memberEdit.jsp";

					}else {
						//データベースからすべての記事を読み込む
						List<Member> memberList = getMemberListLogic.selectAll();
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
						request.setAttribute("id_s", id_s);
						request.setAttribute("members", members);
						request.setAttribute("enrolled", enrolled);
						request.setAttribute("gender_s", gender_s);
						request.setAttribute("position_request_s", position_request_s);

						forward = "/WEB-INF/jsp/memberList.jsp";
					}
				}

			// 新規一括登録
			} else {
				// GetMemberListLogicは、登録後の画面のみで使用する

				// 新規一括登録フォームへ
				if(fnc==10) {
					int number_of_member = 0;
					str = request.getParameter("number_of_member");
					if(str != null) {
						number_of_member = Integer.parseInt(str);
					}
					request.setAttribute("number_of_member", number_of_member);
					// メンバーの新規一括登録
					forward = "/WEB-INF/jsp/memberEdit.jsp";

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

					// member_id配列の内容取得
					str_arr = request.getParameterValues("member_id");
					// member_id配列初期化
					int[] member_id = new int[name.length];
					// member_id配列作成
					for(int i=0; i<name.length; i++) {
						if(str_arr[i] != null) {
							member_id[i] = Integer.parseInt(str_arr[i]);
						}
					}str_arr = null;

					// seat_id配列の内容取得
					str_arr = request.getParameterValues("seat_id");
					// seat_id配列初期化
					int[] seat_id = new int[name.length];
					// seat_id配列作成
					for(int i=0; i<name.length; i++) {
						if(str_arr[i] != null) {
							seat_id[i] = Integer.parseInt(str_arr[i]);
						}
					}str_arr = null;

					// enrolled配列の内容取得
					str_arr = request.getParameterValues("enrolled");
					// enrolled配列初期化
					boolean[] enrolled_arr = new boolean[name.length];
					// enrolled配列作成
					for(int i=0; i<name.length; i++) {
						if(str_arr[i].equals("true")) {
							enrolled_arr[i] = true;
						} else {
							enrolled_arr[i] = false;
						}
					}str_arr = null;

					// gender配列の内容取得
					str_arr = request.getParameterValues("gender");
					// gender配列初期化
					int[] gender = new int[name.length];
					// enrolled配列作成
					for(int i=0; i<name.length; i++) {
						if(str_arr[i] != null) {
							gender[i] = Integer.parseInt(str_arr[i]);
						}
					}str_arr = null;

					// position_request配列の内容取得
					str_arr = request.getParameterValues("position_request");
					// gender配列初期化
					int[] position_request = new int[name.length];
					// enrolled配列作成
					for(int i=0; i<name.length; i++) {
						if(str_arr[i] != null) {
							position_request[i] = Integer.parseInt(str_arr[i]);
						}
					}str_arr = null;

					// MemberLogicのオブジェクトを生成
					GetMemberListLogic getMemberListLogic = new GetMemberListLogic();

					List<Member> memberList_new = new ArrayList<Member>();

					for(int i=0; i<name.length; i++) {
						Member member;
						member = new Member(
								member_id[i],
								name[i],
								seat_id[i],
								enrolled_arr[i],
								gender[i],
								position_request[i]);
						memberList_new.add(member);
					}

					// membersデータベースの最大idを取得(Create後に、前回までの最大idで削除するため)
					int max_id = getMemberListLogic.getMaxId();
					//データベースに挿入する
					boolean bret = getMemberListLogic.createMemberList(memberList_new);


					// 新規一括登録でデータベースへの登録が失敗した場合
					if(bret == false) {
						// エラーメッセージ用意
						request.setAttribute("errorMsg", "メンバーの更新にエラーが発生しました。名前は全て埋める必要があります。");

						// フォワード用配列に代入
						int n_memberList_new = memberList_new.size();
						int[] id_s =  new int[n_memberList_new];
						String[] members = new String[n_memberList_new];
						int[] seat_id_s = new int[n_memberList_new];
						boolean[] enrolled = new boolean[n_memberList_new];
						int[] gender_s = new int[n_memberList_new];
						int[] position_request_s = new int[n_memberList_new];

						for(int i=0; i<n_memberList_new; i++) {
							// 並べ替えた後の名前リストの配列データ
							id_s[i] =  memberList_new.get(i).getId();
							members[i] = memberList_new.get(i).getName();
							seat_id_s[i] =  i + 1;
							enrolled[i] = memberList_new.get(i).isEnrolled();
							gender_s[i] = memberList_new.get(i).getGender();
							position_request_s[i] = memberList_new.get(i).getPosition_request();
						}
						request.setAttribute("id_s", id_s);
						request.setAttribute("members", members);
						request.setAttribute("seat_id_s", seat_id_s);
						request.setAttribute("enrolled", enrolled);
						request.setAttribute("gender_s", gender_s);
						request.setAttribute("position_request_s", position_request_s);

						forward = "/WEB-INF/jsp/memberEdit.jsp";


					// 新規一括登録でデータベースへの登録が成功した場合
					} else {
						// 前回までのデータを前回までの最大idで削除
						boolean aret = getMemberListLogic.deleteLastTimeMemberList(max_id);

						if(aret == false) {
							// エラーメッセージ用意
							request.setAttribute("errorMsg", "メンバーの更新にエラーが発生しました");
						} else {
							//データベースからすべての記事を読み込む
							List<Member> memberList = getMemberListLogic.selectAll();
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
							request.setAttribute("id_s", id_s);
							request.setAttribute("members", members);
							request.setAttribute("enrolled", enrolled);
							request.setAttribute("gender_s", gender_s);
							request.setAttribute("position_request_s", position_request_s);
						}

						forward = "/WEB-INF/jsp/memberList.jsp";
					}
				}

			}

			RequestDispatcher dispatcher =
					request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		}

	}
}
