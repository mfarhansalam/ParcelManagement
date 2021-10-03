package dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import database.DatabaseConnection;
import model.Parcel;

public class ParcelDAO {
	static Connection connect = null;
	static PreparedStatement ps = null;

	String parcel_id, parcel_status, stud_id, staff_id, courier_id;
	int parcel_weight;
	Date parcel_date;
	
	// Add parcel DAO
	public String addparcel(Parcel newparc) {
		String add_status = "";
		parcel_id = newparc.getParcel_id();
		parcel_status = newparc.getParcel_status();
		parcel_weight = newparc.getParcel_weight();
		parcel_date = newparc.getParcel_date();
		stud_id = newparc.getStud_id();
		staff_id = newparc.getStaff_id();
		courier_id = newparc.getCourier_id();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM parcel WHERE parcel_id = ?");
			ps.setString(1, parcel_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.print("already exist");
				add_status = "ID number already exist";
			}
			else {
				ps = connect.prepareStatement("INSERT INTO parcel (parcel_id, parcel_status, parcel_weight, parcel_date, stud_id, staff_id, courier_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, parcel_id);
				ps.setString(2, parcel_status);
				ps.setInt(3, parcel_weight);
				ps.setDate(4, parcel_date);
				ps.setString(5, stud_id);
				ps.setString(6, staff_id);
				ps.setString(7, courier_id);
				
				ps.execute();
				add_status = "success";
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return add_status;
	}
	
	// View parcel (join student, staff, courier) list DAO
	public static List<Parcel> viewparceljoinall() {
		List<Parcel> list_parcel = new ArrayList<Parcel>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM parcel P JOIN student STU ON P.stud_id = STU.stud_id JOIN courier C ON P.courier_id = C.courier_id JOIN staff STA ON p.staff_id = STA.staff_id");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Parcel parc = new Parcel();
				parc.setParcel_id(rs.getString("parcel_id"));
				parc.setParcel_status(rs.getString("parcel_status"));
				parc.setParcel_weight(rs.getInt("parcel_weight"));
				parc.setParcel_date(rs.getDate("parcel_date"));
				parc.setStud_id(rs.getString("stud_id"));
				parc.setStaff_id(rs.getString("staff_id"));
				parc.setCourier_id(rs.getString("courier_id"));
				parc.setStudent(StudentDAO.viewstudentbyid(rs.getString("stud_id")));
				parc.setCourier(CourierDAO.viewcourierbyid(rs.getString("courier_id")));
				parc.setStaff(StaffDAO.viewstaffbyid(rs.getString("staff_id")));
				
				list_parcel.add(parc);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list_parcel;
	}
	
	// View parcel (join student, courier) list DAO
	public static List<Parcel> viewparcelcourierstudentlist(String view_by_staff_id) {
		List<Parcel> list_parcel = new ArrayList<Parcel>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM parcel P JOIN student S ON P.stud_id = S.stud_id JOIN courier C ON P.courier_id = C.courier_id WHERE p.staff_id = ?");
			ps.setString(1, view_by_staff_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Parcel parc = new Parcel();
				parc.setParcel_id(rs.getString("parcel_id"));
				parc.setParcel_status(rs.getString("parcel_status"));
				parc.setParcel_weight(rs.getInt("parcel_weight"));
				parc.setParcel_date(rs.getDate("parcel_date"));
				parc.setStud_id(rs.getString("stud_id"));
				parc.setStaff_id(rs.getString("staff_id"));
				parc.setCourier_id(rs.getString("courier_id"));
				parc.setStudent(StudentDAO.viewstudentbyid(rs.getString("stud_id")));
				parc.setCourier(CourierDAO.viewcourierbyid(rs.getString("courier_id")));
				
				list_parcel.add(parc);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list_parcel;
	}
	
	// View parcel (join staff, courier) list DAO
	public static List<Parcel> viewparcelcourierstafflist(String view_by_student_id) {
		List<Parcel> list_parcel = new ArrayList<Parcel>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM parcel P JOIN staff S ON P.staff_id = S.staff_id JOIN courier C ON P.courier_id = C.courier_id WHERE p.stud_id = ?");
			ps.setString(1, view_by_student_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Parcel parc = new Parcel();
				parc.setParcel_id(rs.getString("parcel_id"));
				parc.setParcel_status(rs.getString("parcel_status"));
				parc.setParcel_weight(rs.getInt("parcel_weight"));
				parc.setParcel_date(rs.getDate("parcel_date"));
				parc.setStud_id(rs.getString("stud_id"));
				parc.setStaff_id(rs.getString("staff_id"));
				parc.setCourier_id(rs.getString("courier_id"));
				parc.setStaff(StaffDAO.viewstaffbyid(rs.getString("staff_id")));
				parc.setCourier(CourierDAO.viewcourierbyid(rs.getString("courier_id")));
				
				list_parcel.add(parc);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list_parcel;
	}
	
	// View specific parcel by id DAO
	public static Parcel viewparcelbyid(String view_parcelid) {
		Parcel viewparc = new Parcel();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM parcel WHERE parcel_id = ?");
			ps.setString(1, view_parcelid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				viewparc.setParcel_id(rs.getString("parcel_id"));
				viewparc.setParcel_status(rs.getString("parcel_status"));
				viewparc.setParcel_weight(rs.getInt("parcel_weight"));
				viewparc.setParcel_date(rs.getDate("parcel_date"));
				viewparc.setStud_id(rs.getString("stud_id"));
				viewparc.setStaff_id(rs.getString("staff_id"));
				viewparc.setCourier_id(rs.getString("courier_id"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return viewparc;
	}
	
	// Update specific parcel by id DAO
	public void updateparcel(Parcel updtparcel) {
		parcel_id = updtparcel.getParcel_id();
		parcel_status = updtparcel.getParcel_status();
		parcel_weight = updtparcel.getParcel_weight();
		parcel_date = updtparcel.getParcel_date();
		stud_id = updtparcel.getStud_id();
		staff_id = updtparcel.getStaff_id();
		courier_id = updtparcel.getCourier_id();
		
		try {
			ps = connect.prepareStatement("UPDATE parcel SET parcel_status = ?, parcel_weight = ?, parcel_date = ?, stud_id = ?, staff_id = ?, courier_id = ? WHERE parcel_id = ?");
			ps.setString(1, parcel_status);
			ps.setInt(2, parcel_weight);
			ps.setDate(3, parcel_date);
			ps.setString(4, stud_id);
			ps.setString(5, staff_id);
			ps.setString(6, courier_id);
			ps.setString(7, parcel_id);
			
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Delete specific parcel by id DAO
	public void deleteparcel(String delete_parcelid) {
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("DELETE FROM parcel WHERE parcel_id = ?");
			ps.setString(1, delete_parcelid);
			
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// View & count specific parcel by courier DAO
	public static List<Parcel> viewparcelbycourier() {
		List<Parcel> parc_courier_list = new ArrayList<Parcel>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT P.courier_id, COUNT(P.courier_id) AS total FROM parcel P JOIN courier C ON P.courier_id = C.courier_id GROUP BY P.courier_id");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Parcel parc_courier_current = new Parcel();
				parc_courier_current.setCourier(CourierDAO.viewcourierbyid(rs.getString("courier_id")));
				parc_courier_current.setTotal_by_courier(rs.getInt("total"));
				
				parc_courier_list.add(parc_courier_current);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return parc_courier_list;
	}
	
	// View & count specific parcel by status DAO
	public static List<Parcel> viewparcelbystatus() {
		List<Parcel> parc_status_list = new ArrayList<Parcel>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT parcel_status, COUNT(parcel_status) AS total FROM parcel GROUP BY parcel_status");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Parcel parc_status_current = new Parcel();
				parc_status_current.setParcel_status(rs.getString("parcel_status"));
				parc_status_current.setTotal_by_status(rs.getInt("total"));
				
				parc_status_list.add(parc_status_current);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return parc_status_list;
	}
	
	// View & count specific parcel by month DAO
	public static List<Parcel> viewparcelbymonth() {
		List<Parcel> parc_month_list = new ArrayList<Parcel>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT MONTHNAME(parcel_date) AS month_name, COUNT(MONTHNAME(parcel_date)) AS total FROM parcel WHERE YEAR(parcel_date) = YEAR(CURRENT_DATE) GROUP BY MONTHNAME(parcel_date) ORDER BY MONTH(parcel_date)");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Parcel parc_month_current = new Parcel();
				parc_month_current.setParcel_month(rs.getString("month_name"));
				parc_month_current.setTotal_by_month(rs.getInt("total"));
				
				parc_month_list.add(parc_month_current);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return parc_month_list;
	}
	
	// Count specific parcel by status & student id DAO
	public static boolean alertbystatus(String stud_id) {
		boolean noti_status = false;
		int total_not_received = 0;
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT COUNT(parcel_id) AS total FROM parcel WHERE parcel_status = 'Not Received' AND stud_id = ?");
			ps.setString(1, stud_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				total_not_received = total_not_received + rs.getInt("total");
			}
			
			if(total_not_received > 0) {
				noti_status = true;
			}
			else {
				noti_status = false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return noti_status;
	}
}