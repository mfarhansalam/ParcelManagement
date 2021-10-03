package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Courier;
import dao.CourierDAO;

@WebServlet("/UpdateCourierController")
public class UpdateCourierController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourierDAO courdao;
	
    public UpdateCourierController() {
        super();
        courdao = new CourierDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Courier updtcourier = new Courier();
		
		updtcourier.setCourier_id(request.getParameter("courier_id"));
		updtcourier.setCourier_name(request.getParameter("courier_name"));
		updtcourier.setCourier_phonenum(request.getParameter("courier_phonenum"));
		
		courdao.updatecourier(updtcourier);
		
		request.setAttribute("process_status", "success");
		request.setAttribute("courierlist", courdao.viewcourierlist());
		RequestDispatcher view = request.getRequestDispatcher("Staff_courier.jsp");
		view.forward(request, response);
	}
}