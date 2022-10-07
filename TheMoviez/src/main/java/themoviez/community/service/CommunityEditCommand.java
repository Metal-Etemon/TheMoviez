package themoviez.community.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.community.dao.CommunityDAO;
import themoviez.service.TheMoviezCommand;

public class CommunityEditCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String com_num = req.getParameter("com_num");
		String com_title = req.getParameter("com_title");
		String com_content = req.getParameter("com_content");
		
		CommunityDAO dao = new CommunityDAO();
		dao.edit(com_num, com_title, com_content);
		
		
	}

}
