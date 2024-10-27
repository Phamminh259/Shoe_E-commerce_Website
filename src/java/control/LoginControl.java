
package control;

import dao.DAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "LoginControl", urlPatterns = {"/login"})
public class LoginControl extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	Cookie arr[] = request.getCookies();
    	if(arr != null) {
    		for(Cookie o : arr) {
        		if(o.getName().equals("userC")) {
        			request.setAttribute("username", o.getValue());
        		}
        		if(o.getName().equals("passC")) {
        			request.setAttribute("password", o.getValue());
        		}
        	}
    	}
    	
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	 response.setContentType("text/html;charset=UTF-8");
         String username = request.getParameter("user");
         String password = request.getParameter("pass");
         String remember =request.getParameter("remember");
         
         DAO dao = new DAO();
         Account a = dao.login(username, password);
         if (a == null) {
             request.setAttribute("error", "Sai username hoac password!");
             request.getRequestDispatcher("Login.jsp").forward(request, response);
         } else {
             HttpSession session = request.getSession();
             session.setAttribute("acc", a);
             session.setMaxInactiveInterval(60*60*24);
           
             Cookie u = new Cookie("userC", username);
             Cookie p = new Cookie("passC", password);
             if(remember != null) {
            	 p.setMaxAge(60*60*24);
             }else {
            	 p.setMaxAge(0);
             }
             
             u.setMaxAge(60*60*24*365);

             response.addCookie(u);
             response.addCookie(p);
             
             response.sendRedirect("home");
         }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
