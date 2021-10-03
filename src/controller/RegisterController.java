package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import model.Staff;
import dao.StudentDAO;
import dao.StaffDAO;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private StudentDAO studdao;
    private StaffDAO stafdao;
    
    public RegisterController() {
        super();
        studdao = new StudentDAO();
        stafdao = new StaffDAO();
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
			
			String status = studdao.registerstudent(newstud);
			
			if(status.equalsIgnoreCase("fail")) {
				request.setAttribute("status", "ID number already exist");
				RequestDispatcher view = request.getRequestDispatcher("Register.jsp");
				view.forward(request, response);
			}
			else if(status.equalsIgnoreCase("success")) {
				RequestDispatcher view = request.getRequestDispatcher("Student_login.jsp");
				view.forward(request, response);
			}
		}
		else if(user_type.equalsIgnoreCase("Staff")) {
			Staff newstaf = new Staff();
			
			newstaf.setStaff_id(user_id);
			newstaf.setStaff_password(user_password);
			
			String status = stafdao.registerstaff(newstaf);
			
			if(status.equalsIgnoreCase("fail")) {
				request.setAttribute("status", "ID number already exist");
				RequestDispatcher view = request.getRequestDispatcher("Register.jsp");
				view.forward(request, response);
			}
			else if(status.equalsIgnoreCase("success")) {
				RequestDispatcher view = request.getRequestDispatcher("Staff_login.jsp");
				view.forward(request, response);
			}
		}
	}
}