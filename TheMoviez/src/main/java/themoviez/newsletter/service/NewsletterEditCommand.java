package themoviez.newsletter.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.newsletter.dao.NewsletterDAO;
import themoviez.service.TheMoviezCommand;

public class NewsletterEditCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		String news_num = req.getParameter("news_num");
		String news_title = req.getParameter("news_title");
		String m_id = req.getParameter("m_id");

		String news_content = req.getParameter("news_content");

		NewsletterDAO dao = new NewsletterDAO();
		dao.edit(news_num, news_title, news_content);

	}

}
