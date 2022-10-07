package themoviez.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.community.dao.CommunityDAO;
import themoviez.qna.dao.QnADAO;
import themoviez.service.TheMoviezCommand;

public class QnAWriteCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String qna_title = req.getParameter("qna_title");
		String m_id = req.getParameter("m_id");
		String qna_passwd = req.getParameter("qna_passwd");
		String qna_content = req.getParameter("qna_content");
		String qna_ip = req.getRemoteAddr();
		
		QnADAO dao = new QnADAO();
		dao.write(qna_title, m_id, qna_passwd, qna_content, qna_ip);
		
	}

}
