<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<h1>削除処理</h1>

	<c:choose>
		<%-- エラーがなければ、削除完了画面が表示される。 --%>
		<c:when test="${deleteAccountBean != null}">
			<p>${deleteMsg}</p>
				
			<table class="delete-table" border="1">
				<tr>
					<th class="delete-th">パスワード</th>
					<th class="delete-th">メールアドレス</th>
					<th class="delete-th">名前</th>
					<th class="delete-th">年齢</th>
				</tr>
				<tr>
					<td class="delete-td">${deleteAccountBean.pass}</td>
					<td class="delete-td">${deleteAccountBean.mail}</td>
					<td class="delete-td">${deleteAccountBean.name}</td>
					<td class="delete-td">${deleteAccountBean.age}</td>
				</tr>
			</table>
		</c:when>
		<%-- エラーがあった場合、こちらにエラー内容が表示される。 --%>
		<c:otherwise>
			<p>${deleteMsg}</p>
		</c:otherwise>
	</c:choose>
	
	<ul>
		<li>
			<a href="/userManagement/SearchUpdateDeleteServlet">検索へ戻る</a>
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