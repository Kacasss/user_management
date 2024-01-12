package validation;

import java.util.List;

public class AccountValidation {
	
	/**
	 * 社員登録と編集の入力内容をバリデーション処理するメソッド。
	 * @param String pass
	 * @param String passConfirm
	 * @param String mail
	 * @param String name
	 * @param int age
	 * @return List<String> errors
	 */
	public List<String> registUpdate(List<String> errors, String pass, String passConfirm, String mail, String name, int age) {
		
		if (pass == null || pass.length() <= 0) {
			errors.add("パスワードが入力されていません。");
		} else if (pass.length() < 5) {
				errors.add("パスワードは5文字以上で入力して下さい。");
		} 
		
		if (!pass.equals(passConfirm)) {
			errors.add("パスワードとパスワード確認で入力している文字が相違しています。");
		}

		if (mail == null || mail.length() <= 0) {
			errors.add("メールアドレスが入力されていません。");
		} 
		
		if (name == null || name.length() <= 0) {
			errors.add("名前が入力されていません。");
		}
		
		if (age < 0 || 120 < age) {
			errors.add("年齢は0～120歳の間を入力して下さい。");
		}
		return errors;
	}

}
