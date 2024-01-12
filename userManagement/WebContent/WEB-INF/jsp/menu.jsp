<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<h1>メニュー</h1>
	<p>${adminLoginBean.name}さん、作業内容を選んで下さい。</p>
	
	<ul>
		<li>
			<%-- 5件ずつ表示する為、pageStart=0 を追加 --%>
			<a href="/userManagement/ListServlet?pageStart=0">一覧</a>
		</li>
		<li>
			<%-- メニューから新規登録画面に移動するのか、登録確認画面から新規登録画面に戻るのか
				　を区別する為に、first=true を追加
			--%>
			<a href="/userManagement/RegistServlet?first=true">登録</a>
		</li>
		<li>
			<a href="/userManagement/SearchUpdateDeleteServlet">編集・削除</a>
		</li>
		<li>
			<a href="/userManagement/LogoutServlet">ログアウト</a>
		</li>
	</ul>
</body>
</html>