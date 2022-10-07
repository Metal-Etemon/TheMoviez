package themoviez.review.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.comment.dao.CommentDAO;
import themoviez.comment.entity.CommentDTO;
import themoviez.community.dao.CommunityDAO;
import themoviez.community.entity.CommunityDTO;
import themoviez.service.TheMoviezCommand;

public class ComCommentListCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String com_num = req.getParameter("com_num");
		
		CommunityDAO dao = new CommunityDAO();
		CommunityDTO data = dao.content(com_num);
		req.setAttribute("content", data);
		
		CommentDAO cmtDao = new CommentDAO();	
		ArrayList<CommentDTO> cmtList = cmtDao.comCmtList(com_num);
		
//		ArrayList<CommentDTO> cmtData = cmtDao.
//		ContentCommentDTO cmtdata = cmtDao.comtCmtList(com_num);
		req.setAttribute("cmtList", cmtList);

	}

}
