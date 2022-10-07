package themoviez.community.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.community.dao.CommunityDAO;
import themoviez.entity.PageTO;
import themoviez.service.TheMoviezCommand;

public class CommunitySearchCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int curPage = 1;
		
		if (req.getParameter("curPage") != null) {
			curPage = Integer.parseInt(req.getParameter("curPage"));
		}
		
		String method = req.getParameter("method");
		String keyword = req.getParameter("keyword");

		
		req.setAttribute("method", method);
		req.setAttribute("keyword", keyword);

		
		CommunityDAO dao = new CommunityDAO();
		PageTO list = dao.search(curPage, method, keyword);
		
		req.setAttribute("list", list.getCommunityList());
		req.setAttribute("page", list);
		
	}
	
	

}
