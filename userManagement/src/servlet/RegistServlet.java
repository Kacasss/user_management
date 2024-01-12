package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import validation.AccountValidation;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
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
		
		// 登録情報が既にある場合は、登録情報を削除する。
		AccountBean accountBean = (AccountBean) session.getAttribute("registAccountBean");
		if (accountBean != null) {
			session.removeAttribute("registAccountBean");
		}
		
		// 新規登録画面の各項目をブランクにしておきたい為、メニュー画面から新規登録に移動したかどうか確認。
		if (request.getParameter("first") != null) {
			
			// regist.jspの<input name="mail" value="&{registMail}">等の内容を破棄する。
			session.removeAttribute("registMail");
			session.removeAttribute("registName"); 
			session.removeAttribute("registAge");
			
			// regist.jspの<c:if test="${registErrors != null && registErrors.size() > 0}">の内容を破棄する。
			session.removeAttribute("registErrors");
		}
		
		// 登録確認から戻るを押した場合、エラーメッセージを削除する。
		String back = request.getParameter("back");
		 if (back != null) {
			 session.removeAttribute("registErrors");
		}

		// ログイン中の場合は、新規登録画面へ移動
		RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/regist.jsp");
		dispatch.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// フォームで入力されたパスワード、パスワード確認、メールアドレス、名前、年齢を代入する。
		// 年齢は、一旦文字列で代入し、後ほど数値に変換する。
		String pass = request.getParameter("pass");
		String passConfirm = request.getParameter("passConfirm");
		String mail = request.getParameter("mail");
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");
		
		// <input name="mail or name" value="&{registMail or registName}">で入力した値を保持する為にセッションスコープに保存。
		if (mail == null) {
			mail = "";
		}
		session.setAttribute("registMail", mail);
		
		if (name == null) {
			name = "";
		}
		session.setAttribute("registName", name);
		
		List<String> registErrors = new ArrayList<>();
		
		// age が入力されているか確認。
		int age = 0;
		if (strAge != null) {
			try {
				age =  Integer.parseInt(strAge);                             // 文字列を数値に変換
				session.setAttribute("registAge", strAge);                   // セッションスコープに保存
			} catch (NumberFormatException e) {
				registErrors.add("年齢が入力されていません。数字で入力して下さい。");     // 正しく入力されていない場合はエラーをセッションスコープに保存
				session.setAttribute("registErrors", registErrors);
			}
		}
		
		// 入力内容のバリデーションチェック。
		// パスワード、パスワード確認、メールアドレス、名前、年齢を渡し、内容が正しく入力されているか確認する。
		AccountValidation validation = new AccountValidation();
		registErrors = validation.registUpdate(registErrors, pass, passConfirm, mail, name, age);	
		
		// バリデーションエラーがあった場合は、新規登録画面にリダイレクトし、以降の処理をやめる。
		if (registErrors.size() > 0) {
			session.setAttribute("registErrors", registErrors);
			response.sendRedirect("/userManagement/RegistServlet");
			return;
		}
		
		// 登録内容表示用
		AccountBean accountBean = new AccountBean(pass, mail, name, age);
		session.setAttribute("registAccountBean", accountBean);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/registConfirm.jsp");
		dispatch.forward(request, response);
	}
	
}
