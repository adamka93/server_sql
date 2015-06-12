package com.Person.model;

public class Person {

	private int userID;
	private String name;
	private String pass;
	private String email;
	private String address;
	private String groupID;

	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	
    @Override
    public String toString() {
        return "User [userid=" + userID + ", name=" + name
                + ", pass=" + pass + ", email=" + email + ", address="
                + address + ", groupID=" + groupID + "]";
    } 
}
