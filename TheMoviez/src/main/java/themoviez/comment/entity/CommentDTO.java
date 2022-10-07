package themoviez.comment.entity;

public class CommentDTO {
	private int cmt_num;
	private String m_id;
	private String cmt_content;
	private String cmt_writeday;
	private int cmt_repRoot;
	private int cmt_repStep;
	private int cmt_repIndent;
	private String cmt_ip;
	private int com_num;
	private int news_num;
	private int qna_num;
	
	public int getCmt_num() {
		return cmt_num;
	}
	public void setCmt_num(int cmt_num) {
		this.cmt_num = cmt_num;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getCmt_content() {
		return cmt_content;
	}
	public void setCmt_content(String cmt_content) {
		this.cmt_content = cmt_content;
	}
	public String getCmt_writeday() {
		return cmt_writeday;
	}
	public void setCmt_writeday(String cmt_writeday) {
		this.cmt_writeday = cmt_writeday;
	}
	public int getCmt_repRoot() {
		return cmt_repRoot;
	}
	public void setCmt_repRoot(int cmt_repRoot) {
		this.cmt_repRoot = cmt_repRoot;
	}
	public int getCmt_repStep() {
		return cmt_repStep;
	}
	public void setCmt_repStep(int cmt_repStep) {
		this.cmt_repStep = cmt_repStep;
	}
	public int getCmt_repIndent() {
		return cmt_repIndent;
	}
	public void setCmt_repIndent(int cmt_repIndent) {
		this.cmt_repIndent = cmt_repIndent;
	}
	
	public String getCmt_ip() {
		return cmt_ip;
	}
	public void setCmt_ip(String cmt_ip) {
		this.cmt_ip = cmt_ip;
	}
	public int getCom_num() {
		return com_num;
	}
	public void setCom_num(int com_num) {
		this.com_num = com_num;
	}
	public int getNews_num() {
		return news_num;
	}
	public void setNews_num(int news_num) {
		this.news_num = news_num;
	}
	public int getQna_num() {
		return qna_num;
	}
	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}
	
	
}
