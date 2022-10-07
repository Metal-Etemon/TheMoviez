package themoviez.movie.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.movie.dao.MovieDAO;
import themoviez.movie.entity.MovieDTO;
import themoviez.service.TheMoviezCommand;

public class MovieReviewCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		String link = req.getParameter("link");

		MovieDAO dao = new MovieDAO();
		MovieDTO data = dao.movieReview(link);
		
		req.setAttribute("movie", data);
		System.out.println(link);
	}

}
