package themoviez.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.qna.dao.QnADAO;
import themoviez.service.TheMoviezCommand;

public class QnAEditCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		String qna_num = req.getParameter("qna_num");
		String qna_title = req.getParameter("qna_title");
		String m_id = req.getParameter("m_id");
		String qna_passwd = req.getParameter("m_passwd");
		String qna_content = req.getParameter("qna_content");

		QnADAO dao = new QnADAO();
		dao.edit(qna_num, qna_title, m_id, qna_passwd, qna_content);

	}

}
