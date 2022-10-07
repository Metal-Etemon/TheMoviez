package themoviez.member.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.member.dao.MemberDAO;
import themoviez.member.entity.MemberDTO;
import themoviez.service.TheMoviezCommand;

public class MemberListCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberDTO> memberList = dao.memberList();
		req.setAttribute("memberList", memberList);
		
	}

}
