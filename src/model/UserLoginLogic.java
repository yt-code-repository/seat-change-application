package model;

import dao.UsersDAO;

public class UserLoginLogic {

	public User execute(User login) {

		UsersDAO dao = new UsersDAO();

		//該当ユーザーの情報を取得する
		User user = dao.findByLogin(login);

		// Usersテーブルに該当するユーザーがいれば
		// Userを返す。そうでなければnullを返す
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

}
