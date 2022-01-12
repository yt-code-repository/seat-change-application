<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.Holiday"%>
<%@ page import="java.util.Date"%>

<%
// fncパラメーター 取得
int fnc = (int) request.getAttribute("fnc");
// errorMsgパラメーター 取得
String errorMsg = (String) request.getAttribute("errorMsg");
// ユーザー名取得
String loginUser_name = (String) session.getAttribute("loginUser_name");
%>

<!DOCTYPE html>
<html>
<head>
<!-- コメントアウト風になっているのはIEの対応 -->
<script>
<!--
	function check(fnc) {
		var str;

		if (fnc == 1) {
			str = '祝日情報を登録します。よろしいですか？';
		} else if (fnc == 2) {
			str = '祝日情報を更新します。よろしいですか？';
		} else if (fnc == 3) {
			str = '祝日情報を削除します。よろしいですか？';
		} else if (fnc == 10) {
			str = '祝日登録件数を変更します。よろしいですか？';
		} else if (fnc == 11) {
			str = '祝日情報を新規一括登録します。よろしいですか？';
		}

		if (window.confirm(str)) {
			var ele = document.createElement('input');
			// データを設定
			ele.setAttribute('type', 'hidden');
			ele.setAttribute('name', 'fnc');
			ele.setAttribute('value', fnc);
			// 要素を追加
			document.inputForm.appendChild(ele);

			return true;
		} else {
			//window.alert('キャンセルされました');
			return false;
		}
	}


// -->
</script>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/style-cms.css">
<title>CMS 祝日の編集</title>
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

		<h2>祝日の編集</h2>

		<% if(errorMsg!=null){ %>
			<p style="color: #ff0000;">
				<%= errorMsg %>
			</p>
		<% } %>

		<form name="inputForm" action="HolidayEdit" method="post">
			<!-- 祝日 更新 -->
			<% if(fnc==0 || (fnc ==1 && errorMsg!=null ) || fnc==2 || fnc==3){ %>
			<%
			// 変更削除の場合：holiday 取得
			Holiday holiday = (Holiday) request.getAttribute("holiday");
			int holiday_year = (int) request.getAttribute("holiday_year");
			int holiday_month = (int) request.getAttribute("holiday_month");
			int holiday_day = (int) request.getAttribute("holiday_day");
			%>

				<input type="hidden" name="holiday_id" value="<%= holiday.getId() %>">


				<div class="seat_cms flex_space-around">
					<p>名前</p>
					<p><input type="text" name="name"
						value="<%= holiday.getName() %>" size="30" required></p>
				</div>
				<div class="seat_cms flex_space-around">
					<p>年(西暦)</p>
					<p>
						<select name="year">
							<option value="<%= holiday_year %>" selected><%= holiday_year %></option>
							<option value="<%= holiday_year + 1 %>"><%= holiday_year + 1 %></option>
							<option value="<%= holiday_year + 2 %>"><%= holiday_year + 2 %></option>
						</select>
					</p>
				</div>
				<div class="seat_cms flex_space-around">
					<p>月</p>
					<p>
						<select name="month">
							<option value="1"   <%if(holiday_month==1) {%>selected<%} %>>1</option>
							<option value="2"   <%if(holiday_month==2) {%>selected<%} %>>2</option>
							<option value="3"   <%if(holiday_month==3) {%>selected<%} %>>3</option>
							<option value="4"   <%if(holiday_month==4) {%>selected<%} %>>4</option>
							<option value="5"   <%if(holiday_month==5) {%>selected<%} %>>5</option>
							<option value="6"   <%if(holiday_month==6) {%>selected<%} %>>6</option>
							<option value="7"   <%if(holiday_month==7) {%>selected<%} %>>7</option>
							<option value="8"   <%if(holiday_month==8) {%>selected<%} %>>8</option>
							<option value="9"   <%if(holiday_month==9) {%>selected<%} %>>9</option>
							<option value="10"  <%if(holiday_month==10){%>selected<%}%>>10</option>
							<option value="11"  <%if(holiday_month==11){%>selected<%}%>>11</option>
							<option value="12"  <%if(holiday_month==12){%>selected<%}%>>12</option>
						</select>
					</p>
				</div>
				<div class="seat_cms flex_space-around">
					<p>日</p>
					<p>
						<select name="day">
							<option value="1"   <%if(holiday_day==1) {%>selected<%} %>>1</option>
							<option value="2"   <%if(holiday_day==2) {%>selected<%} %>>2</option>
							<option value="3"   <%if(holiday_day==3) {%>selected<%} %>>3</option>
							<option value="4"   <%if(holiday_day==4) {%>selected<%} %>>4</option>
							<option value="5"   <%if(holiday_day==5) {%>selected<%} %>>5</option>
							<option value="6"   <%if(holiday_day==6) {%>selected<%} %>>6</option>
							<option value="7"   <%if(holiday_day==7) {%>selected<%} %>>7</option>
							<option value="8"   <%if(holiday_day==8) {%>selected<%} %>>8</option>
							<option value="9"   <%if(holiday_day==9) {%>selected<%} %>>9</option>
							<option value="10"  <%if(holiday_day==10){%>selected<%}%>>10</option>
							<option value="11"  <%if(holiday_day==11){%>selected<%}%>>11</option>
							<option value="12"  <%if(holiday_day==12){%>selected<%}%>>12</option>
							<option value="13"  <%if(holiday_day==13){%>selected<%}%>>13</option>
							<option value="14"  <%if(holiday_day==14){%>selected<%}%>>14</option>
							<option value="15"  <%if(holiday_day==15){%>selected<%}%>>15</option>
							<option value="16"  <%if(holiday_day==16){%>selected<%}%>>16</option>
							<option value="17"  <%if(holiday_day==17){%>selected<%}%>>17</option>
							<option value="18"  <%if(holiday_day==18){%>selected<%}%>>18</option>
							<option value="19"  <%if(holiday_day==19){%>selected<%}%>>19</option>
							<option value="20"  <%if(holiday_day==20){%>selected<%}%>>20</option>
							<option value="21"  <%if(holiday_day==21){%>selected<%}%>>21</option>
							<option value="22"  <%if(holiday_day==22){%>selected<%}%>>22</option>
							<option value="23"  <%if(holiday_day==23){%>selected<%}%>>23</option>
							<option value="24"  <%if(holiday_day==24){%>selected<%}%>>24</option>
							<option value="25"  <%if(holiday_day==25){%>selected<%}%>>25</option>
							<option value="26"  <%if(holiday_day==26){%>selected<%}%>>26</option>
							<option value="27"  <%if(holiday_day==27){%>selected<%}%>>27</option>
							<option value="28"  <%if(holiday_day==28){%>selected<%}%>>28</option>
							<option value="29"  <%if(holiday_day==29){%>selected<%}%>>29</option>
							<option value="30"  <%if(holiday_day==30){%>selected<%}%>>30</option>
							<option value="31"  <%if(holiday_day==31){%>selected<%}%>>31</option>
						</select>
					</p>
				</div>
				<p class="btn">
					<input class="btns btn-clear" type="submit" name="delete"
						value="削除" onClick="return check(3)">

					<input class="btns btn-send" type="submit" name="save"
						value="変更" onClick="return check(2)">
				</p>

			<!-- 祝日 新規登録 -->
			<% } else if(fnc==1) { %>
			<%
			// 新規登録の場合：Current年月日 取得
			int current_year = (int) request.getAttribute("current_year");
			int current_month = (int) request.getAttribute("current_month");
			int current_date = (int) request.getAttribute("current_date");
			%>
			<%-- 祝日の新規作成 --%>
				<input type="hidden" name="holiday_id" value="-1">


				<div class="seat_cms flex_space-around">
					<p>名前</p>
					<p><input type="text" name="name" value=""
						size="30" required></p>
				</div>
				<div class="seat_cms flex_space-around">
					<p>年(西暦)</p>
					<p>
						<select name="year">
							<option value="<%= current_year %>" selected><%= current_year %></option>
							<option value="<%= current_year + 1 %>"><%= current_year + 1 %></option>
						</select>
					</p>
				</div>
				<div class="seat_cms flex_space-around">
					<p>月</p>
					<p>
						<select name="month">
							<option value="1"   <%if(current_month==1) {%>selected<%} %>>1</option>
							<option value="2"   <%if(current_month==2) {%>selected<%} %>>2</option>
							<option value="3"   <%if(current_month==3) {%>selected<%} %>>3</option>
							<option value="4"   <%if(current_month==4) {%>selected<%} %>>4</option>
							<option value="5"   <%if(current_month==5) {%>selected<%} %>>5</option>
							<option value="6"   <%if(current_month==6) {%>selected<%} %>>6</option>
							<option value="7"   <%if(current_month==7) {%>selected<%} %>>7</option>
							<option value="8"   <%if(current_month==8) {%>selected<%} %>>8</option>
							<option value="9"   <%if(current_month==9) {%>selected<%} %>>9</option>
							<option value="10"  <%if(current_month==10){%>selected<%}%>>10</option>
							<option value="11"  <%if(current_month==11){%>selected<%}%>>11</option>
							<option value="12"  <%if(current_month==12){%>selected<%}%>>12</option>
						</select>
					</p>
				</div>
				<div class="seat_cms flex_space-around">
					<p>日</p>
					<p>
						<select name="day">
							<option value="1"   <%if(current_date==1) {%>selected<%} %>>1</option>
							<option value="2"   <%if(current_date==2) {%>selected<%} %>>2</option>
							<option value="3"   <%if(current_date==3) {%>selected<%} %>>3</option>
							<option value="4"   <%if(current_date==4) {%>selected<%} %>>4</option>
							<option value="5"   <%if(current_date==5) {%>selected<%} %>>5</option>
							<option value="6"   <%if(current_date==6) {%>selected<%} %>>6</option>
							<option value="7"   <%if(current_date==7) {%>selected<%} %>>7</option>
							<option value="8"   <%if(current_date==8) {%>selected<%} %>>8</option>
							<option value="9"   <%if(current_date==9) {%>selected<%} %>>9</option>
							<option value="10"  <%if(current_date==10){%>selected<%}%>>10</option>
							<option value="11"  <%if(current_date==11){%>selected<%}%>>11</option>
							<option value="12"  <%if(current_date==12){%>selected<%}%>>12</option>
							<option value="13"  <%if(current_date==13){%>selected<%}%>>13</option>
							<option value="14"  <%if(current_date==14){%>selected<%}%>>14</option>
							<option value="15"  <%if(current_date==15){%>selected<%}%>>15</option>
							<option value="16"  <%if(current_date==16){%>selected<%}%>>16</option>
							<option value="17"  <%if(current_date==17){%>selected<%}%>>17</option>
							<option value="18"  <%if(current_date==18){%>selected<%}%>>18</option>
							<option value="19"  <%if(current_date==19){%>selected<%}%>>19</option>
							<option value="20"  <%if(current_date==20){%>selected<%}%>>20</option>
							<option value="21"  <%if(current_date==21){%>selected<%}%>>21</option>
							<option value="22"  <%if(current_date==22){%>selected<%}%>>22</option>
							<option value="23"  <%if(current_date==23){%>selected<%}%>>23</option>
							<option value="24"  <%if(current_date==24){%>selected<%}%>>24</option>
							<option value="25"  <%if(current_date==25){%>selected<%}%>>25</option>
							<option value="26"  <%if(current_date==26){%>selected<%}%>>26</option>
							<option value="27"  <%if(current_date==27){%>selected<%}%>>27</option>
							<option value="28"  <%if(current_date==28){%>selected<%}%>>28</option>
							<option value="29"  <%if(current_date==29){%>selected<%}%>>29</option>
							<option value="30"  <%if(current_date==30){%>selected<%}%>>30</option>
							<option value="31"  <%if(current_date==31){%>selected<%}%>>31</option>
						</select>
					</p>
				</div>

				<p class="btn">
					<input class="btns btn-clear" type="reset" value="クリア"> <input
						class="btns btn-send" type="submit" value="登録"
						onClick="return check(1)">
				</p>


			<!-- 祝日 新規一括登録 -->　　　　
<!-- 			&& errorMsg==null -->
			<% } else if(fnc>=10) { %>
			<%
			// 新規一括登録の場合：Current年月日 取得
			int number_of_registrations = (int) request.getAttribute("number_of_registrations");
			// holidays 配列取得
			int[] id_s = (int[]) request.getAttribute("id_s");
			String[] names = (String[]) request.getAttribute("names");
			int[] years = (int[]) request.getAttribute("years");
			int[] months = (int[]) request.getAttribute("months");
			int[] days = (int[]) request.getAttribute("days");
			// 今年の年数取得
			int current_year = (int) request.getAttribute("current_year");
			%>

				<div class="seat_cms flex_space-around">
					<p>id</p>
					<p>名前</p>
					<p>日付</p>
				</div>

				<%
				for (int i = 0; i < number_of_registrations; i++) {
				%>
					<% if (i <names.length) { %>
						<div class="seat_cms flex_space-around">
							<p>
								<input type="hidden" name="holiday_id" value="<%= i+1 %>">
							</p>
							<p>
								<%= i+1 %>
							</p>
							<p>
								<input type="text" name="name" value="<%= names[i] %>" size="20">
							</p>
							<p>
								<select name="years">
									<option value="0">年</option>
									<option value="<%= current_year-1 %>" <%if(years[i]==current_year-1){%>  selected  <%}%>><%= current_year-1 %>年</option>
									<option value="<%= current_year %>"   <%if(years[i]==current_year){%>    selected  <%}%>><%= current_year   %>年</option>
									<option value="<%= current_year+1 %>" <%if(years[i]==current_year+1){%>  selected  <%}%>><%= current_year+1 %>年</option>
									<option value="<%= current_year+2 %>" <%if(years[i]==current_year+2){%>  selected  <%}%>><%= current_year+2 %>年</option>
									<option value="<%= current_year+3 %>" <%if(years[i]==current_year+3){%>  selected  <%}%>><%= current_year+3 %>年</option>
								</select>
								<select name="months">
									<option value="0">月</option>
									<option value="1"  <%if(months[i]==1){%>  selected  <%}%>>1月</option>
									<option value="2"  <%if(months[i]==2){%>  selected  <%}%>>2月</option>
									<option value="3"  <%if(months[i]==3){%>  selected  <%}%>>3月</option>
									<option value="4"  <%if(months[i]==4){%>  selected  <%}%>>4月</option>
									<option value="5"  <%if(months[i]==5){%>  selected  <%}%>>5月</option>
									<option value="6"  <%if(months[i]==6){%>  selected  <%}%>>6月</option>
									<option value="7"  <%if(months[i]==7){%>  selected  <%}%>>7月</option>
									<option value="8"  <%if(months[i]==8){%>  selected  <%}%>>8月</option>
									<option value="9"  <%if(months[i]==9){%>  selected  <%}%>>9月</option>
									<option value="10" <%if(months[i]==10){%> selected <%}%>>10月</option>
									<option value="11" <%if(months[i]==11){%> selected <%}%>>11月</option>
									<option value="12" <%if(months[i]==12){%> selected <%}%>>12月</option>
								</select>
								<select name="days">
									<option value="0">日</option>
									<option value="1"  <%if(days[i]==1)   {%>selected<%}  %>>1日</option>
									<option value="2"  <%if(days[i]==2)   {%>selected<%}  %>>2日</option>
									<option value="3"  <%if(days[i]==3)   {%>selected<%}  %>>3日</option>
									<option value="4"  <%if(days[i]==4)   {%>selected<%}  %>>4日</option>
									<option value="5"  <%if(days[i]==5)   {%>selected<%}  %>>5日</option>
									<option value="6"  <%if(days[i]==6)   {%>selected<%}  %>>6日</option>
									<option value="7"  <%if(days[i]==7)   {%>selected<%}  %>>7日</option>
									<option value="8"  <%if(days[i]==8)   {%>selected<%}  %>>8日</option>
									<option value="9"  <%if(days[i]==9)   {%>selected<%}  %>>9日</option>
									<option value="10" <%if(days[i]==10) {%>selected<%}  %>>10日</option>
									<option value="11" <%if(days[i]==11) {%>selected<%}  %>>11日</option>
									<option value="12" <%if(days[i]==12) {%>selected<%}  %>>12日</option>
									<option value="13" <%if(days[i]==13) {%>selected<%}  %>>13日</option>
									<option value="14" <%if(days[i]==14) {%>selected<%}  %>>14日</option>
									<option value="15" <%if(days[i]==15) {%>selected<%}  %>>15日</option>
									<option value="16" <%if(days[i]==16) {%>selected<%}  %>>16日</option>
									<option value="17" <%if(days[i]==17) {%>selected<%}  %>>17日</option>
									<option value="18" <%if(days[i]==18) {%>selected<%}  %>>18日</option>
									<option value="19" <%if(days[i]==19) {%>selected<%}  %>>19日</option>
									<option value="20" <%if(days[i]==20) {%>selected<%}  %>>20日</option>
									<option value="21" <%if(days[i]==21) {%>selected<%}  %>>21日</option>
									<option value="22" <%if(days[i]==22) {%>selected<%}  %>>22日</option>
									<option value="23" <%if(days[i]==23) {%>selected<%}  %>>23日</option>
									<option value="24" <%if(days[i]==24) {%>selected<%}  %>>24日</option>
									<option value="25" <%if(days[i]==25) {%>selected<%}  %>>25日</option>
									<option value="26" <%if(days[i]==26) {%>selected<%}  %>>26日</option>
									<option value="27" <%if(days[i]==27) {%>selected<%}  %>>27日</option>
									<option value="28" <%if(days[i]==28) {%>selected<%}  %>>28日</option>
									<option value="29" <%if(days[i]==29) {%>selected<%}  %>>29日</option>
									<option value="30" <%if(days[i]==30) {%>selected<%}  %>>30日</option>
									<option value="31" <%if(days[i]==31) {%>selected<%}  %>>31日</option>
								</select>
							</p>
						</div>

					<% } else { %>
						<div class="seat_cms flex_space-around">
							<p>
								<input type="hidden" name="holiday_id" value="<%= i+1 %>">
							</p>
							<p>
								<%= i+1 %>
							</p>
							<p>
								<input type="text" name="name" value="" size="20">
							</p>
							<p>
								<select name="years">
									<option value="0" selected>年</option>
									<option value="<%= current_year-1 %>"><%= current_year-1 %>年</option>
									<option value="<%= current_year %>"><%= current_year %>年</option>
									<option value="<%= current_year+1 %>"><%= current_year+1 %>年</option>
									<option value="<%= current_year+2 %>"><%= current_year+2 %>年</option>
									<option value="<%= current_year+3 %>"><%= current_year+3 %>年</option>
								</select>
								<select name="months">
									<option value="0" selected>月</option>
									<option value="1"  >1月</option>
									<option value="2"  >2月</option>
									<option value="3"  >3月</option>
									<option value="4"  >4月</option>
									<option value="5"  >5月</option>
									<option value="6"  >6月</option>
									<option value="7"  >7月</option>
									<option value="8"  >8月</option>
									<option value="9"  >9月</option>
									<option value="10" >10月</option>
									<option value="11" >11月</option>
									<option value="12" >12月</option>
								</select>
								<select name="days">
									<option value="0">日</option>
									<option value="1"  >1日</option>
									<option value="2"  >2日</option>
									<option value="3"  >3日</option>
									<option value="4"  >4日</option>
									<option value="5"  >5日</option>
									<option value="6"  >6日</option>
									<option value="7"  >7日</option>
									<option value="8"  >8日</option>
									<option value="9"  >9日</option>
									<option value="10" >10日</option>
									<option value="11" >11日</option>
									<option value="12" >12日</option>
									<option value="13" >13日</option>
									<option value="14" >14日</option>
									<option value="15" >15日</option>
									<option value="16" >16日</option>
									<option value="17" >17日</option>
									<option value="18" >18日</option>
									<option value="19" >19日</option>
									<option value="20" >20日</option>
									<option value="21" >21日</option>
									<option value="22" >22日</option>
									<option value="23" >23日</option>
									<option value="24" >24日</option>
									<option value="25" >25日</option>
									<option value="26" >26日</option>
									<option value="27" >27日</option>
									<option value="28" >28日</option>
									<option value="29" >29日</option>
									<option value="30" >30日</option>
									<option value="31" >31日</option>
								</select>
							</p>
						</div>
					<% }%>

				<%
				} // endfor
				%>



				<p class="btn">
					<input class="btns btn-clear" type="reset" value="クリア">
					<input class="btns btn-send" type="submit" value="新規一括登録"
						onClick="return check(11)">
				</p>


				<h3>件数の変更</h3>
				<p class="btn">
					<input type="hidden" name="registration" value="2">

					一括登録する件数を選択してください。
					<select name="number_of_registrations">
						<option value="10"  <%if(number_of_registrations==10){%>selected<%}%>>10</option>
						<option value="11"  <%if(number_of_registrations==11){%>selected<%}%>>11</option>
						<option value="12"  <%if(number_of_registrations==12){%>selected<%}%>>12</option>
						<option value="13"  <%if(number_of_registrations==13){%>selected<%}%>>13</option>
						<option value="14"  <%if(number_of_registrations==14){%>selected<%}%>>14</option>
						<option value="15"  <%if(number_of_registrations==15){%>selected<%}%>>15</option>
						<option value="16"  <%if(number_of_registrations==16){%>selected<%}%>>16</option>
						<option value="17"  <%if(number_of_registrations==17){%>selected<%}%>>17</option>
						<option value="18"  <%if(number_of_registrations==18){%>selected<%}%>>18</option>
						<option value="19"  <%if(number_of_registrations==19){%>selected<%}%>>19</option>
						<option value="20"  <%if(number_of_registrations==20){%>selected<%}%>>20</option>
						<option value="21"  <%if(number_of_registrations==21){%>selected<%}%>>21</option>
						<option value="22"  <%if(number_of_registrations==22){%>selected<%}%>>22</option>
						<option value="23"  <%if(number_of_registrations==23){%>selected<%}%>>23</option>
						<option value="24"  <%if(number_of_registrations==24){%>selected<%}%>>24</option>
						<option value="25"  <%if(number_of_registrations==25){%>selected<%}%>>25</option>
					</select>
				</p>


				<p class="btn">
					<input class="btns btn-clear" type="reset" value="クリア">
					<input class="btns btn-send" type="submit" value="件数の変更"
						onClick="return check(10)">
				</p>

			<% } %>
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