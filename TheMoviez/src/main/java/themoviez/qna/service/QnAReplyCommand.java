package themoviez.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.qna.dao.QnADAO;
import themoviez.service.TheMoviezCommand;

public class QnAReplyCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String qna_num=req.getParameter("qna_num");
		String qna_title=req.getParameter("qna_title");
		String m_id=req.getParameter("m_id");
		String qna_passwd=req.getParameter("qna_passwd");
		String qna_content=req.getParameter("qna_content");
		String qna_ip=req.getRemoteAddr();
		String qna_repRoot=req.getParameter("qna_repRoot");
		String qna_repStep=req.getParameter("qna_repStep");
		String qna_repIndent=req.getParameter("qna_repIndent");
		
		QnADAO dao=new QnADAO();
		dao.reply(qna_num, qna_title, m_id, qna_passwd, qna_content, qna_ip, qna_repRoot, qna_repStep, qna_repIndent);
		
	}

	
}
