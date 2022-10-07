package themoviez.review.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.comment.dao.CommentDAO;
import themoviez.review.dao.ReviewDAO;
import themoviez.service.TheMoviezCommand;

public class ReviewDeleteCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String moviez = req.getParameter("movie");
		String match = "<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>";
		String movie = moviez.replaceAll(match, "");
		
		String rev_num = req.getParameter("rev_num");
		ReviewDAO dao = new ReviewDAO();
		dao.revDelete(rev_num);
		
	}

}
