package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Parcel;
import dao.ParcelDAO;
import dao.StudentDAO;
import dao.CourierDAO;

@WebServlet("/UpdateParcelController")
public class UpdateParcelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ParcelDAO parcdao;
    private StudentDAO studdao;
    private CourierDAO courdao;
    HttpSession session;
    
    public UpdateParcelController() {
        super();
        parcdao = new ParcelDAO();
        studdao = new StudentDAO();
        courdao = new CourierDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Parcel updtparcel = new Parcel();
		
		String date = request.getParameter("parcel_date");
		Date dt;
		try {
			dt = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
			updtparcel.setParcel_date(sqlDate);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		updtparcel.setParcel_id(request.getParameter("parcel_id"));
		updtparcel.setParcel_status(request.getParameter("parcel_status"));
		updtparcel.setParcel_weight(Integer.parseInt(request.getParameter("parcel_weight")));
		updtparcel.setStaff_id(request.getParameter("staff_id"));
		updtparcel.setStud_id(request.getParameter("stud_id"));
		updtparcel.setCourier_id(request.getParameter("courier_id"));
		
		parcdao.updateparcel(updtparcel);
		
		session = request.getSession(true);
		String java_session_value = (String)session.getAttribute("session_id");
		
		request.setAttribute("process_status", "success");
		request.setAttribute("studentlist", studdao.viewstudentlist());
		request.setAttribute("courierlist", courdao.viewcourierlist());
		request.setAttribute("parceljoinlist", parcdao.viewparcelcourierstudentlist(java_session_value));
		RequestDispatcher view = request.getRequestDispatcher("Staff_parcel.jsp");
		view.forward(request, response);
	}
}