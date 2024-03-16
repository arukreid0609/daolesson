package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.User;


public class UsersDAO {
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	
	// 接続処理
	private void connect() throws NamingException, SQLException {
		Context context = new InitialContext();
		DataSource ds = (DataSource)context.lookup("java:comp/env/mariadb");
		this.con = ds.getConnection();
	}
	
	// 切断処理
	private void disconnect() {
		try {
			if(rs != null)rs.close();
			if(stmt != null)stmt.close();
			if(con != null)con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 接続テスト
	public void connectCheck() {
		try {
			connect();
			System.out.println("接続OK");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	// 全件取得
	public List<User> findAll(){
		List<User> list = new ArrayList<>();
		try {
			connect();
			
			// SQL文の準備,実行
			this.stmt = this.con.prepareStatement("SELECT * FROM users");
			this.rs = this.stmt.executeQuery();

			while(rs.next()) {
				// 各カラムのデータ取得
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				
				// データからインスタンス生成、Listに追加
				User user = new User(id,name,age);
				list.add(user);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}
	
	// １件追加
	public void InsertOne(User user) {
		try {
			connect();
			
			// SQL文準備、実行
			this.stmt = this.con.prepareStatement("INSERT INTO users(name,age) VALUES(?,?)");
			this.stmt.setString(1, user.getName());
			this.stmt.setInt(2, user.getAge());
			int result = this.stmt.executeUpdate();
			
			if(result != 1) {
				System.out.println("追加に失敗しました");
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
}
