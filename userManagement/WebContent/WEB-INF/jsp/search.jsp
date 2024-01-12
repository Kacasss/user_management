<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<h1>検索</h1>
	<p class="p-search">ユーザーを検索します。名前を入力してください。</p>
	<p class="p-search">スペースは不要です。部分一致でも可能。</p>

	<form method="post" action="/userManagement/SearchUpdateDeleteServlet">
		<table class="search-table">
			<tr>
				<td class="search-name">名前</td>
				<td>
					<input type="text" name="name">
				</td>
			</tr>
		</table>
		
		<input type="reset" value="リセット">
		<input type="submit" value="検索">
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