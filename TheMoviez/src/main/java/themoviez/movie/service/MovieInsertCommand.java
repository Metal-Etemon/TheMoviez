package themoviez.movie.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.movie.dao.MovieDAO;
import themoviez.movie.entity.MovieDTO;
import themoviez.service.TheMoviezCommand;

public class MovieInsertCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String moviez = req.getParameter("title");
		String match = "<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>";
		String movie = moviez.replaceAll(match, "");
		
		String title = movie;
		String link = req.getParameter("link");
		String image = req.getParameter("image");
		String subtitle = req.getParameter("subtitle");
		String pubDate = req.getParameter("pubDate");
		String director = req.getParameter("director");
		String actor = req.getParameter("actor");
		String userRating = req.getParameter("userRating");
		
		
		MovieDAO dao = new MovieDAO();
		dao.movieWrite(title, link, image, subtitle, pubDate, director, actor, userRating);
		
		MovieDTO data = dao.movieReview(link);
		req.setAttribute("movie", data);
		
		
	}

}
