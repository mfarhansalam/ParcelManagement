package dao;

import java.sql.*;
import java.util.*;

import database.DatabaseConnection;
import model.Courier;

public class CourierDAO {
	static Connection connect = null;
	static PreparedStatement ps = null;
	
	String courier_id, courier_name, courier_phonenum;
	
	// Add courier DAO
	public String addcourier(Courier newcour) {
		String add_status = "";
		courier_id = newcour.getCourier_id();
		courier_name = newcour.getCourier_name();
		courier_phonenum = newcour.getCourier_phonenum();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM courier WHERE courier_id = ?");
			ps.setString(1, courier_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.print("already exist");
				add_status = "ID number already exist";
			}
			else {
				connect = DatabaseConnection.getConnection();
				ps = connect.prepareStatement("INSERT INTO courier (courier_id, courier_name, courier_phonenum) VALUES (?, ?, ?)");
				ps.setString(1, courier_id);
				ps.setString(2, courier_name);
				ps.setString(3, courier_phonenum);
				
				ps.execute();
				add_status = "success";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return add_status;
	}
	
	// View courier list DAO
	public static List<Courier> viewcourierlist() {
		List<Courier> cour_list = new ArrayList<Courier>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM courier");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Courier cour_info = new Courier();
				cour_info.setCourier_id(rs.getString("courier_id"));
				cour_info.setCourier_name(rs.getString("courier_name"));
				cour_info.setCourier_phonenum(rs.getString("courier_phonenum"));
				
				cour_list.add(cour_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cour_list;
	}
	
	// View specific courier by id DAO
	public static Courier viewcourierbyid(String view_courierid) {
		Courier viewcourier = new Courier();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM courier WHERE courier_id = ?");
			ps.setString(1, view_courierid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				viewcourier.setCourier_id(rs.getString("courier_id"));
				viewcourier.setCourier_name(rs.getString("courier_name"));
				viewcourier.setCourier_phonenum(rs.getString("courier_phonenum"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return viewcourier;
	}
	
	// Update specific courier by id DAO
	public void updatecourier(Courier updtcourier) {
		courier_id = updtcourier.getCourier_id();
		courier_name = updtcourier.getCourier_name();
		courier_phonenum = updtcourier.getCourier_phonenum();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("UPDATE courier SET courier_name = ?, courier_phonenum = ? WHERE courier_id = ?");
			ps.setString(1, courier_name);
			ps.setString(2, courier_phonenum);
			ps.setString(3, courier_id);
			
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Delete specific courier by id DAO
	public String deletecourier(String delete_courierid) {
		String delete_status = "";
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("DELETE FROM courier WHERE courier_id = ?");
			ps.setString(1, delete_courierid);
			
			ps.execute();
			delete_status = "success";
		}
		catch(Exception e) {
			e.printStackTrace();
			delete_status = "Error. Make sure there is no parcel connected to this courier";
		}
		return delete_status;
	}
}