package themoviez.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.qna.dao.QnADAO;
import themoviez.qna.entity.QnADTO;
import themoviez.service.TheMoviezCommand;

public class QnAContentCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		String qna_num = req.getParameter("qna_num");

		QnADAO dao = new QnADAO();
		QnADTO data = dao.content(qna_num);

		req.setAttribute("content", data);
	}

}
