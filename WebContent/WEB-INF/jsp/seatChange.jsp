<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="model.Member"%>

<%
// members 配列取得
int[] id_s = (int[]) request.getAttribute("id_s");
String[] members = (String[]) request.getAttribute("members");
int[] seat_id_s = (int[]) request.getAttribute("seat_id_s");
int[] gender_s = (int[]) request.getAttribute("gender_s");
int[] position_request_s = (int[]) request.getAttribute("position_request_s");

// アプリの実行回数の取得
int count = (int) request.getAttribute("count");

// 日付情報取得
int start_date_mode = (int) request.getAttribute("start_date_mode");
int current_year = (int) request.getAttribute("current_year");
int start_date_year = (int) request.getAttribute("start_date_year");
int start_date_month = (int) request.getAttribute("start_date_month");
int start_date_day = (int) request.getAttribute("start_date_day");
String day_of_week = (String) request.getAttribute("day_of_week");
int selected_week = (int) request.getAttribute("selected_week");

// ユーザー名取得
String user_name = (String) session.getAttribute("user_name");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/style.css">
<title>Seat Change Application </title>
</head>
<body>

	<div class="container">
		<h1>Seat Change Application (席替えアプリ)</h1>
		<div class="flex_space-around">
			<p>
				<a href="SeatChangeServlet?pge_id=1">CMS ログイン</a>
			</p>
			<p class="logout">
				<%= user_name %>
				さまオンライン中 <a href="UserLogout">ログアウト</a>
			</p>
		</div>

		<h3><%= start_date_year %>年<%= start_date_month %>月<%= start_date_day %>日(<%= day_of_week %>) からの座席表</h3>
		<p><%= count %>回目の実行結果</p>

		<form action= "<%= request.getContextPath() + "/SeatChangeServlet" %>"
			method="post">

<!-- 			週で実行する場合 -->
			<% if(start_date_mode==0){  %>
				<select name="selected_week" >
					<option value="0" <%if(selected_week==0){%> selected <%}%>>今週からスタート</option>
					<option value="1" <%if(selected_week==1){%> selected <%}%>>来週からスタート</option>
					<option value="2" <%if(selected_week==2){%> selected <%}%>>2週間後からスタート</option>
				</select>
				または、
				<select name="selected_year" class="selected_date">
					<option value="0" selected>年</option>
					<option value="<%= current_year %>"><%= current_year %>年</option>
					<option value="<%= current_year+1 %>"><%= current_year+1 %>年</option>
				</select>
				<select name="selected_month" class="selected_date">
					<option value="0"  selected>月</option>
					<option value="1">1月</option>
					<option value="2">2月</option>
					<option value="3">3月</option>
					<option value="4">4月</option>
					<option value="5">5月</option>
					<option value="6">6月</option>
					<option value="7">7月</option>
					<option value="8" >8月</option>
					<option value="9" >9月</option>
					<option value="10">10月</option>
					<option value="11">11月</option>
					<option value="12">12月</option>
				</select>
				<select name="selected_day" class="selected_date">
					<option value="0" selected>日</option>
					<option value="1">1日</option>
					<option value="2">2日</option>
					<option value="3">3日</option>
					<option value="4">4日</option>
					<option value="5">5日</option>
					<option value="6">6日</option>
					<option value="7">7日</option>
					<option value="8">8日</option>
					<option value="9">9日</option>
					<option value="10">10日</option>
					<option value="11">11日</option>
					<option value="12">12日</option>
					<option value="13">13日</option>
					<option value="14">14日</option>
					<option value="15">15日</option>
					<option value="16">16日</option>
					<option value="17">17日</option>
					<option value="18">18日</option>
					<option value="19">19日</option>
					<option value="20">20日</option>
					<option value="21">21日</option>
					<option value="22">22日</option>
					<option value="23">23日</option>
					<option value="24">24日</option>
					<option value="25">25日</option>
					<option value="26">26日</option>
					<option value="27">27日</option>
					<option value="28">28日</option>
					<option value="29">29日</option>
					<option value="30">30日</option>
					<option value="31">31日</option>
				</select>

<!-- 			年月日で実行する場合 -->
			<% } else if(start_date_mode==1) { %>
				<select name="selected_week">
					<option value="0" <%if(selected_week==0){%> selected <%}%>>今週からスタート</option>
					<option value="1" <%if(selected_week==1){%> selected <%}%>>来週からスタート</option>
					<option value="2" <%if(selected_week==2){%> selected <%}%>>2週間後からスタート</option>
				</select>
				または、
				<select name="selected_year" class="selected_date">
					<option value="0">年</option>
					<option value="<%= current_year %>" <%if(start_date_year==current_year)  {%>selected<%}  %>><%= current_year %>年</option>
					<option value="<%= current_year+1 %>" <%if(start_date_year==current_year+1)  {%>selected<%}  %>><%= current_year+1 %>年</option>
				</select>
				<select name="selected_month" class="selected_date">
					<option value="0">月</option>
					<option value="1"  <%if(start_date_month==1)  {%>selected<%}  %>>1月</option>
					<option value="2"  <%if(start_date_month==2)  {%>selected<%}  %>>2月</option>
					<option value="3"  <%if(start_date_month==3)  {%>selected<%}  %>>3月</option>
					<option value="4"  <%if(start_date_month==4)  {%>selected<%}  %>>4月</option>
					<option value="5"  <%if(start_date_month==5)  {%>selected<%}  %>>5月</option>
					<option value="6"  <%if(start_date_month==6)  {%>selected<%}  %>>6月</option>
					<option value="7"  <%if(start_date_month==7)  {%>selected<%}  %>>7月</option>
					<option value="8"  <%if(start_date_month==8)  {%>selected<%}  %>>8月</option>
					<option value="9"  <%if(start_date_month==9)  {%>selected<%}  %>>9月</option>
					<option value="10" <%if(start_date_month==10) {%>selected<%} %>>10月</option>
					<option value="11" <%if(start_date_month==11) {%>selected<%} %>>11月</option>
					<option value="12" <%if(start_date_month==12) {%>selected<%} %>>12月</option>
				</select>
				<select name="selected_day" class="selected_date">
					<option value="0">日</option>
					<option value="1"  <%if(start_date_day==1)   {%>selected<%}  %>>1日</option>
					<option value="2"  <%if(start_date_day==2)   {%>selected<%}  %>>2日</option>
					<option value="3"  <%if(start_date_day==3)   {%>selected<%}  %>>3日</option>
					<option value="4"  <%if(start_date_day==4)   {%>selected<%}  %>>4日</option>
					<option value="5"  <%if(start_date_day==5)   {%>selected<%}  %>>5日</option>
					<option value="6"  <%if(start_date_day==6)   {%>selected<%}  %>>6日</option>
					<option value="7"  <%if(start_date_day==7)   {%>selected<%}  %>>7日</option>
					<option value="8"  <%if(start_date_day==8)   {%>selected<%}  %>>8日</option>
					<option value="9"  <%if(start_date_day==9)   {%>selected<%}  %>>9日</option>
					<option value="10" <%if(start_date_day==10) {%>selected<%}  %>>10日</option>
					<option value="11" <%if(start_date_day==11) {%>selected<%}  %>>11日</option>
					<option value="12" <%if(start_date_day==12) {%>selected<%}  %>>12日</option>
					<option value="13" <%if(start_date_day==13) {%>selected<%}  %>>13日</option>
					<option value="14" <%if(start_date_day==14) {%>selected<%}  %>>14日</option>
					<option value="15" <%if(start_date_day==15) {%>selected<%}  %>>15日</option>
					<option value="16" <%if(start_date_day==16) {%>selected<%}  %>>16日</option>
					<option value="17" <%if(start_date_day==17) {%>selected<%}  %>>17日</option>
					<option value="18" <%if(start_date_day==18) {%>selected<%}  %>>18日</option>
					<option value="19" <%if(start_date_day==19) {%>selected<%}  %>>19日</option>
					<option value="20" <%if(start_date_day==20) {%>selected<%}  %>>20日</option>
					<option value="21" <%if(start_date_day==21) {%>selected<%}  %>>21日</option>
					<option value="22" <%if(start_date_day==22) {%>selected<%}  %>>22日</option>
					<option value="23" <%if(start_date_day==23) {%>selected<%}  %>>23日</option>
					<option value="24" <%if(start_date_day==24) {%>selected<%}  %>>24日</option>
					<option value="25" <%if(start_date_day==25) {%>selected<%}  %>>25日</option>
					<option value="26" <%if(start_date_day==26) {%>selected<%}  %>>26日</option>
					<option value="27" <%if(start_date_day==27) {%>selected<%}  %>>27日</option>
					<option value="28" <%if(start_date_day==28) {%>selected<%}  %>>28日</option>
					<option value="29" <%if(start_date_day==29) {%>selected<%}  %>>29日</option>
					<option value="30" <%if(start_date_day==30) {%>selected<%}  %>>30日</option>
					<option value="31" <%if(start_date_day==31) {%>selected<%}  %>>31日</option>
				</select>
			<% } %>

			スタートで下の席替えボタンを押してください。(休日の場合は翌営業日になります。)

			<div class="teacher_platform">
				<div class="platform">
					<input type="submit" value="席替え">
				</div>
				<div class="platform flex_center">
					<p><img src="img/teacher.png" alt="講師"></p>
					<p>講師</p>
				</div>
			</div>
			<div class="seats">
				<%
				for (int i = 0 ; i < members.length; i++) {
				%>
					<% if(position_request_s[i]==1) { %>
						<div class="seat front flex_center">
							<p>
								<% if(gender_s[i] == 1) { %>
									<img src="img/male.png" alt="男性">
								<% } else if(gender_s[i] == 2) { %>
									<img src="img/female.png" alt="女性">
								<% } %>
							</p>
							<p><%= members[i] %></p>
						</div>
					<% } %>

					<% if(position_request_s[i]==2) { %>
						<div class="seat middle flex_center">
							<p>
								<% if(gender_s[i] == 1) { %>
									<img src="img/male.png" alt="男性">
								<% } else if(gender_s[i] == 2) { %>
									<img src="img/female.png" alt="女性">
								<% } %>
							</p>
							<p><%= members[i] %></p>
						</div>
					<% } %>

					<% if(position_request_s[i]==3) { %>
						<div class="seat back flex_center">
							<p>
								<% if(gender_s[i] == 1) { %>
									<img src="img/male.png" alt="男性">
								<% } else if(gender_s[i] == 2) { %>
									<img src="img/female.png" alt="女性">
								<% } %>
							</p>
							<p><%= members[i] %></p>
						</div>
					<% } %>
				<%
				} // endfor
				%>
			</div>
		</form>


		<div><input type="button" value="年月日のクリア" onclick="clickBtnClearYearMonthDay()"/></div>

	</div>
	<script>
		function clickBtnClearYearMonthDay(){

			const selected_date = document.getElementsByClassName('selected_date')


			for ( let i = 0; i < selected_date.length; i ++ ){
				selected_date[i].selectedIndex = 0;
			}
		}
	</script>
</body>
</html>





