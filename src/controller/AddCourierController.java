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

@WebServlet("/AddCourierController")
public class AddCourierController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CourierDAO courdao;
    
    public AddCourierController() {
        super();
        courdao = new CourierDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Courier newcour = new Courier();
		
		newcour.setCourier_id(request.getParameter("courier_id"));
		newcour.setCourier_name(request.getParameter("courier_name"));
		newcour.setCourier_phonenum(request.getParameter("courier_phonenum"));
		
		String process_status = courdao.addcourier(newcour);
		
		request.setAttribute("process_status", process_status);
		request.setAttribute("courierlist", courdao.viewcourierlist());
		RequestDispatcher view = request.getRequestDispatcher("Staff_courier.jsp");
		view.forward(request, response);
	}
}