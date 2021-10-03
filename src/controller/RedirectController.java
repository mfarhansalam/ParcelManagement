package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Student;
import model.Staff;
import dao.StudentDAO;
import dao.CourierDAO;
import dao.ParcelDAO;
import dao.StaffDAO;

@WebServlet("/RedirectController")
public class RedirectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studdao;
	private CourierDAO courdao;
	private ParcelDAO parcdao;
	private StaffDAO stafdao;
    HttpSession session;
	
    public RedirectController() {
        super();
        studdao = new StudentDAO();
        courdao = new CourierDAO();
        parcdao = new ParcelDAO();
        stafdao = new StaffDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		session = request.getSession(true);
		String java_session_value = (String)session.getAttribute("session_id");
		
		RequestDispatcher view;
		String process_status = "";
		try {
			switch(action) {
				case "studentinfo":
					Student studentinfo = studdao.viewstudentbyid(java_session_value);
					request.setAttribute("student", studentinfo);
					view = request.getRequestDispatcher("Student_info.jsp");
					view.forward(request, response);
					break;
				case "studentparcel":
					request.setAttribute("parceljoinlist", parcdao.viewparcelcourierstafflist(java_session_value));
					view = request.getRequestDispatcher("Student_parcel.jsp");
					view.forward(request, response);
					break;
				case "staffinfo":
					Staff staffinfo = stafdao.viewstaffbyid(java_session_value);
					request.setAttribute("staff", staffinfo);
					view = request.getRequestDispatcher("Staff_info.jsp");
					view.forward(request, response);
					break;
				case "staffcourier":
					request.setAttribute("process_status", "success");
					request.setAttribute("courierlist", courdao.viewcourierlist());
					view = request.getRequestDispatcher("Staff_courier.jsp");
					view.forward(request, response);
					break;
				case "staffstudent":
					request.setAttribute("process_status", "success");
					request.setAttribute("studentlist", studdao.viewstudentlist());
					view = request.getRequestDispatcher("Staff_student.jsp");
					view.forward(request, response);
					break;
				case "staffparcel":
					request.setAttribute("process_status", "success");
					request.setAttribute("studentlist", studdao.viewstudentlist());
					request.setAttribute("courierlist", courdao.viewcourierlist());
					request.setAttribute("parceljoinlist", parcdao.viewparcelcourierstudentlist(java_session_value));
					view = request.getRequestDispatcher("Staff_parcel.jsp");
					view.forward(request, response);
					break;
				case "staffreport":
					request.setAttribute("parcelstatuslist", parcdao.viewparcelbystatus());
					request.setAttribute("parcelcourierlist", parcdao.viewparcelbycourier());
					request.setAttribute("parcelmonthlist", parcdao.viewparcelbymonth());
					request.setAttribute("parcellist", parcdao.viewparceljoinall());
					view = request.getRequestDispatcher("Staff_report.jsp");
					view.forward(request, response);
					break;
				case "deleteparcel":
					String delete_parcelid = request.getParameter("deleteid");
					parcdao.deleteparcel(delete_parcelid);
					request.setAttribute("process_status", "success");
					request.setAttribute("studentlist", studdao.viewstudentlist());
					request.setAttribute("courierlist", courdao.viewcourierlist());
					request.setAttribute("parceljoinlist", parcdao.viewparcelcourierstudentlist(java_session_value));
					view = request.getRequestDispatcher("Staff_parcel.jsp");
					view.forward(request, response);
					break;
				case "updateparcel":
					String update_parcelid = request.getParameter("updateid");
					request.setAttribute("studentlist", studdao.viewstudentlist());
					request.setAttribute("courierlist", courdao.viewcourierlist());
					request.setAttribute("parcelinfo", parcdao.viewparcelbyid(update_parcelid));
					view = request.getRequestDispatcher("Staff_update_parcel.jsp");
					view.forward(request, response);
					break;
				case "deletecourier":
					String delete_courierid = request.getParameter("deleteid");
					process_status = courdao.deletecourier(delete_courierid);
					request.setAttribute("process_status", process_status);
					request.setAttribute("courierlist", courdao.viewcourierlist());
					view = request.getRequestDispatcher("Staff_courier.jsp");
					view.forward(request, response);
					break;
				case "updatecourier":
					String update_courier_id = request.getParameter("updateid");
					request.setAttribute("courierinfo", courdao.viewcourierbyid(update_courier_id));
					view = request.getRequestDispatcher("Staff_update_courier.jsp");
					view.forward(request, response);
					break;
				case "deletestudent":
					String delete_studentid = request.getParameter("deleteid");
					process_status = studdao.deletestudent(delete_studentid);
					request.setAttribute("process_status", process_status);
					request.setAttribute("studentlist", studdao.viewstudentlist());
					view = request.getRequestDispatcher("Staff_student.jsp");
					view.forward(request, response);
					break;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}