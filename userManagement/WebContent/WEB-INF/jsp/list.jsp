<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<h1>社員一覧</h1>
	<p>名前はダミーデータを参考にして入力してます</p>
	
	<c:choose>
		<%-- 一覧画面が取得出来た場合、こちらが表示される。 --%>
		<c:when test="${limitAccountList != null || accountList != null}">
			<table class="list-table" border="1">
				<tr>
					<th class="list-th">パスワード</th>
					<th class="list-th">メールアドレス</th>
					<th class="list-th">名前</th>
					<th class="list-th">年齢</th>
				</tr>
		
				<c:forEach var="accountBean" items="${limitAccountList}">
					<tr>
						<td class="list-td">${accountBean.pass}</td>
						<td class="list-td">${accountBean.mail}</td>
						<td class="list-td">${accountBean.name}</td>
						<td class="list-td">${accountBean.age}</td>
					</tr>
				</c:forEach>
			</table>
			
			<%-- レコード数が5より大きい場合はページネーションを表示する。 --%>
			<c:if test="${accountList.size() > 5}">
				<table class="list-table" class="pagenation" border="1">
					<tr>
						<%-- for (int i = 0; i <= accountListPage - 1; i++) --%>
						<c:forEach var="i" begin="0" end="${accountListPage - 1}" step="1">
							<td>
								<%-- ${i + 1} は +1 が無ければ、0,1,2 となる為、 1,2,3 で表示する為に、 i に +1 している。--%>	
								<a class="a-pagination" href="/userManagement/ListServlet?pageStart=${5 * i}">${i + 1}</a>
							</td>
						</c:forEach>
					</tr>
				</table>
			</c:if>
			
		</c:when>
		<%-- エラーがあった場合、こちらにエラー内容が表示される。 --%>
		<c:otherwise>
			<p>${listMsg}</p>
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