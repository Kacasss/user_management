<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<h1>新規登録</h1>
	
	<%-- 新規登録画面で、エラーがあった場合は、こちらにエラー内容を表示する --%>
	<c:if test="${registErrors != null && registErrors.size() > 0}">
		<c:forEach var="error" items="${registErrors}">
			<p class="error">${error}</p>
		</c:forEach>
	</c:if>
	
	<form class="form regist" method="post" action="/userManagement/RegistServlet">
		<table>
			<tr>
				<td class="form-column column-regist">パスワード</td>
				<td>
					<input type="password" name="pass">
				</td>
			</tr>
			
			<tr>
				<td class="form-column column-regist">パスワード確認</td>
				<td>
					<input type="password" name="passConfirm">
				</td>
			</tr>
			
			<tr>
				<td class="form-column column-regist">メールアドレス</td>
				<td>
					<input type="email" name="mail" value="${registMail}">
				</td>
			</tr>
			
			<tr>
				<td class="form-column column-regist">名前</td>
				<td>
					<input type="text" name="name" value="${registName}">
				</td>
			</tr>
			
			<tr>
				<td class="form-column column-regist">年齢</td>
				<td>
					<input type="number" name="age" value="${registAge}">
				</td>
			</tr>
		</table>
		<input type="submit" value="登録確認">
	</form>
	
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