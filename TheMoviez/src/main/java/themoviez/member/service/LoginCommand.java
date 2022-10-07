package themoviez.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import themoviez.member.dao.MemberDAO;
import themoviez.member.entity.MemberDTO;
import themoviez.service.TheMoviezCommand;

public class LoginCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		String m_id = req.getParameter("m_id");
		String m_passwd = req.getParameter("m_passwd");
		MemberDAO dao = new MemberDAO();
		int logincheck = dao.loginCheck(m_id, m_passwd);
		HttpSession session = req.getSession();		
		
		if (logincheck == 1) {
			MemberDTO data = dao.getForSession(m_id);
			session.setAttribute("m_id", data.getM_id());
			session.setAttribute("m_passwd", data.getM_passwd());
			session.setAttribute("m_num", data.getM_num());			
		}
		
	}
}
