package themoviez.review.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.entity.PageTO;
import themoviez.movie.dao.MovieDAO;
import themoviez.movie.entity.MovieDTO;
import themoviez.review.dao.ReviewDAO;
import themoviez.service.TheMoviezCommand;

public class ReviewListPageCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		String link = req.getParameter("link");

		MovieDAO dao = new MovieDAO();
		MovieDTO data = dao.movieReview(link);
		req.setAttribute("movie", data);

		int curPage = 1;
		if (req.getParameter("curPage") != null) {
			curPage = Integer.parseInt(req.getParameter("curPage"));
		}

		ReviewDAO revDao = new ReviewDAO();
		PageTO revList = revDao.revListPage(curPage, link);
		req.setAttribute("revList", revList.getReviewList());
		req.setAttribute("page", revList);

	}
}
