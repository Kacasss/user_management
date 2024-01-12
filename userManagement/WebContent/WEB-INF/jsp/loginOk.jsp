<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<h1>ログイン成功</h1>
	<p>${welcome}</p>
	
	<ul>
		<li>
			<a class="menu" href="/userManagement/MenuServlet">メニューへ</a>
		</li>
		<li>
			<a href="/userManagement/LogoutServlet">ログアウト</a>
		</li>
	</ul>

</body>
</html>