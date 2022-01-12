<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Member"%>

<%
//fncパラメーター 取得
int fnc = (int) request.getAttribute("fnc");
//errorMsgパラメーター 取得
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
			str = 'メンバーを登録します。よろしいですか？';
		} else if (fnc == 2) {
			str = 'メンバーを更新します。よろしいですか？';
		} else if (fnc == 3) {
			str = 'メンバーを削除します。よろしいですか？';
		} else if (fnc == 11) {
			str = 'メンバーを新規一括登録します。よろしいですか？';
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
<title>CMS メンバーの編集</title>
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

		<h2>メンバーの編集</h2>

		<% if(errorMsg!=null){ %>
			<p style="color: #ff0000;">
				<%= errorMsg %>
			</p>
		<% } %>

		<form name="inputForm" action="MemberEdit" method="post">

			<!-- メンバー編集 -->
			<% if(fnc==0 || fnc==2 || fnc==3){ %>
			<%
			// 変更削除の場合：members 取得
			Member member = (Member) request.getAttribute("member");
			%>
				<input type="hidden" name="member_id" value="<%= member.getId() %>">
				<input type="hidden" name="seat_id" value="<%= member.getSeat_id() %>">

				<div class="seat_cms flex_space-around">
					<p>出席番号</p>
					<p><%= member.getId() %></p>
				</div>
				<div class="seat_cms flex_space-around">
					<p>名前</p>
					<p><input type="text" name="name"
						value="<%= member.getName() %>" size="20" required></p>
				</div>
				<div class="seat_cms flex_space-around">
					<p>出席状況</p>
					<p>
						<select name="enrolled">
							<option value="true"
								<% if(member.isEnrolled()==true) {%>
									selected
								<% } %>
									>1:在籍
							</option>
							<option value="false"
								<% if(member.isEnrolled()==false) {%>
									selected
								<% } %>
									>2:非在籍
							</option>
						</select>
					</p>
				</div>
				<div class="seat_cms flex_space-around">
					<p>性別</p>
					<p>
						<select name="gender">
							<option value="1"
								<% if(member.getGender()==1) {%>
									selected
								<% } %>
									>1:男
							</option>
							<option value="2"
								<% if(member.getGender()==2) {%>
									selected
								<% } %>
									>2:女
							</option>
						</select>
					</p>
				</div>
				<div class="seat_cms flex_space-around">
					<p>ポジションリクエスト</p>
					<p>
						<select name="position_request">
							<option value="1"
								<% if(member.getPosition_request()==1) {%>
									selected
								<% } %>
									>1:前の席</option>
							<option value="2"
								<% if(member.getPosition_request()==2) {%>
									selected
								<% } %>
									>2:中間の席
							</option>
							<option value="3"
								<% if(member.getPosition_request()==3) {%>
									selected
								<% } %>
									>3:後の席
							</option>
						</select>
					</p>
				</div>
				<p class="btn">

					<input class="btns btn-send" type="submit" name="save"
						value="変更" onClick="return check(2)">
				</p>

<!-- 			メンバー新規一括登録 -->
<!-- 			登録フォーム -->
			<% } else if(fnc>=10 && errorMsg==null) {%>
			<%
				// 新規登録の場合：Current年月日 取得
				int number_of_member = (int) request.getAttribute("number_of_member");
			%>



				<div class="seat_cms flex_space-around">
					<p>出席番号</p>
					<p>名前</p>
					<p>在籍状況</p>
					<p>性別</p>
					<p>希望場所</p>

				</div>
				<%
				for (int i = 0; i < number_of_member; i++) {
				%>
					<div class="seat_cms flex_space-around">
						<p>
							<input type="hidden" name="member_id" value="<%= i+1 %>">
							<input type="hidden" name="seat_id" value="<%= i+1 %>">
						</p>
						<p>
							<%= i+1 %>
						</p>
						<p>
							<input type="text" name="name" value="" size="20">
						</p>
						<p>
							<select name="enrolled">
								<option value="true" selected>1:在籍</option>
								<option value="false">2:非在籍</option>
							</select>
						</p>
						<p>
							<select name="gender">
								<option value="1" selected>1:男</option>
								<option value="2">2:女</option>
							</select>
						</p>
						<p>
							<select name="position_request">
								<option value="1">1:前の席</option>
								<option value="2" selected>2:中間の席</option>
								<option value="3">3:後ろの席</option>
							</select>
						</p>
					</div>
				<%
				} // endfor
				%>




				<p class="btn">
					<input class="btns btn-clear" type="reset" value="クリア">
					<input class="btns btn-send" type="submit"
						value="新規一括登録" onClick="return check(11)">
				</p>

<!-- 			新規一括登録 -->
<!-- 			エラーメッセージある時 -->
			<% } else if(fnc>=10 && errorMsg!=null) {%>
			<%
				// members 配列取得
				int[] id_s = (int[]) request.getAttribute("id_s");
				String[] members = (String[]) request.getAttribute("members");
				int[] seat_id_s = (int[]) request.getAttribute("seat_id_s");
				boolean[] enrolled = (boolean[]) request.getAttribute("enrolled");
				int[] gender_s = (int[]) request.getAttribute("gender_s");
				int[] position_request_s = (int[]) request.getAttribute("position_request_s");
			%>

				<div class="seat_cms flex_space-around">
					<p>出席番号</p>
					<p>名前</p>
					<p>在籍状況</p>
					<p>性別</p>
					<p>希望場所</p>
				</div>

				<%
				for (int i = 0; i<members.length; i++) {
				%>
					<div class="seat_cms flex_space-around">
						<p>
							<input type="hidden" name="member_id" value="<%= id_s[i] %>">
							<input type="hidden" name="seat_id" value="<%= seat_id_s[i] %>">
						</p>
						<p>
							<%= i+1 %>
						</p>
						<p>
							<input type="text" name="name" value="<%= members[i] %>" size="20">
						</p>
						<p>
							<select name="enrolled">
								<option value="true" <%if(enrolled[i]==true){%>selected<%}%>>1:在籍</option>
								<option value="false" <%if(enrolled[i]==false){%>selected<%}%>>2:非在籍</option>
							</select>
						</p>
						<p>
							<select name="gender">
								<option value="1" <%if(gender_s[i]==1){%>selected<%}%>>1:男</option>
								<option value="2" <%if(gender_s[i]==2){%>selected<%}%>>2:女</option>
							</select>
						</p>
						<p>
							<select name="position_request">
								<option value="1" <%if(position_request_s[i]==1){%>selected<%}%>>1:前の席</option>
								<option value="2" <%if(position_request_s[i]==2){%>selected<%}%>>2:中間の席</option>
								<option value="3" <%if(position_request_s[i]==3){%>selected<%}%>>3:後ろの席</option>
							</select>
						</p>
					</div>
				<%
				} // endfor
				%>


				<p class="btn">
					<input class="btns btn-clear" type="reset" value="クリア">
					<input class="btns btn-send" type="submit"
						value="新規一括登録" onClick="return check(11)">
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