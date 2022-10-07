package themoviez.review.entity;

public class ReviewDTO {
	private int rev_num;

	private String m_id;
	private String rev_content;
	private String rev_writeday;
	private int rev_like;
	private String rev_ip;
	private String link;
	
	
	public int getRev_num() {
		return rev_num;
	}
	public void setRev_num(int rev_num) {
		this.rev_num = rev_num;
	}

	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getRev_content() {
		return rev_content;
	}
	public void setRev_content(String rev_content) {
		this.rev_content = rev_content;
	}
	public String getRev_writeday() {
		return rev_writeday;
	}
	public void setRev_writeday(String rev_writeday) {
		this.rev_writeday = rev_writeday;
	}
	public int getRev_like() {
		return rev_like;
	}
	public void setRev_like(int rev_like) {
		this.rev_like = rev_like;
	}
	public String getRev_ip() {
		return rev_ip;
	}
	public void setRev_ip(String rev_ip) {
		this.rev_ip = rev_ip;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	
}
