package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Staff;
import dao.StaffDAO;

@WebServlet("/UpdateStaffController")
public class UpdateStaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StaffDAO stafdao;
	
    public UpdateStaffController() {
        super();
        stafdao = new StaffDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Staff updtstaff = new Staff();
		
		updtstaff.setStaff_id(request.getParameter("staff_id"));
		updtstaff.setStaff_name(request.getParameter("staff_name"));
		updtstaff.setStaff_email(request.getParameter("staff_email"));
		updtstaff.setStaff_phonenum(request.getParameter("staff_phonenum"));
		
		stafdao.updatestaff(updtstaff);
		
		Staff staffinfo = stafdao.viewstaffbyid(request.getParameter("staff_id"));
		RequestDispatcher view = request.getRequestDispatcher("Staff_info.jsp");
		
		request.setAttribute("staff", staffinfo);
		view.forward(request, response);
	}
}