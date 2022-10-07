package themoviez.comment.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.comment.dao.CommentDAO;
import themoviez.community.dao.CommunityDAO;
import themoviez.community.entity.CommunityDTO;
import themoviez.entity.PageTO;
import themoviez.service.TheMoviezCommand;

public class ComCommentListPageCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		String com_num = req.getParameter("com_num");

		int curPage = 1;

		if (req.getParameter("curPage") != null) {
			curPage = Integer.parseInt(req.getParameter("curPage"));
		}

		CommunityDAO dao = new CommunityDAO();
		CommunityDTO data = dao.content(com_num);
		req.setAttribute("content", data);

		CommentDAO cmtDao = new CommentDAO();
//		ArrayList<CommentDTO> cmtList = cmtDao.comCmtList(com_num);
//		req.setAttribute("cmtList", cmtList);

		PageTO cmtList = cmtDao.comCmtListPage(curPage, com_num);
		
		req.setAttribute("cmtList", cmtList.getCommentList());
		req.setAttribute("page", cmtList);

	}

}
