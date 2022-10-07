package themoviez.comment.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.comment.dao.CommentDAO;
import themoviez.service.TheMoviezCommand;

public class ComCommentDeleteCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String cmt_num = req.getParameter("cmt_num");
		String com_num = req.getParameter("com_num");
		CommentDAO dao = new CommentDAO();
		dao.comCmtDelete(cmt_num);
		
	}

}
