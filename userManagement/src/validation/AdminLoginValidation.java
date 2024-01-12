package validation;

import java.util.ArrayList;
import java.util.List;

public class AdminLoginValidation {
	
	/**
	 * ログインの入力内容をバリデーション処理するメソッド。
	 * @param String pass
	 * @param String mail
	 * @return List<String> errors
	 */
	public List<String> adminLogin(String pass, String mail) {
		List<String> errors = new ArrayList<>();
		
		if (pass == null || pass.length() <= 0) {
			errors.add("パスワードが入力されていません。");
		} else {
			if (pass.length() < 5) {
				errors.add("パスワードは5文字以上で入力して下さい。");
			}
		}

		if (mail == null || mail.length() <= 0) {
			errors.add("メールアドレスが入力されていません。");
		} 
		return errors;
	}

}
