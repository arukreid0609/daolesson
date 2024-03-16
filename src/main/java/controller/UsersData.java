package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UsersDAO;
import model.Data;
import model.User;

@WebServlet("/UsersData")
public class UsersData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DAOクラスを通してデータベースのデータ取得
		UsersDAO dao = new UsersDAO();
		List<User> list = dao.findAll();
		
		// レスポンス用のインスタンスを生成、JSON文字列に変換
		Data result = new Data(list);
		String json = new Gson().toJson(result);

		// レスポンス内容(JSON形式でレスポンス)
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json);
	}
}
