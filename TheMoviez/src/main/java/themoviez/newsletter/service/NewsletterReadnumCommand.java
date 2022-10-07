package themoviez.newsletter.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.newsletter.dao.NewsletterDAO;
import themoviez.service.TheMoviezCommand;

public class NewsletterReadnumCommand implements TheMoviezCommand {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		String news_num = req.getParameter("news_num");

		NewsletterDAO dao = new NewsletterDAO();
		dao.readCount(news_num);
		
	}
}
