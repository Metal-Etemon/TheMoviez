package themoviez.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.member.dao.MemberDAO;
import themoviez.service.TheMoviezCommand;

public class MemberDeleteCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String m_num = req.getParameter("m_num");
		
		MemberDAO dao = new MemberDAO();
		dao.memberDelete(m_num);
		
	}

}
