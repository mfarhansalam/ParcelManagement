package dao;

import java.sql.*;
import java.security.*;

import database.DatabaseConnection;
import model.Staff;

public class StaffDAO {
	static Connection connect = null;
	static PreparedStatement ps = null;
	
	String staff_id, staff_password, staff_name, staff_email, staff_phonenum;
	
	
	// Register staff DAO
	public String registerstaff(Staff newstaf) {
		String status = null;
		staff_id = newstaf.getStaff_id();
		staff_password = newstaf.getStaff_password();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM staff WHERE staff_id = ?");
			ps.setString(1, staff_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				status = "fail";
			}
			else {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(staff_password.getBytes());

				byte byteData[] = md.digest();

				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
				
				ps = connect.prepareStatement("INSERT INTO staff (staff_id, staff_password) VALUES (?, ?)");
				ps.setString(1, staff_id);
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
	
	// Login staff DAO
	public String loginstaff(Staff newstaf) {
		String login_status = null;
		staff_id = newstaf.getStaff_id();
		staff_password = newstaf.getStaff_password();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM staff WHERE staff_id = ?");
			ps.setString(1, staff_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(staff_password.getBytes());

				byte byteData[] = md.digest();

				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
				
				ps = connect.prepareStatement("SELECT * FROM staff WHERE staff_id = ? AND staff_password = ?");
				ps.setString(1, staff_id);
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
	
	// View specific staff by id DAO 
	public static Staff viewstaffbyid(String view_staffid) {
		Staff viewstaff = new Staff();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM staff WHERE staff_id = ?");
			ps.setString(1, view_staffid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				viewstaff.setStaff_id(rs.getString("staff_id"));
				viewstaff.setStaff_password(rs.getString("staff_password"));
				viewstaff.setStaff_name(rs.getString("staff_name"));
				viewstaff.setStaff_email(rs.getString("staff_email"));
				viewstaff.setStaff_phonenum(rs.getString("staff_phonenum"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return viewstaff;
	}
	
	// Update specific staff by id DAO 
	public void updatestaff(Staff updtstaff) {
		staff_id = updtstaff.getStaff_id();
		staff_name = updtstaff.getStaff_name();
		staff_email = updtstaff.getStaff_email();
		staff_phonenum = updtstaff.getStaff_phonenum();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("UPDATE staff SET staff_name = ?, staff_email = ?, staff_phonenum = ? WHERE staff_id = ?");
			ps.setString(1, staff_name);
			ps.setString(2, staff_email);
			ps.setString(3, staff_phonenum);
			ps.setString(4, staff_id);
			
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}