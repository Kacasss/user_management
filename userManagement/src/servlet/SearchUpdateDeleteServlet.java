package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AccountBean;
import beans.AdminLoginBean;
import dao.AccountDAO;


@WebServlet("/SearchUpdateDeleteServlet")
public class SearchUpdateDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// セッションスコープに、ログイン情報があるかどうか確認。
		// ある場合はadminLoginBean、ない場合はnullが代入される。
		AdminLoginBean adminLoginBean = (AdminLoginBean) session.getAttribute("adminLoginBean");

		// ログインしてなければ、ログインへ移動
		 if (adminLoginBean == null) {
			 response.sendRedirect("/userManagement/LoginServlet");
			 return;
		}
		 
		// 名前検索で取得した複数のレコードを取得し、nullでない場合は削除する。
		List<AccountBean> accountListName = (List<AccountBean>) session.getAttribute("accountListName");
		if (accountListName != null) {
			session.removeAttribute("accountListName");
		}
		
		// ログイン中の場合は、検索画面へ移動
		RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/search.jsp");
		dispatch.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// フォームで入力された名前を代入する。
		String name = request.getParameter("name");
		
		AccountDAO dao = new AccountDAO();
		
		// 名前で検索をし、該当のレコードを取得する。なければ、nullが代入される。
		List<AccountBean> accountListName = dao.findName(name);

		// エラーがなければ、セッションスコープに保存し、エラーの場合はエラーMSGを保存する。
		if (accountListName != null) {
			session.setAttribute("accountListName", accountListName);
		} else {
			request.setAttribute("searchMsg", "DBに接続できないか、該当の名前がありませんでした。もう一度検索して下さい。");
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/searchUpdateDelete.jsp");
		dispatch.forward(request, response);
	}

}
