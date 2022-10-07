package themoviez.community.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.community.dao.CommunityDAO;
import themoviez.service.TheMoviezCommand;

public class CommunityDeleteCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String com_num = req.getParameter("com_num");
		
		CommunityDAO dao = new CommunityDAO();
		dao.delete(com_num);
	}

}
