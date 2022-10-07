package themoviez.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.member.dao.MemberDAO;
import themoviez.member.entity.MemberDTO;
import themoviez.service.TheMoviezCommand;

public class MemberIdCheckCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	
		String m_id = req.getParameter("m_id");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO data = dao.idCheck(m_id);
	
		req.setAttribute("idCheck", data);
	}

}
