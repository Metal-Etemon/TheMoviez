package themoviez.community.entity;

public class CommunityDTO {
	private int com_num;
	private String com_title;   
	private String m_id;
	private String com_content;
	private String com_writeday;
	private int com_readnum;
	private String com_ip;
	
	public CommunityDTO() {}
	
	public CommunityDTO(int com_num, String com_title, String m_id, String com_content,
			String com_writeday, int com_readnum, String com_ip) {
		this.com_num = com_num;
		this.com_title = com_title;
		this.m_id = m_id;
		this.com_content = com_content;
		this.com_writeday = com_writeday;
		this.com_readnum = com_readnum;
		this.com_ip = com_ip;
	}

	public int getCom_num() {
		return com_num;
	}

	public void setCom_num(int com_num) {
		this.com_num = com_num;
	}

	public String getCom_title() {
		return com_title;
	}

	public void setCom_title(String com_title) {
		this.com_title = com_title;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getCom_content() {
		return com_content;
	}

	public void setCom_content(String com_content) {
		this.com_content = com_content;
	}

	public String getCom_writeday() {
		return com_writeday;
	}

	public void setCom_writeday(String com_writeday) {
		this.com_writeday = com_writeday;
	}

	public int getCom_readnum() {
		return com_readnum;
	}

	public void setCom_readnum(int com_readnum) {
		this.com_readnum = com_readnum;
	}

	public String getCom_ip() {
		return com_ip;
	}

	public void setCom_ip(String com_ip) {
		this.com_ip = com_ip;
	}
	
	
}
