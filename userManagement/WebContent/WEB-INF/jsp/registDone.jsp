<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<h1>登録処理</h1>

	<c:choose>
		<%-- 登録が成功した場合、こちらが表示される。 --%>
		<c:when test="${success != null}">
			<p>${registMsg}</p>
				
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
		</c:when>
		<%-- エラーがあった場合、こちらにエラー内容が表示される。 --%>
		<c:otherwise>
			<p>${registMsg}</p>
		</c:otherwise>
	</c:choose>
	
	<ul>
		<li>
			<a class="menu" href="/userManagement/MenuServlet">メニューへ戻る</a>
		</li>
		<li>
			<a href="/userManagement/LogoutServlet">ログアウト</a>
		</li>
	</ul>

</body>
</html>