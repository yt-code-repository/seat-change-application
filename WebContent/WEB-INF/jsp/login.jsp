<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>席替えアプリ CMSへログイン</title>
</head>
<body>
	<div class="wrap">
		<div class="main">
			<h1>席替えアプリ CMSにログイン</h1>
			<div class="login">
				<form action="Login" method="post">
					<p>
						ユーザー名：<input type="text" name="login_id">
					</p>
					<p>
						パスワード：<input type="password" name="pass">
					</p>
					<p>
						<input class="btns btn-send" type="submit" value="ログイン">
					</p>
				</form>
			</div>
		</div>
	</div></body>
</html>