package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import model.Parcel;
import dao.ParcelDAO;
import dao.StudentDAO;
import dao.CourierDAO;

@WebServlet("/AddParcelController")
public class AddParcelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat formatter;
    private ParcelDAO parcdao;
    private StudentDAO studdao;
    private CourierDAO courdao;
    
    public AddParcelController() {
        super();
        parcdao = new ParcelDAO();
        studdao = new StudentDAO();
        courdao = new CourierDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Parcel newparc = new Parcel();
		
		String date = request.getParameter("parcel_date");
		Date dt;
		try {
			dt = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
			newparc.setParcel_date(sqlDate);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		newparc.setParcel_id(request.getParameter("parcel_id"));
		newparc.setParcel_status(request.getParameter("parcel_status"));
		newparc.setParcel_weight(Integer.parseInt(request.getParameter("parcel_weight")));
		newparc.setStaff_id(request.getParameter("staff_id"));
		newparc.setStud_id(request.getParameter("stud_id"));
		newparc.setCourier_id(request.getParameter("courier_id"));
		
		String process_status = parcdao.addparcel(newparc);
		
		request.setAttribute("process_status", process_status);
		request.setAttribute("studentlist", studdao.viewstudentlist());
		request.setAttribute("courierlist", courdao.viewcourierlist());
		request.setAttribute("parceljoinlist", parcdao.viewparcelcourierstudentlist(request.getParameter("staff_id")));
		RequestDispatcher view = request.getRequestDispatcher("Staff_parcel.jsp");
		view.forward(request, response);
	}
}