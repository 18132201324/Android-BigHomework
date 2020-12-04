package com.example.smartcommunityapplication.entities;

public class User {
	private String id;
	private String phone_number;
	private String password;
	private String truth_name;
	private String nick_name;
	private String gender;
	private String introduce;
	private String indentity;
	private float creditworthiness;
	private String head_photo;
	private String belong_community;
	private float balance;
	private int score;
	
	public User() {
		super();
	}
	public User(String id, String phone_number, String password, String truth_name, String nick_name, String gender,
			String introduce, String indentity, float creditworthiness, String head_photo, String belong_community,float balance,
			int score) {
		super();
		this.id = id;
		this.phone_number = phone_number;
		this.password = password;
		this.truth_name = truth_name;
		this.nick_name = nick_name;
		this.gender = gender;
		this.introduce = introduce;
		this.indentity = indentity;
		this.creditworthiness = creditworthiness;
		this.head_photo = head_photo;
		this.belong_community = belong_community;
		this.balance = balance;
		this.score = score;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTruth_name() {
		return truth_name;
	}
	public void setTruth_name(String truth_name) {
		this.truth_name = truth_name;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getIndentity() {
		return indentity;
	}
	public void setIndentity(String indentity) {
		this.indentity = indentity;
	}
	public float getCreditworthiness() {
		return creditworthiness;
	}
	public void setCreditworthiness(float creditworthiness) {
		this.creditworthiness = creditworthiness;
	}
	public String getHead_photo() {
		return head_photo;
	}
	public void setHead_photo(String head_photo) {
		this.head_photo = head_photo;
	}
	public String getBelong_community() {
		return belong_community;
	}
	public void setBelong_community(String belong_community) {
		this.belong_community = belong_community;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", phone_number=" + phone_number + ", password=" + password + ", truth_name="
				+ truth_name + ", nick_name=" + nick_name + ", gender=" + gender + ", introduce=" + introduce
				+ ", indentity=" + indentity + ", creditworthiness=" + creditworthiness + ", head_photo=" + head_photo
				+ ", belong_community=" + belong_community + ", balance=" + balance + ", score=" + score + "]";
	}
	
}
