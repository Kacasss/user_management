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


@WebServlet("/UpdateConfirmServlet")
public class UpdateConfirmServlet extends HttpServlet {
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
		
		// セッションスコープから入力した各項目を取り出し、代入する。
		AccountBean accountBean = (AccountBean) session.getAttribute("updateAccountBean");
		
		// 更新処理を行う。
		if (dao.update(accountBean)) {
			request.setAttribute("updateMsg", "以下内容で変更しました。");
		} else {
			request.setAttribute("updateMsg", "変更に失敗しました。もう一度時間をあけて変更してください。");
		}
		
		// 各フォームに入力した内容とエラー内容をセッションスコープから削除する。
		session.removeAttribute("updateId");
		session.removeAttribute("updateMail");
		session.removeAttribute("updateName"); 
		session.removeAttribute("updateAge");
		session.removeAttribute("updateErrors");
		
		RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/updateDone.jsp");
		dispatch.forward(request, response);
		
	}

}
