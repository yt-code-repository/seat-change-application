package model;

import dao.LoginDAO;

public class LoginLogic {
	public LoginUser execute(LoginUser login) {

		LoginDAO dao = new LoginDAO();

		//該当ユーザーの情報を取得する
		LoginUser user = dao.findByLogin(login);

		// Accountテーブルに該当するユーザーがいれば
		// Userを返す。そうでなければnullを返す
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}
}
