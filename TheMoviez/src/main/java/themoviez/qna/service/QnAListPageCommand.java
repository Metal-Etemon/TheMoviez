package themoviez.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.entity.PageTO;
import themoviez.qna.dao.QnADAO;
import themoviez.service.TheMoviezCommand;

public class QnAListPageCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int curPage = 1;

		if (req.getParameter("curPage") != null) {
			curPage = Integer.parseInt(req.getParameter("curPage"));
		}

		QnADAO dao = new QnADAO();
		PageTO list = dao.page(curPage);

		req.setAttribute("list", list.getQnaList());
		req.setAttribute("page", list);

	}

}
