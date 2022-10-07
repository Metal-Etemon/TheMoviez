package themoviez.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.qna.dao.QnADAO;
import themoviez.service.TheMoviezCommand;

public class QnADeleteCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String qna_num = req.getParameter("qna_num");
		
		QnADAO dao = new QnADAO();
		dao.delete(qna_num);
	}

}
