package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DbConnection;
import model.Member;

public class MemberDAO {

	/**
	 * idで該当記事を取得
	 * @param id
	 * @return
	 */
	public Member findMemberById(int member_id){

		Member member = null;

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return null;

		try {

			//SELECT文
			String sql = "SELECT * FROM members WHERE id = ?";

			//SQLを送信
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, member_id);

			//SELECTを実行し、結果を取得
			ResultSet rs = pStmt.executeQuery();

			//rs結果表に格納されたレコードをMemberインスタンスリストに代入
			if (rs.next()) {
				// Memberのデータを取得
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int seat_id = rs.getInt("seat_id");
				boolean enrolled = rs.getBoolean("enrolled");
				int gender = rs.getInt("gender");
				int position_request = rs.getInt("position_request");

				// memberインスタンスを生成
				member = new Member(
						id,
						name,
						seat_id,
						enrolled,
						gender,
						position_request
						);

			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;

		} finally {
			//データベース切断
//			if(conn != null) {
//				if(DbConnection.dbDisconnection(conn)==false) {
//					return null;
//				}
//			}
		}

		return member;

	}


	/**
	 * Membersテーブルから全てのレコードを読みだして、メンバー一覧を取得する
	 * @return
	 * @throws ClassNotFoundException
	 */
	public List<Member> findEnrolled() {
		//検索結果を受け取る器
		List<Member> memberList = new ArrayList<>();

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return null;

		try {

			//SELECT文
			String sql = "SELECT * FROM members WHERE enrolled = TRUE ORDER BY id";
			//PreparedStatementに代入して準備する
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SQL文を実行して、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			//1件ずつ関連レコードを取得して、社員リストに追加
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int seat_id = rs.getInt("seat_id");
				boolean enrolled = rs.getBoolean("enrolled");
				int gender = rs.getInt("gender");
				int position_request = rs.getInt("position_request");

				//結果表に格納されたレコードの内容を
				//Employeeインスタンスに設定し、ArrayListインスタンスに追加
				Member member = new Member(id, name, seat_id, enrolled, gender, position_request);
				//設定したインスタンスをリストに追加
				memberList.add(member);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		} finally {
			//データベース切断
//			if(conn != null) {
//				if(DbConnection.dbDisconnection(conn)==false) {
//					return null;
//				}
//			}
		}
		// メンバーリストを返す
		return memberList;
	}


	/**
	 * Membersテーブルから在籍者と希望座席のレコードを読みだして、メンバー一覧を取得する
	 * @return
	 * @throws ClassNotFoundException
	 */
	public List<Member> findEnrolledPositionRequest(int position_request_id) {
		//検索結果を受け取る器
		List<Member> memberList = new ArrayList<>();

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return null;

		try {

			//SELECT文
			String sql ="";
			sql += "SELECT * FROM members ";
			sql += "WHERE ";
			sql += "enrolled = TRUE and position_request = ? ";
			sql += "ORDER BY id";
			//PreparedStatementに代入して準備する
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, position_request_id);

			//SQL文を実行して、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			//1件ずつ関連レコードを取得して、メンバーリストに追加
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int seat_id = rs.getInt("seat_id");
				boolean enrolled = rs.getBoolean("enrolled");
				int gender = rs.getInt("gender");
				int position_request = rs.getInt("position_request");

				// memberインスタンスに設定し、ArrayListインスタンスに追加
				Member member = new Member(id, name, seat_id, enrolled, gender, position_request);
				//設定したインスタンスをリストに追加
				memberList.add(member);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		} finally {
			//データベース切断
//			if(conn != null) {
//				if(DbConnection.dbDisconnection(conn)==false) {
//					return null;
//				}
//			}
		}
		// メンバーリストを返す
		return memberList;
	}

	/**
	 * Membersテーブルから全てのレコードを読みだして、メンバー一覧を取得する
	 * @return
	 * @throws ClassNotFoundException
	 */
	public List<Member> findAll() {
		//検索結果を受け取る器
		List<Member> memberList = new ArrayList<>();

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return null;

		try {

			//SELECT文
			String sql = "SELECT * FROM members ORDER BY id";
			//PreparedStatementに代入して準備する
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SQL文を実行して、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			//1件ずつ関連レコードを取得して、社員リストに追加
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int seat_id = rs.getInt("seat_id");
				boolean enrolled = rs.getBoolean("enrolled");
				int gender = rs.getInt("gender");
				int position_request = rs.getInt("position_request");

				//結果表に格納されたレコードの内容を
				//Employeeインスタンスに設定し、ArrayListインスタンスに追加
				Member member = new Member(id, name, seat_id, enrolled, gender, position_request);
				//設定したインスタンスをリストに追加
				memberList.add(member);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		} finally {
			//データベース切断
//			if(conn != null) {
//				if(DbConnection.dbDisconnection(conn)==false) {
//					return null;
//				}
//			}
		}
		// メンバーリストを返す
		return memberList;
	}


	/**
	 *  insertAll
	 * @param List<Member> memberList
	 * @return
	 */
	public boolean createAtOnce(List<Member> memberList) {

		String sql = "";
		int n_memberList = memberList.size();

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return false;

		try {

			//SQL文
			sql = "INSERT INTO"
					+ " members"
					+ " (name, seat_id, enrolled, gender, position_request)"
					+ " VALUES";
			for(int i=0; i<n_memberList-1; i++) {
				sql += " (?,?,?,?,?),";
			}sql += " (?,?,?,?,?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			for(int i=0; i<n_memberList; i++) {
				pStmt.setString((1+5*i), memberList.get(i).getName());
				pStmt.setInt((2+5*i), memberList.get(i).getSeat_id());
				pStmt.setBoolean((3+5*i), memberList.get(i).isEnrolled());
				pStmt.setInt((4+5*i), memberList.get(i).getGender());
				pStmt.setInt((5+5*i), memberList.get(i).getPosition_request());
			}

			//SQL命令を発行する
			int result = pStmt.executeUpdate();

			//読み込んだ結果を処理する
			if(result == 0) {
				return false;
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return false;

		}finally {
			//データベース切断

		}
		return true;
	}

	/**
	 * membersテーブルに新しいメンバーを挿入する
	 * @param mutter
	 * @return
	 */
	public boolean create(Member member) {

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return false;


		try {

			// INSERT文
			String sql = "";
			sql += "INSERT INTO Member";
			sql += "(name, seat_id, enroled) " ;
			sql += "VALUES ";
			sql += "(?, ?, ?, ?, ?)";

			//PreparedStatementに代入して準備する
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// INSERT文のプレースホルダーへ代入
			pStmt.setString(1, member.getName());
			pStmt.setInt(2, member.getSeat_id());
			pStmt.setBoolean(3, member.isEnrolled());
			pStmt.setInt(4, member.getGender());
			pStmt.setInt(5, member.getPosition_request());

			//SQL文を実行して、結果表を取得する
			int result = pStmt.executeUpdate();
			// INSERTが正常に行った場合は、result=1
			if (result !=1) {
				// 新規挿入にエラーが発生
				return false;
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return false;
		} finally {
			//データベース切断
		}

		return true;
	}

	/**
	 *
	 * @param member
	 * @return
	 */
	public boolean update(Member member) {

		String sql = "";

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return false;

		try {
			//SQL文を定義する
	        sql = "UPDATE"
	        		+ " members"
	        		+ " SET "
	        		+ " name = ?, "
	        		+ " seat_id = ?, "
	        		+ " enrolled = ?, "
	        		+ " gender = ? ,"
	        		+ " position_request = ? "
	        		+ " WHERE "
	        		+ " id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, member.getName());
			pStmt.setInt(2, member.getSeat_id());
			pStmt.setBoolean(3, member.isEnrolled());
			pStmt.setInt(4, member.getGender());
			pStmt.setInt(5, member.getPosition_request());
			pStmt.setInt(6, member.getId());

			//SQL命令を発行する
			int result = pStmt.executeUpdate();

			//読み込んだ結果を処理する
			if(result != 1) {
				return false;
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return false;

		}finally {
			//データベース切断

		}
		return true;
	}

	/**
	 * Membersテーブルからidの最大値を取得する
	 * @return max_id
	 * @throws ClassNotFoundException
	 */
	public int findMaxId() {
		//検索結果を受け取る器
		int max_id = 0;

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return 0;

		try {

			//SELECT文
			String sql = "SELECT MAX(id) FROM members";
			//PreparedStatementに代入して準備する
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SQL文を実行して、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			//1件ずつ関連レコードを取得して、社員リストに追加
			while (rs.next()) {
				max_id = rs.getInt("max");
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return 0;
		} finally {
			//データベース切断
//			if(conn != null) {
//				if(DbConnection.dbDisconnection(conn)==false) {
//					return null;
//				}
//			}
		}

		// コンソール出力
//		System.out.println("daoのid : " + id);

		// idを返す
		return max_id;
	}


	/**
	 * 記事を削除
	 * @param
	 * @return
	 */
	public boolean deleteLastTime(int id){

		String sql = "";

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return false;

		try {
			//SQL文
			sql = "DELETE FROM members "
					+ "WHERE id <= ?";


			//SQL命令を準備する
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			//SQL命令を発行する
			int result = pStmt.executeUpdate();

			if(result < 0) {
				return false;
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return false;

		}finally {
			//データベース切断

		}
		//読み込んだ結果を返す
		return true;
	}
}