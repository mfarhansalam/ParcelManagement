package model;

import java.sql.*;

public class Parcel {
	private String parcel_id;
	private String parcel_status;
	private int parcel_weight;
	private Date parcel_date;
	private String stud_id;
	private String staff_id;
	private String courier_id;
	private Student student;
	private Courier courier;
	private Staff staff;
	private int total_by_status;
	private int total_by_courier;
	private String parcel_month;
	private int total_by_month;
	
	public String getParcel_id() {
		return parcel_id;
	}
	public void setParcel_id(String parcel_id) {
		this.parcel_id = parcel_id;
	}
	public String getParcel_status() {
		return parcel_status;
	}
	public void setParcel_status(String parcel_status) {
		this.parcel_status = parcel_status;
	}
	public int getParcel_weight() {
		return parcel_weight;
	}
	public void setParcel_weight(int parcel_weight) {
		this.parcel_weight = parcel_weight;
	}
	public Date getParcel_date() {
		return parcel_date;
	}
	public void setParcel_date(Date parcel_date) {
		this.parcel_date = parcel_date;
	}
	public String getStud_id() {
		return stud_id;
	}
	public void setStud_id(String stud_id) {
		this.stud_id = stud_id;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getCourier_id() {
		return courier_id;
	}
	public void setCourier_id(String courier_id) {
		this.courier_id = courier_id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public int getTotal_by_status() {
		return total_by_status;
	}
	public void setTotal_by_status(int total_by_status) {
		this.total_by_status = total_by_status;
	}
	public int getTotal_by_courier() {
		return total_by_courier;
	}
	public void setTotal_by_courier(int total_by_courier) {
		this.total_by_courier = total_by_courier;
	}
	public String getParcel_month() {
		return parcel_month;
	}
	public void setParcel_month(String parcel_month) {
		this.parcel_month = parcel_month;
	}
	public int getTotal_by_month() {
		return total_by_month;
	}
	public void setTotal_by_month(int total_by_month) {
		this.total_by_month = total_by_month;
	}
}