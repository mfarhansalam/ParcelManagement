package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import dao.StudentDAO;

@WebServlet("/UpdateStudentController")
public class UpdateStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studdao;
	
    public UpdateStudentController() {
        super();
        studdao = new StudentDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student updtstudent = new Student();
		
		updtstudent.setStud_id(request.getParameter("student_id"));
		updtstudent.setStud_name(request.getParameter("student_name"));
		updtstudent.setStud_email(request.getParameter("student_email"));
		updtstudent.setStud_phonenum(request.getParameter("student_phonenum"));
		
		studdao.updatestudent(updtstudent);
		
		Student studentinfo = studdao.viewstudentbyid(request.getParameter("student_id"));
		RequestDispatcher view = request.getRequestDispatcher("Student_info.jsp");
		
		request.setAttribute("student", studentinfo);
		view.forward(request, response);
	}
}