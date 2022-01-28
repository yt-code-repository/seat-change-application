package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.DbConnection;
import model.Holiday;

public class HolidayDAO {

	/**
	 * idで該当祝日を取得
	 * @param holiday_id
	 * @return
	 */
	public Holiday findHolidayById(int holiday_id){

		Holiday holiday = null;

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return null;

		try {

			//SELECT文
			String sql = "SELECT * FROM holidays WHERE id = ?";

			//SQLを送信
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, holiday_id);

			//SELECTを実行し、結果を取得
			ResultSet rs = pStmt.executeQuery();

			//rs結果表に格納されたレコードをMemberインスタンスリストに代入
			if (rs.next()) {
				// Memberのデータを取得
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Date date = rs.getDate("date");

				// holidayインスタンスを生成
				holiday = new Holiday(
						id,
						name,
						date
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

		return holiday;

	}
	/**
	 * Membersテーブルから全てのレコードを読みだして、メンバー一覧を取得する
	 * @return
	 * @throws ClassNotFoundException
	 */
	public List<Holiday> findAll() {
		//検索結果を受け取る器
		List<Holiday> holidayList = new ArrayList<>();

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return null;

		try {

			//SELECT文
			String sql = "SELECT * FROM holidays ORDER BY date ASC";
			//PreparedStatementに代入して準備する
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SQL文を実行して、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			//1件ずつ関連レコードを取得して、社員リストに追加
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Date date = rs.getDate("date");

				//結果表に格納されたレコードの内容を
				//Employeeインスタンスに設定し、ArrayListインスタンスに追加
				Holiday holiday = new Holiday(id, name, date);
				//設定したインスタンスをリストに追加
				holidayList.add(holiday);
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
		// リストを返す
		return holidayList;
	}


	/**
	 *  insert祝日情報
	 * @param holiday
	 * @return
	 */
	public boolean insert(Holiday holiday) {

		String sql = "";

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return false;

		try {
			//SQL文
			sql = "INSERT INTO"
					+ " holidays"
					+ " (name, date)"
					+ " VALUES"
					+ " (?,?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, holiday.getName());
			java.sql.Date holiday_date = getSQLDate(holiday.getDate());
			pStmt.setDate(2, holiday_date);
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
	 * 祝日情報を削除
	 * @param id
	 * @return
	 */
	public boolean delete(int id){

		String sql = "";

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return false;

		try {
			//SQL文
			sql = "DELETE"
					+ " FROM holidays "
					+ " WHERE id =?";

			//SQL命令を準備する
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			//SQL命令を発行する
			int result = pStmt.executeUpdate();

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
		//読み込んだ結果を返す
		return true;
	}

	/**
	 *
	 * @param member
	 * @return
	 */
	public boolean update(Holiday holiday) {

		String sql = "";

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return false;

		try {
			//SQL文を定義する
	        sql = "UPDATE"
	        		+ " holidays"
	        		+ " SET "
	        		+ " name = ?, "
	        		+ " date = ? "
	        		+ " WHERE "
	        		+ " id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// プレースホルダに値を設定
			pStmt.setString(1, holiday.getName());

			// sql.Dateへ変換して代入
			java.sql.Date holiday_date = getSQLDate(holiday.getDate());
			pStmt.setDate(2, holiday_date);

			pStmt.setInt(3, holiday.getId());

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
	 *
	 * @param date
	 * @return
	 */
	private static java.sql.Date getSQLDate(java.util.Date date) {
		java.sql.Date SQL_date = new java.sql.Date(date.getTime());
	    return SQL_date;
	}




	/**
	 * Holidaysテーブルからidの最大値を取得する
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
			String sql = "SELECT MAX(id) FROM holidays";
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
			sql = "DELETE FROM holidays "
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


	/**
	 *  createAtOnce
	 * @param List<Holiday> holidayList
	 * @return
	 */
	public boolean createAtOnce(List<Holiday> holidayList) {

		String sql = "";
		int n_holidayList = holidayList.size();

		//データベースに接続
		Connection conn = null;
		conn = DbConnection.conn;
		if(conn == null) return false;

		try {

			//SQL文
			sql = "INSERT INTO"
					+ " holidays"
					+ " (name, date)"
					+ " VALUES";
			for(int i=0; i<n_holidayList-1; i++) {
				sql += " (?,?),";
			}sql += " (?,?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			for(int i=0; i<n_holidayList; i++) {
				// holidayList(i)番目の要素のnameを代入
				pStmt.setString((1+2*i), holidayList.get(i).getName());
				// holidayList(i)番目の要素のdateをsql.Dateへ変換して代入
				java.sql.Date holiday_date = getSQLDate(holidayList.get(i).getDate());
				pStmt.setDate(2+2*i, holiday_date);
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
}
