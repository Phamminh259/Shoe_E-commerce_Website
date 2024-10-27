package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;

import entity.Account;
import entity.Email;
import entity.EmailUtils;


@WebServlet(name = "ForgotPasswordControl", urlPatterns = {"/forgotPassword"})
public class ForgotPasswordControl extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
   	 	response.setContentType("text/html;charset=UTF-8");

		try {
			String emailAddress = request.getParameter("email");
			String username = request.getParameter("username");
			
			DAO dao = new DAO();
			Account account = dao.checkAccountExistByUsernameAndEmail(username, emailAddress);
			if(account == null) {
				request.setAttribute("error", "Email hoac username sai!");
			}
			if(account != null) {
				Email email =new Email();
				email.setFrom("shopgiaypm@gmail.com");
				email.setFromPassword("aohamtvdplgwejok");
				email.setTo(emailAddress);
				email.setSubject("Forgot Password Function");
				StringBuilder sb = new StringBuilder();
				sb.append("Dear ").append(username).append("<br>");
				sb.append("Ban da quen mat khau <br> ");
				sb.append("Mat khau cua ban la: <b>").append(account.getPass()).append(" </b> <br>");
				sb.append("To Admin Shop Giày PM");
				
				email.setContent(sb.toString());
				EmailUtils.send(email);
				
				request.setAttribute("mess", "Mat khau da duoc gui den email cua ban!");
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
	}

}
