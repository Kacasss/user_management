<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<h1>登録確認</h1>

	<p>以下内容で登録します。</p>
	<table class="regist-table" border="1">
		<tr>
			<th class="regist-th">パスワード</th>
			<th class="regist-th">メールアドレス</th>
			<th class="regist-th">名前</th>
			<th class="regist-th">年齢</th>
		</tr>
		<tr>
			<td class="regist-td">${registAccountBean.pass}</td>
			<td class="regist-td">${registAccountBean.mail}</td>
			<td class="regist-td">${registAccountBean.name}</td>
			<td class="regist-td">${registAccountBean.age}</td>
		</tr>
	</table>
	
	<form class="form-regist-confirm" method="post" action="/userManagement/RegistConfirmServlet">
		<input type="submit" value="登録">
	</form>
	
	<ul>
		<li>
			<a class="back" href="/userManagement/RegistServlet?back=registConfirm">戻る</a>
		</li>
		<li>
			<a class="menu" href="/userManagement/MenuServlet">メニューへ戻る</a>
		</li>
		<li>
			<a href="/userManagement/LogoutServlet">ログアウト</a>
		</li>
	</ul>

</body>
</html>