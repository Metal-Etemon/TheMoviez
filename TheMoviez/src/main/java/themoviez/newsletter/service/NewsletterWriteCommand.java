package themoviez.newsletter.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.newsletter.dao.NewsletterDAO;
import themoviez.service.TheMoviezCommand;

public class NewsletterWriteCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String news_title = req.getParameter("news_title");
		String m_id = req.getParameter("m_id");

		String news_content = req.getParameter("news_content");
		String news_ip = req.getRemoteAddr();
		
		NewsletterDAO dao = new NewsletterDAO();
		dao.write(news_title, m_id, news_content, news_ip);
		
	}

}
