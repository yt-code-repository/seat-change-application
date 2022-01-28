package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserLoginLogic;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);


		String login_id = request.getParameter("login_id");
		String pass = request.getParameter("pass");

		// Userインスタンスの生成
		User login = new User(login_id,pass);

		//ログイン
		UserLoginLogic userLoginLogic = new UserLoginLogic();
		User user = userLoginLogic.execute(login);

		if(user!=null) {
			String user_name = user.getName();
			HttpSession session = request.getSession();
			session.setAttribute("user_name",user_name);
			response.sendRedirect("SeatChangeServlet?pge_id=0");
		} else {
			String forward = "/WEB-INF/jsp/userLoginResult.jsp";
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		}
	}

}
