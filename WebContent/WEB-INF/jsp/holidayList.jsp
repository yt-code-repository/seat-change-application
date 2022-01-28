<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.Date"%>

<%
// holidays 配列取得
// int current_year = (int) request.getAttribute("current_year");
int[] id_s = (int[]) request.getAttribute("id_s");
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
<title>CMS 祝日リスト</title>
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

			<h2>祝日一覧</h2>
			<p class="btn">
				<input class="btns btn-send" type="button" value="新規登録"
					onClick="location.href='HolidayEdit?fnc=1'">
			</p>
			<div class="seat_cms flex_space-around">
				<p>番号</p>
				<p>名前</p>
				<p>日付</p>
			</div>
			<%
			for (int i = 0; i < names.length; i++) {
			%>
				<div class="seat_cms flex_space-around">
					<p>
						<%= i+1 %>
					</p>
					<p>
						<a href="HolidayEdit?holiday_id=<%= id_s[i] %>"><%= names[i] %></a>
					</p>
					<p>
						<%= date_s[i] %>
					</p>
				</div>
			<%
			} // endfor
			%>

			<h2>新規一括登録</h2>
			<form name="inputForm" action="HolidayEdit" method="post">
				<p class="btn">
					<input type="hidden" name="fnc" value="10">
					<input type="hidden" name="registration" value="1">

					一括登録する件数を選択してください。
					<select name="number_of_registrations">
						<option value="10"  <%if(names.length==10){%>selected<%}%>>10</option>
						<option value="11"  <%if(names.length==11){%>selected<%}%>>11</option>
						<option value="12"  <%if(names.length==12){%>selected<%}%>>12</option>
						<option value="13"  <%if(names.length==13){%>selected<%}%>>13</option>
						<option value="14"  <%if(names.length==14){%>selected<%}%>>14</option>
						<option value="15"  <%if(names.length==15){%>selected<%}%>>15</option>
						<option value="16"  <%if(names.length==16){%>selected<%}%>>16</option>
						<option value="17"  <%if(names.length==17){%>selected<%}%>>17</option>
						<option value="18"  <%if(names.length==18){%>selected<%}%>>18</option>
						<option value="19"  <%if(names.length==19){%>selected<%}%>>19</option>
						<option value="20"  <%if(names.length==20){%>selected<%}%>>20</option>
						<option value="21"  <%if(names.length==21){%>selected<%}%>>21</option>
						<option value="22"  <%if(names.length==22){%>selected<%}%>>22</option>
						<option value="23"  <%if(names.length==23){%>selected<%}%>>23</option>
						<option value="24"  <%if(names.length==24){%>selected<%}%>>24</option>
						<option value="25"  <%if(names.length==25){%>selected<%}%>>25</option>
					</select>

					<input class="btns btn-clear" type="reset" value="クリア">
					<input class="btns btn-send" type="submit" value="新規一括登録">
				</p>
			</form>

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