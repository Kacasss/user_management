package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.AccountBean;
import beans.AdminLoginBean;

public class AccountDAO extends DbAccess {
	
	/**
	 * ログイン情報を取得する為のメソッド
	 * @param String inputPass
	 * @param String inputMail
	 * @return AdminLoginBean adminLoginBean
	 */
	public AdminLoginBean findAdminLogin(String inputPass, String inputMail) {
		AdminLoginBean adminLoginBean = null;

		try {
			connect();
			String sql = "SELECT * FROM admin_login WHERE pass = ? AND mail = ?";   // 1件分のレコード
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, inputPass);
			ps.setString(2, inputMail);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String pass = rs.getString("pass");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				
				adminLoginBean = new AdminLoginBean(id, pass, mail, name);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			disconnect();
		}
		return adminLoginBean;
	}
	
	/**
	 * 名前で検索し、該当のレコードを取得する為のメソッド
	 * @param String inputName
	 * @return List<AccountBean> accountList
	 */
	public List<AccountBean> findName(String inputName) {
		List<AccountBean> accountList = new ArrayList<>();

		try {
			connect();
			String sql = "SELECT * FROM account WHERE name LIKE '%" + inputName + "%'";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			// 残念ながら　? がうまくいかなかった為コメントアウト   ps.setString(1, inputName);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String pass = rs.getString("pass");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				
				AccountBean accountBean = new AccountBean(id, pass, mail, name, age);
				accountList.add(accountBean);
			}
			
			//リストが空の場合、nullを返却する
			if(accountList.isEmpty()){
			    return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			disconnect();
		}
		return accountList;
	}
	
	/**
	 * IDで検索し、該当のレコードを取得する為のメソッド
	 * @param int inputId
	 * @return AccountBean accountBean
	 */
	public AccountBean findId(int inputId) {
		AccountBean accountBean = null;

		try {
			connect();
			String sql = "SELECT * FROM account WHERE id = ?";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, inputId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String pass = rs.getString("pass");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				accountBean = new AccountBean(id, pass, mail, name, age);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			disconnect();
		}
		return accountBean;
	}
	
	/**
	 * 5件分のレコードを取得するメソッド
	 * @param int pageStart    // OFFSETに使用
	 * @return List<AccountBean> limitAccountList
	 */
	public List<AccountBean> limitList(int pageStart) {
		List<AccountBean> limitAccountList = new ArrayList<>();

		try {
			connect();
			String sql = "SELECT * FROM account LIMIT ? OFFSET ?";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, 5);           // LIMIT
			ps.setInt(2, pageStart);   // OFFSET
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String pass = rs.getString("pass");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				
				AccountBean accountBean = new AccountBean(id, pass, mail, name, age);
				limitAccountList.add(accountBean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			disconnect();
		}
		return limitAccountList;
	}
	
	/**
	 * 全件分のレコードを取得するメソッド
	 * @return List<AccountBean> accountList
	 */
	public List<AccountBean> list() {
		List<AccountBean> accountList = new ArrayList<>();

		try {
			connect();
			String sql = "SELECT * FROM account";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String pass = rs.getString("pass");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				
				AccountBean accountBean = new AccountBean(id, pass, mail, name, age);
				accountList.add(accountBean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			disconnect();
		}
		return accountList;
	}
	
	/**
	 * 1件分のレコードを取得するメソッド
	 * @param AccountBean accountBean
	 * @return boolean
	 */
	public boolean regist(AccountBean accountBean) {

		try {
			connect();
			String sql = "INSERT INTO account (pass, mail, name, age) VALUES (?, ?, ?, ?)";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, accountBean.getPass());
			ps.setString(2, accountBean.getMail());
			ps.setString(3, accountBean.getName());
			ps.setInt(4, accountBean.getAge());
			
			int rs = ps.executeUpdate();
			
			if (rs != 1) {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	

	public boolean update(AccountBean accountBean) {

		try {
			connect();
			String sql = "UPDATE account SET pass = ?, mail = ?, name = ?, age = ? WHERE id = ?";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, accountBean.getPass());
			ps.setString(2, accountBean.getMail());
			ps.setString(3, accountBean.getName());
			ps.setInt(4, accountBean.getAge());
			ps.setInt(5, accountBean.getId());
			
			int rs = ps.executeUpdate();
			
			if (rs != 1) {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	
	public boolean delete(AccountBean accountBean) {

		try {
			connect();
			String sql = "DELETE FROM account WHERE id = ?";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);

			ps.setInt(1, accountBean.getId());
			
			int rs = ps.executeUpdate();
			
			if (rs != 1) {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	
}
