package themoviez.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.entity.PageTO;
import themoviez.member.dao.MemberDAO;
import themoviez.service.TheMoviezCommand;

public class MemberListPageCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int curPage = 1;
		
		if (req.getParameter("curPage") != null) {
			curPage = Integer.parseInt(req.getParameter("curPage"));
		}
		
		MemberDAO dao = new MemberDAO();
		PageTO memberList = dao.page(curPage);
		
		req.setAttribute("memberList", memberList.getMemberList());
		req.setAttribute("page", memberList);
		
	}
	
	

}
