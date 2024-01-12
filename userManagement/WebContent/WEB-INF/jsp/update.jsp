<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<h1>変更</h1>
	
	<c:choose>
		<%-- 変更画面が表示される。 --%>
		<c:when test="${updateAccountBean != null}">
			<%-- 変更画面で、バリデーションエラーがあった場合は、こちらにエラー内容を表示する --%>
			<c:if test="${updateErrors != null && updateErrors.size() > 0}">
				<c:forEach var="error" items="${updateErrors}">
					<p class="error">${error}</p>
				</c:forEach>
			</c:if>
			
			<form class="form update" method="post" action="/userManagement/UpdateServlet">
				<table>
					<tr>
						<td>
							<input type="hidden" name="id" value="${updateId}">
						</td>
					</tr>
					
					<tr>
						<td class="form-column column-update">パスワード</td>
						<td>
							<input type="password" name="pass">
						</td>
					</tr>
					
					<tr>
						<td class="form-column column-update">パスワード確認</td>
						<td>
							<input type="password" name="passConfirm">
						</td>
					</tr>
					
					<tr>
						<td class="form-column column-update">メールアドレス</td>
						<td>
							<input type="email" name="mail" value="${updateMail}">
						</td>
					</tr>
					
					<tr>
						<td class="form-column column-update">名前</td>
						<td>
							<input type="text" name="name" value="${updateName}">
						</td>
					</tr>
					
					<tr>
						<td class="form-column column-update">年齢</td>
						<td>
							<input type="number" name="age" value="${updateAge}">
						</td>
					</tr>
				</table>
				<input type="submit" value="変更確認">
			</form>
		</c:when>
		<%-- DB接続不可等のエラーがあった場合、こちらにエラー内容が表示される。 --%>
		<c:otherwise>
			<p>${updateSqlError}</p>
		</c:otherwise>
	</c:choose>	
	
	<ul>
		<li>
			<a class="back" href="/userManagement/SearchUpdateDeleteServlet">戻る</a>
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