<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.Date"%>

<%
// members 配列取得
int[] id_s = (int[]) request.getAttribute("id_s");
String[] members = (String[]) request.getAttribute("members");
int[] seat_id_s = (int[]) request.getAttribute("seat_id_s");
boolean[] enrolled = (boolean[]) request.getAttribute("enrolled");
int[] gender_s = (int[]) request.getAttribute("gender_s");
int[] position_request_s = (int[]) request.getAttribute("position_request_s");

// holidays 配列取得
String[] names = (String[]) request.getAttribute("names");
Date[] date_s = (Date[]) request.getAttribute("date_s");

// ユーザー名取得
String loginUser_name = (String) session.getAttribute("loginUser_name");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/style-cms.css">
<title>メンバーリスト</title>
</head>
<body>


	<div class="container">
		<h1>Seat Change Application (席替えアプリ)</h1>
		<div class="flex_space-around">
			<p>
			</p>
			<p class="logout">
				<%= loginUser_name %>
				さまオンライン中 <a href="Logout">ログアウト</a>
			</p>
		</div>

		<div class ="opbtns">
		</div>

		<div class="division">
			<div>
				<div class="opbtns_item"><a href="CtrlForCms?pge_id=1">メンバー管理</a></div>

				<h2>メンバーリスト</h2>

				<div class="members">
					<div class="seat_cms flex_space-around">
							<p>出席番号</p>
							<p>名前</p>
							<p>在籍状況</p>
							<p>性別</p>
							<p>希望席</p>
					</div>

					<%
					for (int i = 0; i < members.length; i++) {
					%>
						<div class="seat_cms flex_space-around">
							<p><%= id_s[i] %></p>
							<p><%= members[i] %></p>
							<p>
								<% if(enrolled[i]==false) { %>
									非在籍
								<% } else if(enrolled[i]==true) { %>
									○
								<% } %>
							</p>
							<p>
								<% if(gender_s[i]==1) { %>
									男性
								<% } else if(gender_s[i]==2) { %>
									女性
								<% } %>
							</p>
							<p>
								<% if(position_request_s[i]==1) { %>
									前の席
								<% } else if(position_request_s[i]==2) { %>
									中間の席
								<% } else if(position_request_s[i]==3) { %>
									後ろの席
								<% } %>
							</p>
						</div>
					<%
					} // endfor
					%>
				</div>
			</div>

			<div>
				<div class="opbtns_item"><a href="CtrlForCms?pge_id=2">祝日管理</a></div>

				<h2>祝日リスト</h2>

				<div class="holidays">
					<div class="seat_cms flex_space-around">
						<p>番号</p>
						<p class="flex-item1">名前</p>
						<p>日付</p>
					</div>

					<%
					for (int i = 0; i < names.length; i++) {
					%>
						<div class="seat_cms flex_space-around">
							<p><%= i+1 %></p>
							<p class="flex-item1"><%= names[i] %></p>
							<p><%= date_s[i] %></p>
						</div>
					<%
					} // endfor
					%>
				</div>
			</div>

		</div>

		<div class="flex_space-around">
			<div class="opbtns_item">
				<a href="CtrlForCms?pge_id=0">マイページ</a>
			</div>
			<div class="opbtns_item">
				<a href="SeatChangeServlet?pge_id=0">アプリへ</a>
			</div>
		</div>

	</div>




</body>
</html>