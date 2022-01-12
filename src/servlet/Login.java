package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.LoginUser;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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

		//リクエストパラメータの取得
//		request.setCharacterEncoding("UTF-8");

		String login_id = request.getParameter("login_id");
		String pass = request.getParameter("pass");

		//LoginUserインスタンスの生成
		LoginUser login = new LoginUser(login_id,pass);

		//ログイン
		LoginLogic loginLogic = new LoginLogic();
		LoginUser loginUser = loginLogic.execute(login);

		if(loginUser!=null) {
			String loginUser_name = loginUser.getName();
			HttpSession session = request.getSession();
			session.setAttribute("loginUser_name",loginUser_name);
			response.sendRedirect("CtrlForCms?pge_id=0");
		}else {

			String forward = "/WEB-INF/jsp/loginResult.jsp";
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		}
	}

}
