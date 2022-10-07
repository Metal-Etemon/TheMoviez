package themoviez.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.member.dao.MemberDAO;
import themoviez.service.TheMoviezCommand;

public class MemberEditCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			req.setCharacterEncoding("UTF-8");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String m_num = req.getParameter("m_num");
		String m_name = req.getParameter("m_name");
		String m_gender = req.getParameter("m_gender");
		String m_birth = req.getParameter("m_birth");
		String m_tel = req.getParameter("m_tel");
		String m_zipcode = req.getParameter("zipNo");
		String m_addr = req.getParameter("roadAddrPart1");
		String m_addrdetail = req.getParameter("addrDetail");
		
		String[] like = req.getParameterValues("m_like");
		String m_like = "";
		if (like != null) {
			for (int i = 0; i < like.length; i++) {
				m_like = m_like + " " + like[i];
			}
		}
		
		MemberDAO dao = new MemberDAO();
		dao.memberEdit(m_num, m_name, m_gender, m_birth, m_tel, m_zipcode, m_addr, m_addrdetail, m_like);
		
		req.setAttribute("m_num", m_num);
	}

}
