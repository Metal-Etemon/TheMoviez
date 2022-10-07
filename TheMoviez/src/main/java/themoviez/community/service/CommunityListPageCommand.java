package themoviez.community.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.community.dao.CommunityDAO;
import themoviez.entity.PageTO;
import themoviez.service.TheMoviezCommand;

public class CommunityListPageCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int curPage = 1;
		
		if (req.getParameter("curPage") != null) {
			curPage = Integer.parseInt(req.getParameter("curPage"));
		}
		
		CommunityDAO dao = new CommunityDAO();
		PageTO list = dao.page(curPage);
		
		req.setAttribute("list", list.getCommunityList());
		req.setAttribute("page", list);
		
	}

}
