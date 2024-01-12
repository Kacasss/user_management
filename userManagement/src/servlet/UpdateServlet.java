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
import dao.AccountDAO;
import validation.AccountValidation;


@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
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
		
		String strId = request.getParameter("search_id");
		int id;
		
		// searchUpdateDelete.jspの検索画面から変更入力画面に移動した場合は、searchId、
		// updateConfirm.jspから変更入力画面に戻った場合は、updateId
		//
		// update.jspで<input name="mail" value="&{updateMail}">で表示する内容を入れ替える。
		// searchUpdateDeleteの場合は、一覧情報の各レコードから変更内容を取得するが、
		// updateConfirmの場合は、フォームで入力した変更内容を習得する必要がある。
		if (strId == null) {
			id = Integer.parseInt((String) session.getAttribute("updateId"));   // 変更確認画面から戻る
		} else {
			id = Integer.parseInt(request.getParameter("search_id"));           // 検索画面から移動
		}
		
		AccountDAO dao = new AccountDAO();
		
		// 取得の場合は1レコード、失敗の場合はnullを代入する。
		AccountBean accountBean = dao.findId(id);
		
		// nullではない場合は、accountBeanをセッションスコープに保存する。
		if (accountBean != null) {
			session.setAttribute("updateAccountBean", accountBean);
			
			// もし、検索画面からの移動の場合は、フォームに表示させる為に
			// 検索一覧画面から、各カラム情報を各セッションスコープに保存する。
			// また、エラー表示は削除する。
			if (request.getParameter("first") != null) {
				session.setAttribute("updateId", accountBean.getId());
				session.setAttribute("updateMail", accountBean.getMail());
				session.setAttribute("updateName", accountBean.getName());
				session.setAttribute("updateAge", accountBean.getAge());
				session.removeAttribute("updateErrors");
			}
		} else {
			request.setAttribute("updateSqlError", "接続に失敗しました。");	
		}
		
		// ログイン中の場合は、変更画面へ移動
		RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/update.jsp");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// フォームで入力されたID、パスワード、パスワード確認、メールアドレス、名前、年齢を代入する。
		// ID、年齢は、一旦文字列で代入し、後ほど数値に変換する。
		String strId = request.getParameter("id");
		String pass = request.getParameter("pass");
		String passConfirm = request.getParameter("passConfirm");
		String mail = request.getParameter("mail");
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");
	
		// フォームの<value="&{}">で等の情報を検索画面からなのか、
		// 変更確認画面から戻る時の処理なのかを区別する為にIDをセッションスコープに保存。
		session.setAttribute("updateId", strId);
		int id =  Integer.parseInt(strId);

		// <input name="mail or name" value="&{updateMail or updateName}">で入力した値を保持する為にセッションスコープに保存。
		if (mail == null) {
			mail = "";
		}
		session.setAttribute("updateMail", mail);
		
		if (name == null) {
			name = "";
		}
		session.setAttribute("updateName", name);
		
		List<String> updateErrors = new ArrayList<>();
		
		// age が入力されているか確認。
		int age = 0;
		if (strAge != null) {
			try {
				age =  Integer.parseInt(strAge);                             // 文字列を数値に変換
				session.setAttribute("updateAge", strAge);                   // セッションスコープに保存
			} catch (NumberFormatException e) {
				updateErrors.add("年齢が入力されていません。数字で入力して下さい。");     // 正しく入力されていない場合はエラーをセッションスコープに保存
				session.setAttribute("updateAge", "");
				session.setAttribute("updateErrors", updateErrors);
			}
		}
		
		// 入力内容のバリデーションチェック。
		// パスワード、パスワード確認、メールアドレス、名前、年齢を渡し、内容が正しく入力されているか確認する。
		AccountValidation validation = new AccountValidation();
		updateErrors = validation.registUpdate(updateErrors, pass, passConfirm, mail, name, age);	
		
		// バリデーションエラーがあった場合は、変更入力画面にリダイレクトし、以降の処理をやめる。
		if (updateErrors.size() > 0) {
			session.setAttribute("updateErrors", updateErrors);
			response.sendRedirect("/userManagement/UpdateServlet");
			return;
		}
		
		// 変更内容表示用
		AccountBean accountBean = new AccountBean(id, pass, mail, name, age);
		session.setAttribute("updateAccountBean", accountBean);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/updateConfirm.jsp");
		dispatch.forward(request, response);
	}

}
