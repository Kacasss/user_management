<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<h1>ログイン</h1>

			<%-- ログイン画面で、エラーがあった場合は、こちらにエラー内容を表示する --%>
			<c:if test="${adminLoginErrors != null && adminLoginErrors.size() > 0}">
				<c:forEach var="error" items="${adminLoginErrors}">
					<p class="error">${error}</p>
				</c:forEach>
			</c:if>
				
			<form method="post" action="/userManagement/LoginServlet">
				<table>
					<tr>
						<td>メールアドレス : </td>
						<td>
							<input type="email" name="mail" value="${adminLoginMail}">
						</td>
					</tr>
					<tr>
						<td>パスワード : </td>
						<td>
							<input type="password" name="pass">
						</td>
					</tr>
				</table>
				<input class="submit" type="submit" value="ログイン">
			</form>
</body>
</html>