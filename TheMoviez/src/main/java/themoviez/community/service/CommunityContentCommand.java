package themoviez.community.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.community.dao.CommunityDAO;
import themoviez.community.entity.CommunityDTO;
import themoviez.service.TheMoviezCommand;

public class CommunityContentCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		String com_num = req.getParameter("com_num");

		CommunityDAO dao = new CommunityDAO();
		CommunityDTO data = dao.content(com_num);

		req.setAttribute("content", data);
	}

}
