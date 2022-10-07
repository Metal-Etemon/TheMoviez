package themoviez.review.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.review.dao.ReviewDAO;
import themoviez.service.TheMoviezCommand;

public class ReviewWriteCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String moviez = req.getParameter("movie");
		String match = "<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>";
		String movie = moviez.replaceAll(match, "");
		
		String link = req.getParameter("link");
		String m_id = req.getParameter("m_id");
		String rev_content = req.getParameter("rev_content");
		String rev_ip = req.getRemoteAddr();
		
		ReviewDAO dao = new ReviewDAO();
		dao.revWrite(link, m_id, rev_content, rev_ip);
	}

}
