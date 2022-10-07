package themoviez.community.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.community.dao.CommunityDAO;
import themoviez.service.TheMoviezCommand;

public class CommunityWriteCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String com_title = req.getParameter("com_title");
		String m_id = req.getParameter("m_id");
		String com_content = req.getParameter("com_content");
		String com_ip = req.getRemoteAddr();
		
		CommunityDAO dao = new CommunityDAO();
		dao.write(com_title, m_id, com_content, com_ip);
		
	}

}
