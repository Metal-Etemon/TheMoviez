package themoviez.movie.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.movie.dao.MovieDAO;
import themoviez.movie.entity.MovieDTO;
import themoviez.service.TheMoviezCommand;

public class MovieSearchCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String moviez = req.getParameter("movie");
		String match = "<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>";
		String movie = moviez.replaceAll(match, "");
		MovieDAO dao = new MovieDAO();
		ArrayList<MovieDTO> movieList = dao.searchResult(movie);
		req.setAttribute("movieList", movieList);
		req.setAttribute("movie", movie);
		
	}

}
