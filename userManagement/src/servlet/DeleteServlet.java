package servlet;

import java.io.IOException;

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


@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
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
		
		int id = Integer.parseInt(request.getParameter("id"));
		 
		AccountDAO dao = new AccountDAO();
		 
		// 削除内容表示用
		AccountBean accountBean = dao.findId(id);
		session.setAttribute("deleteAccountBean", accountBean);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/delete.jsp");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		AccountDAO dao = new AccountDAO();
		
		// セッションスコープから取り出し、代入する。
		AccountBean accountBean = (AccountBean) session.getAttribute("deleteAccountBean");
		
		// 削除処理を行う。
		if (dao.delete(accountBean)) {
			request.setAttribute("deleteAccountBean", accountBean);
			request.setAttribute("deleteMsg", "以下内容を削除しました。");
		} else {
			request.setAttribute("deleteMsg", "削除に失敗しました。もう一度時間をあけて削除してください。");
		}

		// 削除内容をセッションスコープから削除する。
		session.removeAttribute("deleteAccountBean");
		
		RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/deleteDone.jsp");
		dispatch.forward(request, response);
	}

}
