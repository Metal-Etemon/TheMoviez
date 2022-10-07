package themoviez.newsletter.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.newsletter.dao.NewsletterDAO;
import themoviez.newsletter.entity.NewsletterDTO;
import themoviez.service.TheMoviezCommand;

public class NewsletterContentCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		String news_num = req.getParameter("news_num");

		NewsletterDAO dao = new NewsletterDAO();
		NewsletterDTO data = dao.content(news_num);

		req.setAttribute("content", data);
	}

}
