<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<h1>変更確認</h1>
	<p>以下内容で変更します。</p>
	<table class="update-table" border="1">
		<tr>
			<th class="update-th">パスワード</th>
			<th class="update-th">メールアドレス</th>
			<th class="update-th">名前</th>
			<th class="update-th">年齢</th>
		</tr>
		<tr>
			<td class="update-td">${updateAccountBean.pass}</td>
			<td class="update-td">${updateAccountBean.mail}</td>
			<td class="update-td">${updateAccountBean.name}</td>
			<td class="update-td">${updateAccountBean.age}</td>
		</tr>
	</table>
	
	<form class="form-update-confirm" method="post" action="/userManagement/UpdateConfirmServlet">
		<input type="submit" value="変更">
	</form>
	
	<ul>
		<li>
			<a class="back" href="/userManagement/UpdateServlet?id=${updateAccountBean.id}">戻る</a>
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