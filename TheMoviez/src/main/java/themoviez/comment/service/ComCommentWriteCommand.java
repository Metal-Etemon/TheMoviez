package themoviez.comment.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import themoviez.comment.dao.CommentDAO;
import themoviez.service.TheMoviezCommand;

public class ComCommentWriteCommand implements TheMoviezCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String com_num = req.getParameter("com_num");
		String m_id = req.getParameter("m_id");
		String cmt_content = req.getParameter("cmt_content");
		String cmt_ip = req.getRemoteAddr();
		
		CommentDAO dao = new CommentDAO();
		dao.comCmtWrite(com_num, m_id, cmt_content, cmt_ip);
	}

}
