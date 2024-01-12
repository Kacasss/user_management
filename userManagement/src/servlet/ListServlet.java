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

@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
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
		
		AccountDAO dao = new AccountDAO();
		
		// ページネーション用に5件ずつ表示する為、始まりのページを指定
		// (例)0~4 →　pageStart:0
		// (例)5~9 →　pageStart:5
		int pageStart = Integer.parseInt(request.getParameter("pageStart"));
		
		// 5件分のレコードを取得し、limitAccountListに格納
		List<AccountBean> limitAccountList = dao.limitList(pageStart);

		// 全レコードを取得し、AccountListに格納
		List<AccountBean> accountList = dao.list();
		
		if (limitAccountList != null && accountList != null) {
			// ページの表示数を計算する。
			// 全レコードを5で割った余りが0なら、5で割る。そうでなければ、5で割った数字に1を追加する。
			// (例:13レコードあれば、10 / 5 = 2 になる為、 2 + 1 で 3ページ分表示。)
			int accountListPage = 0;
			if (accountList.size() % 5 == 0) {
				accountListPage = (accountList.size() / 5);                // 10 % 5 == 2   余り0
			} else {
				accountListPage = (accountList.size() / 5) + 1;            // 13 % 5 == 2   余り3   2 + 1
			}
			
			// 各内容をリクエストスコープに保存する。
			request.setAttribute("pageStart", pageStart);                  // 始まりのページ
			request.setAttribute("limitAccountList", limitAccountList);    // 5件分のレコード
			request.setAttribute("accountListPage", accountListPage);      // 表示するページ数  13レコードなら3ページ
			
			//全レコード分をセッションスコープに保存する。
			session.setAttribute("accountList", accountList);
		} else {
			// DBに接続出来ない等のエラーがあった場合の処理。
			request.setAttribute("listMsg", "表示に失敗しました。時間を置いてアクセスして下さい。");
		}

		// ログイン中の場合は、一覧画面へ移動
		RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/list.jsp");
		dispatch.forward(request, response);
	}

}
