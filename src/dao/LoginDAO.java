package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DbConnection;
import model.LoginUser;

public class LoginDAO {

	public LoginUser findByLogin(LoginUser login){

		String sql = "";
		LoginUser loginUser = null;

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return null;

		try {
			//SQL文
			sql = "SELECT * "
					+ " FROM ACCOUNT "
					+ " WHERE LOGIN_ID = ? AND PASS = ?";

			//SQL命令を準備する
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login.getLogin_id());
			pStmt.setString(2, login.getPass());

			//SQL命令を発行する
			ResultSet rs = pStmt.executeQuery();

			//読み込んだレコードを処理する

			if(rs.next()) {
				int user_id = rs.getInt("USER_ID");
				String login_id = rs.getString("LOGIN_ID");
				String password = rs.getString("PASS");
				String name = rs.getString("NAME");

				//ユーザーアカウントを生成する
				loginUser = new LoginUser(user_id,login_id,password,name);
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;

		}finally {
			//データベース切断

		}
		return loginUser;

	}
}
