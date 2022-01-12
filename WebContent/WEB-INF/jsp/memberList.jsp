<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// members 配列取得
int[] id_s = (int[]) request.getAttribute("id_s");
String[] members = (String[]) request.getAttribute("members");
boolean[] enrolled = (boolean[]) request.getAttribute("enrolled");
int[] gender_s = (int[]) request.getAttribute("gender_s");
int[] position_request_s = (int[]) request.getAttribute("position_request_s");

// ユーザー名取得
String loginUser_name = (String) session.getAttribute("loginUser_name");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/style-cms.css">
<title>CMS メンバーリスト</title>
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

			<h2>メンバー一覧</h2>

			<div class="seat_cms flex_space-around">
				<p>出席番号</p>
				<p>名前</p>
				<p>在籍状況</p>
				<p>性別</p>
				<p>希望場所</p>
			</div>

			<%
			for (int i = 0; i < members.length; i++) {
			%>
				<div class="seat_cms flex_space-around">
					<p class="id">
						<%= i+1 %>
					</p>
					<p class="name">
						<a href="MemberEdit?member_id=<%= id_s[i] %>"><%= members[i] %></a>
					</p>
					<p class="enroled">
						<%= enrolled[i] %>
					</p>
					<p class="gender">
						<%= gender_s[i] %>
					</p>

					<p class="position_request">
						<% if(position_request_s[i]==1){ %>
							前の席
						<% } %>
						<% if(position_request_s[i]==2){ %>
							中間の席
						<% } %>
						<% if(position_request_s[i]==3){ %>
							後の席
						<% } %>
					</p>
				</div>
			<%
			} // endfor
			%>

			<h2>新規一括登録</h2>
			<form name="inputForm" action="MemberEdit" method="post">
				<p class="btn">
					<input type="hidden" name="fnc" value="10">
					一括登録する人数を選択してください。
					<select name="number_of_member">
						<option value="10"  <%if(members.length==10){%>selected<%}%>>10</option>
						<option value="11"  <%if(members.length==11){%>selected<%}%>>11</option>
						<option value="12"  <%if(members.length==12){%>selected<%}%>>12</option>
						<option value="13"  <%if(members.length==13){%>selected<%}%>>13</option>
						<option value="14"  <%if(members.length==14){%>selected<%}%>>14</option>
						<option value="15"  <%if(members.length==15){%>selected<%}%>>15</option>
						<option value="16"  <%if(members.length==16){%>selected<%}%>>16</option>
						<option value="17"  <%if(members.length==17){%>selected<%}%>>17</option>
						<option value="18"  <%if(members.length==18){%>selected<%}%>>18</option>
						<option value="19"  <%if(members.length==19){%>selected<%}%>>19</option>
						<option value="20"  <%if(members.length==20){%>selected<%}%>>20</option>
						<option value="21"  <%if(members.length==21){%>selected<%}%>>21</option>
						<option value="22"  <%if(members.length==22){%>selected<%}%>>22</option>
						<option value="23"  <%if(members.length==23){%>selected<%}%>>23</option>
						<option value="24"  <%if(members.length==24){%>selected<%}%>>24</option>
						<option value="25"  <%if(members.length==25){%>selected<%}%>>25</option>
						<option value="26"  <%if(members.length==26){%>selected<%}%>>26</option>
						<option value="27"  <%if(members.length==27){%>selected<%}%>>27</option>
						<option value="28"  <%if(members.length==28){%>selected<%}%>>28</option>
						<option value="29"  <%if(members.length==29){%>selected<%}%>>29</option>
						<option value="30"  <%if(members.length==30){%>selected<%}%>>30</option>
						<option value="31"  <%if(members.length==31){%>selected<%}%>>31</option>
						<option value="32"  <%if(members.length==32){%>selected<%}%>>32</option>
						<option value="33"  <%if(members.length==33){%>selected<%}%>>33</option>
						<option value="34"  <%if(members.length==34){%>selected<%}%>>34</option>
						<option value="35"  <%if(members.length==35){%>selected<%}%>>35</option>
						<option value="36"  <%if(members.length==36){%>selected<%}%>>36</option>
						<option value="37"  <%if(members.length==37){%>selected<%}%>>37</option>
						<option value="38"  <%if(members.length==38){%>selected<%}%>>38</option>
						<option value="39"  <%if(members.length==39){%>selected<%}%>>39</option>
						<option value="40"  <%if(members.length==40){%>selected<%}%>>40</option>
						<option value="41"  <%if(members.length==41){%>selected<%}%>>41</option>
						<option value="42"  <%if(members.length==42){%>selected<%}%>>42</option>
						<option value="43"  <%if(members.length==43){%>selected<%}%>>43</option>
						<option value="44"  <%if(members.length==44){%>selected<%}%>>44</option>
						<option value="45"  <%if(members.length==45){%>selected<%}%>>45</option>
						<option value="46"  <%if(members.length==46){%>selected<%}%>>46</option>
						<option value="47"  <%if(members.length==47){%>selected<%}%>>47</option>
						<option value="48"  <%if(members.length==48){%>selected<%}%>>48</option>
						<option value="49"  <%if(members.length==49){%>selected<%}%>>49</option>
						<option value="50"  <%if(members.length==50){%>selected<%}%>>50</option>
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