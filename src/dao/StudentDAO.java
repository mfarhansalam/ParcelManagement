package dao;

import java.sql.*;
import java.util.*;
import java.security.*;

import database.DatabaseConnection;
import model.Student;

public class StudentDAO {
	static Connection connect = null;
	static PreparedStatement ps = null;
	
	String stud_id, stud_password, stud_name, stud_email, stud_phonenum;
	
	
	// Register student DAO
	public String registerstudent(Student newstud) {
		String status = null;
		stud_id = newstud.getStud_id();
		stud_password = newstud.getStud_password();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM student WHERE stud_id = ?");
			ps.setString(1, stud_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				status = "fail";
			}
			else {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(stud_password.getBytes());

				byte byteData[] = md.digest();

				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
				
				ps = connect.prepareStatement("INSERT INTO student (stud_id, stud_password) VALUES (?, ?)");
				ps.setString(1, stud_id);
				ps.setString(2, sb.toString());
				
				ps.execute();
				status = "success";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	// Login student DAO
	public String loginstudent(Student newstud) {
		String login_status = null;
		stud_id = newstud.getStud_id();
		stud_password = newstud.getStud_password();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM student WHERE stud_id = ?");
			ps.setString(1, stud_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(stud_password.getBytes());

				byte byteData[] = md.digest();

				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
				
				ps = connect.prepareStatement("SELECT * FROM student WHERE stud_id = ? AND stud_password = ?");
				ps.setString(1, stud_id);
				ps.setString(2, sb.toString());
				
				ResultSet secrs = ps.executeQuery();
				
				boolean status = secrs.next();
				
				if(status) {
					login_status = "success";
				}
				else if(!status) {
					login_status = "Wrong password";
				}
			}
			else {
				login_status = "ID number not exist";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return login_status;
	}
	
	// View specific student by id DAO 
	public static Student viewstudentbyid(String view_studentid) {
		Student viewstudent = new Student();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM student WHERE stud_id = ?");
			ps.setString(1, view_studentid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				viewstudent.setStud_id(rs.getString("stud_id"));
				viewstudent.setStud_name(rs.getString("stud_name"));
				viewstudent.setStud_email(rs.getString("stud_email"));
				viewstudent.setStud_phonenum(rs.getString("stud_phonenum"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return viewstudent;
	}
	
	// View student list DAO
	public static List<Student> viewstudentlist() {
		List<Student> stud_list = new ArrayList<Student>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM student");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Student stud_info = new Student();
				stud_info.setStud_id(rs.getString("stud_id"));
				stud_info.setStud_name(rs.getString("stud_name"));
				stud_info.setStud_email(rs.getString("stud_email"));
				stud_info.setStud_phonenum(rs.getString("stud_phonenum"));
				
				stud_list.add(stud_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return stud_list;
	}
	
	// Update specific student by id DAO 
	public void updatestudent(Student updtstudent) {
		stud_id = updtstudent.getStud_id();
		stud_name = updtstudent.getStud_name();
		stud_email = updtstudent.getStud_email();
		stud_phonenum = updtstudent.getStud_phonenum();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("UPDATE student SET stud_name = ?, stud_email = ?, stud_phonenum = ? WHERE stud_id = ?");
			ps.setString(1, stud_name);
			ps.setString(2, stud_email);
			ps.setString(3, stud_phonenum);
			ps.setString(4, stud_id);
			
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Delete specific student by id DAO 
	public String deletestudent(String delete_studid) {
		String delete_status = "";
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("DELETE FROM student WHERE stud_id = ?");
			ps.setString(1, delete_studid);
			
			ps.execute();
			delete_status = "success";
		}
		catch(Exception e) {
			e.printStackTrace();
			delete_status = "Error. Make sure there is no parcel connected to this student";
		}
		return delete_status;
	}
}