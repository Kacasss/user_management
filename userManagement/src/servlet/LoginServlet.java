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

import beans.AdminLoginBean;
import dao.AccountDAO;
import validation.AdminLoginValidation;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// セッションスコープに、ログイン情報があるかどうか確認。
		// ある場合はadminLoginBean、ない場合はnullが代入される。
		AdminLoginBean adminLoginBean = (AdminLoginBean) session.getAttribute("adminLoginBean");
		
		// ログインしていない場合は、ログイン画面に移動する。
		 if (adminLoginBean == null) {
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatch.forward(request, response);
			return;
		}
		
		//　ログインしている場合は、MenuServletに移動。
		RequestDispatcher dispatch = request.getRequestDispatcher("/MenuServlet");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// フォームで入力されたパスワードとメールアドレスを代入する。
		String pass = request.getParameter("pass");
		String mail = request.getParameter("mail");
		
		// <input name="mail" value="&{adminLoginMail}">で入力した値を保持する為にセッションスコープに保存。
		// 入力された値がnullなら、adminLoginMail に ""、
		// nullでなければ、adminLoginMail に 入力内容が保持される。
		if (mail == null) {
			mail = "";
		}
		session.setAttribute("adminLoginMail", mail);

		// 入力内容のバリデーションチェック。
		// パスワードとメールアドレスを渡し、内容が正しく入力されているか確認する。
		AdminLoginValidation validation = new AdminLoginValidation();
		List<String> adminLoginErrors = validation.adminLogin(pass, mail);
		
		// バリデーションエラーがあった場合は、ログイン入力画面にリダイレクトし、以降の処理をやめる。
		if (adminLoginErrors.size() > 0) {
			session.setAttribute("adminLoginErrors", adminLoginErrors);
			response.sendRedirect("/userManagement/LoginServlet");
			return;
		}
		
		AccountDAO dao = new AccountDAO();
		
		// フォームの入力した内容がDBと一致してるか確認。一致してなければnullが代入される。
		AdminLoginBean adminLoginBean = dao.findAdminLogin(pass, mail);
		
		// フォーム内容と一致していれば、ログイン情報をセッションスコープに保存し、ログインOk画面に移動する。
		if (adminLoginBean != null) {
			request.setAttribute("welcome", "ようこそ、" + adminLoginBean.getName() + "さん");
			session.setAttribute("adminLoginBean", adminLoginBean);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/loginOk.jsp");
			dispatch.forward(request, response);
		} else {
			// DB接続に失敗、DB情報のパスワードかメールアドレスと相違していた場合は、
			// エラー内容をセッションスコープに保存し、ログイン画面に移動する。
			adminLoginErrors.add("パスワード、メールアドレスが違います。");
			session.setAttribute("adminLoginErrors", adminLoginErrors);
			response.sendRedirect("/userManagement/LoginServlet");
		}
	}
	
}
