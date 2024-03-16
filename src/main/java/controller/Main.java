package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDAO;
import model.User;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DAOクラスを通してデータベースからデータ取得
		UsersDAO dao = new UsersDAO();
		List<User> list = dao.findAll();
		
		// スコープにListを保存
		request.setAttribute("list", list);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/main.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// パラメータ取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));

		// インスタンス作成
		User user = new User(name,age);

		// DAOクラスでデータ追加
		UsersDAO dao = new UsersDAO();
		dao.InsertOne(user);

		doGet(request, response);
	}

}
