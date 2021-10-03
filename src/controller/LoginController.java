package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Student;
import model.Staff;
import dao.StudentDAO;
import dao.StaffDAO;
import dao.ParcelDAO;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private StudentDAO studdao;
    private StaffDAO stafdao;
    private ParcelDAO parcdao;
    HttpSession session;
    
    public LoginController() {
        super();
        studdao = new StudentDAO();
        stafdao = new StaffDAO();
        parcdao = new ParcelDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_type = request.getParameter("user_type");
		String user_id = request.getParameter("user_id");
		String user_password = request.getParameter("user_password");
		
		if(user_type.equalsIgnoreCase("Student")) {
			Student newstud = new Student();
			
			newstud.setStud_id(user_id);
			newstud.setStud_password(user_password);
			
			String status = studdao.loginstudent(newstud);
			if(status.equalsIgnoreCase("success")) {
				
				session = request.getSession(true);
				session.setAttribute("session_id", user_id);
				
				String java_session_value = (String)session.getAttribute("session_id");
				
				Student studinfo = studdao.viewstudentbyid(java_session_value);
				session.setAttribute("noti_status", parcdao.alertbystatus(java_session_value));
				request.setAttribute("student", studinfo);
				
				RequestDispatcher view = request.getRequestDispatcher("Student_info.jsp");
				view.forward(request, response);
				session.setAttribute("noti_status", false);
			}
			else {
				request.setAttribute("status", status);
				RequestDispatcher view = request.getRequestDispatcher("Student_login.jsp");
				view.forward(request, response);
			}
		}
		else if(user_type.equalsIgnoreCase("Staff")) {
			Staff newstaf = new Staff();
			
			newstaf.setStaff_id(user_id);
			newstaf.setStaff_password(user_password);
			
			String status = stafdao.loginstaff(newstaf);
			if(status.equalsIgnoreCase("success")) {
				
				session = request.getSession(true);
				session.setAttribute("session_id", user_id);
				
				String java_session_value = (String)session.getAttribute("session_id");
				
				Staff staffinfo = stafdao.viewstaffbyid(java_session_value);
				request.setAttribute("staff", staffinfo);
				
				RequestDispatcher view = request.getRequestDispatcher("Staff_info.jsp");
				view.forward(request, response);
			}
			else {
				request.setAttribute("status", status);
				RequestDispatcher view = request.getRequestDispatcher("Staff_login.jsp");
				view.forward(request, response);
			}
		}
	}
}