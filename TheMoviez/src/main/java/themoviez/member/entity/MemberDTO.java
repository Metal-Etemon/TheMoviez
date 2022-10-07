package themoviez.member.entity;

public class MemberDTO {
	private int m_num;
	private String m_id;
	private String m_passwd;
	private String m_name;
	private String m_gender;
	private String m_birth;
	private String m_tel;
	private String m_zipcode;
	private String m_addr;
	private String m_addrdetail;
	private String m_like;
	private String m_date;
	private String m_ip;
	
	
	public MemberDTO() {}
	
	MemberDTO(int m_num, String m_id, String m_passwd, String m_name, String m_gender, String m_birth, String m_tel, String m_zipcode, String m_addr, String m_addrdetail, String m_like, String m_date, String m_ip) {
		this.m_num = m_num;
		this.m_id = m_id;
		this.m_passwd = m_passwd;
		this.m_name = m_name;
		this.m_gender = m_gender;
		this.m_birth = m_birth;
		this.m_tel = m_tel;
		this.m_zipcode = m_zipcode;
		this.m_addr = m_addr;
		this.m_addrdetail = m_addrdetail;
		this.m_like = m_like;
		this.m_date = m_date;
		this.m_ip = m_ip;
		
	}
	
	
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_passwd() {
		return m_passwd;
	}
	public void setM_passwd(String m_passwd) {
		this.m_passwd = m_passwd;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_gender() {
		return m_gender;
	}
	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}
	public String getM_birth() {
		return m_birth;
	}
	public void setM_birth(String m_birth) {
		this.m_birth = m_birth;
	}
	public String getM_tel() {
		return m_tel;
	}
	public void setM_tel(String m_tel) {
		this.m_tel = m_tel;
	}
	public String getM_zipcode() {
		return m_zipcode;
	}
	public void setM_zipcode(String m_zipcode) {
		this.m_zipcode = m_zipcode;
	}
	public String getM_addr() {
		return m_addr;
	}
	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}
	public String getM_addrdetail() {
		return m_addrdetail;
	}
	public void setM_addrdetail(String m_addrdetail) {
		this.m_addrdetail = m_addrdetail;
	}
	public String getM_like() {
		return m_like;
	}
	public void setM_like(String m_like) {
		this.m_like = m_like;
	}
	public String getM_date() {
		return m_date;
	}
	public void setM_date(String m_date) {
		this.m_date = m_date;
	}
	public String getM_ip() {
		return m_ip;
	}
	public void setM_ip(String m_ip) {
		this.m_ip = m_ip;
	}
	
	
}
