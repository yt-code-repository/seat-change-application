package model;

import java.util.List;

import dao.MemberDAO;

/**
 *  処理を担当するクラス
 *
 */
public class GetMemberListLogic {

	/**
	 * アイテム一覧を取得して返却する
	 *
	 * @return beanList
	 */
	public List<Member> selectAll() {

		// Mutterのインスタンスを生成する
		MemberDAO dao = new MemberDAO();

		// daoを通して全てのつぶやき履歴を取得して返す
		List<Member> memberList = dao.findAll();


		return memberList;
	}

	/**
	 * Enrolledのメンバー一覧を取得して返却する
	 *
	 * @return beanList
	 */
	public List<Member> execute() {

		// Mutterのインスタンスを生成する
		MemberDAO dao = new MemberDAO();

		// daoを通して全てのつぶやき履歴を取得して返す
		List<Member> memberList = dao.findEnrolled();

		return memberList;
	}


	/**
	 * Enrolledでposition_requestメンバー一覧を取得して返却する
	 *
	 * @return beanList
	 */
	public List<Member> getByEnrolledPosition_request(int position_request) {

		// Mutterのインスタンスを生成する
		MemberDAO dao = new MemberDAO();

		// daoを通して全てのつぶやき履歴を取得して返す
		List<Member> memberList = dao.findEnrolledPositionRequest(position_request);

		return memberList;
	}

	/**
	 * メンバーを取得して返却する
	 *
	 * @return Member
	 */
	public Member getMemberById(int member_id) {
		//DAOオブジェクト生成
		MemberDAO dao = new MemberDAO();
		//DBから全レコードを取得する
		Member member = dao.findMemberById(member_id);
		return member;
	}

	/**
	 * 最大idの取得
	 * @param id
	 * @return
	 */
	public int getMaxId() {
		MemberDAO dao = new MemberDAO();
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
	public boolean deleteLastTimeMemberList(int max_id) {
		MemberDAO dao = new MemberDAO();
		if(dao.deleteLastTime(max_id) != false) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 新規一括登録
	 * @param memberList
	 * @return
	 */
	public boolean createMemberList(List<Member> memberList) {
		// 入力項目のチェック
		for(int i=0; i<memberList.size(); i++) {
			boolean flg = inputValidation(memberList.get(i));
			// flg==falseの時、return flg
			if(!flg) {
				return flg;
			}
		}
		MemberDAO dao = new MemberDAO();
		if(dao.createAtOnce(memberList) != false) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * メンバーの内容を更新
	 * @param member
	 * @return
	 */
	public boolean updateMember(Member member) {
		// 入力項目のチェック
		boolean flg = inputValidation(member);
		// flg==falseの時、return flg
		if(!flg) {
			return flg;
		// それ以外、処理を実行
		} else {
			MemberDAO dao = new MemberDAO();
			if(dao.update(member) != false) {
				return true;
			}else {
				return false;
			}
		}
	}

	/**
	 * @param memberList
	 * @return 出席番号の配列
	 */
	public int[] translateId_s(List<Member> memberList) {
		int n_memberList = memberList.size();
		int[] id_s =  new int[n_memberList];
		for(int i=0; i<n_memberList; i++) {
			// idリストの配列データ
			id_s[i] =  memberList.get(i).getId();
		}
		return id_s;
	}

	/**
	 * @param memberList
	 * @return 名前の配列
	 */
	public String[] translateMembers(List<Member> memberList) {
		int n_memberList = memberList.size();
		String[] members = new String[n_memberList];
		for(int i=0; i<n_memberList; i++) {
			// idリストの配列データ
			members[i] =  memberList.get(i).getName();
		}
		return members;
	}

	/**
	 * @param memberList
	 * @return seat_idの配列
	 */
	public int[] translateSeat_id_s(List<Member> memberList) {
		int n_memberList = memberList.size();
		int[] seat_id_s = new int[n_memberList];
		for(int i=0; i<n_memberList; i++) {
			// idリストの配列データ
			seat_id_s[i] =  memberList.get(i).getSeat_id();
		}
		return seat_id_s;
	}

	/**
	 * @param memberList
	 * @return 在籍状況の配列
	 */
	public boolean[] translateEnrolled(List<Member> memberList) {
		int n_memberList = memberList.size();
		boolean[] enrolled = new boolean[n_memberList];
		for(int i=0; i<n_memberList; i++) {
			// idリストの配列データ
			enrolled[i] =  memberList.get(i).isEnrolled();
		}
		return enrolled;
	}

	/**
	 * @param memberList
	 * @return 性別の配列
	 */
	public int[] translateGender_s(List<Member> memberList) {
		int n_memberList = memberList.size();
		int[] gender_s = new int[n_memberList];
		for(int i=0; i<n_memberList; i++) {
			// idリストの配列データ
			gender_s[i] =  memberList.get(i).getGender();
		}
		return gender_s;
	}

	/**
	 * 入力のチェック関数
	 * @param holiday
	 * @return boolean
	 */
	public boolean inputValidation(Member member) {
		// name 取得
		String name = member.getName();

		// 入力不備の時、return false
		if(name.isEmpty()) {
			return false;
		} else if(name.matches(".*<.*") && name.matches(".*>.*")) {
			return false;
		}
		// 入力OKの時、
		return true;
	}
}
