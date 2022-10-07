package themoviez.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TheMoviezCommand {
	
	public void execute(HttpServletRequest req, HttpServletResponse resp);
	
}
