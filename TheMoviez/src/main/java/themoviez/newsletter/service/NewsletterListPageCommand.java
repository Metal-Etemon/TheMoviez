package themoviez.newsletter.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.entity.PageTO;
import themoviez.newsletter.dao.NewsletterDAO;
import themoviez.service.TheMoviezCommand;

public class NewsletterListPageCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int curPage = 1;

		if (req.getParameter("curPage") != null) {
			curPage = Integer.parseInt(req.getParameter("curPage"));
		}

		NewsletterDAO dao = new NewsletterDAO();
		PageTO list = dao.page(curPage);

		req.setAttribute("list", list.getNewsletterList());
		req.setAttribute("page", list);

	}

}
