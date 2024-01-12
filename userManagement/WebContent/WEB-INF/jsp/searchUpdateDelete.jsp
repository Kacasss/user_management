<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<h1>変更・削除</h1>
	
	<c:choose>
		<%-- 検索済の一覧画面が取得出来た場合、こちらが表示される。 --%>
		<c:when test="${accountListName != null}">
			
			<p>以下内容を変更・削除します。</p>
			<table class="search-table" border="1">
				<tr>
					<th class="search-th">パスワード</th>
					<th class="search-th">メールアドレス</th>
					<th class="search-th">名前</th>
					<th class="search-th">年齢</th>
					<th class="search-th">変更</th>
					<th class="search-th">削除</th>
				</tr>
				
				<c:forEach var="accountBean" items="${accountListName}">
					<tr>
						<td class="search-td">${accountBean.pass}</td>
						<td class="search-td">${accountBean.mail}</td>
						<td class="search-td">${accountBean.name}</td>
						<td class="search-td">${accountBean.age}</td>
						<td class="search-td">
							<input class="update-btn" type="submit" name="search_id" value="変更" onclick="location.href='/userManagement/UpdateServlet?first=true&search_id=${accountBean.id}'">
						</td>
						<td class="search-td">
							<input class="delete-btn" type="submit" name="id" value="削除" onclick="location.href='/userManagement/DeleteServlet?id=${accountBean.id}'">
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<%-- エラーがあった場合、こちらにエラー内容が表示される。 --%>
		<c:otherwise>
			<p>${searchMsg}</p>
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