package themoviez.qna.entity;

public class QnADTO {
	int qna_num;
	String qna_title;
	String m_id;
	String qna_passwd;
	String qna_content;
	String qna_writeday;
	int qna_readnum;
	String qna_ip;
	int qna_repRoot;
	int qna_repStep;
	int qna_repIndent;

	public QnADTO() {
	}

	QnADTO(int qna_num, String qna_title, String m_id, String qna_passwd, String qna_content, String qna_writeday,
			int qna_readnum, String qna_ip, int qna_repRoot, int qna_repStep, int qna_repIndent) {
		this.qna_num = qna_num;
		this.qna_title = qna_title;
		this.m_id = m_id;
		this.qna_passwd = qna_passwd;
		this.qna_content = qna_content;
		this.qna_writeday = qna_writeday;
		this.qna_readnum = qna_readnum;
		this.qna_ip = qna_ip;
		this.qna_repRoot = qna_repRoot;
		this.qna_repStep = qna_repStep;
		this.qna_repStep = qna_repStep;
	}

	public int getQna_num() {
		return qna_num;
	}

	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}

	public String getQna_title() {
		return qna_title;
	}

	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getQna_passwd() {
		return qna_passwd;
	}

	public void setQna_passwd(String qna_passwd) {
		this.qna_passwd = qna_passwd;
	}

	public String getQna_content() {
		return qna_content;
	}

	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}

	public String getQna_writeday() {
		return qna_writeday;
	}

	public void setQna_writeday(String qna_writeday) {
		this.qna_writeday = qna_writeday;
	}

	public int getQna_readnum() {
		return qna_readnum;
	}

	public void setQna_readnum(int qna_readnum) {
		this.qna_readnum = qna_readnum;
	}

	public String getQna_ip() {
		return qna_ip;
	}

	public void setQna_ip(String qna_ip) {
		this.qna_ip = qna_ip;
	}

	public int getQna_repRoot() {
		return qna_repRoot;
	}

	public void setQna_repRoot(int qna_repRoot) {
		this.qna_repRoot = qna_repRoot;
	}

	public int getQna_repStep() {
		return qna_repStep;
	}

	public void setQna_repStep(int qna_repStep) {
		this.qna_repStep = qna_repStep;
	}

	public int getQna_repIndent() {
		return qna_repIndent;
	}

	public void setQna_repIndent(int qna_repIndent) {
		this.qna_repIndent = qna_repIndent;
	}
}
