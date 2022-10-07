 package themoviez.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import themoviez.comment.entity.CommentDTO;
import themoviez.entity.PageTO;

public class CommentDAO {

	DataSource dataFactory;

	public CommentDAO() {

		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql8");
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}
	
	// ========================= 커뮤니티 게시글 댓글 리스트 =========================
	public ArrayList<CommentDTO> comCmtList(String _com_num) {
		
		ArrayList<CommentDTO> cmtList = new ArrayList<CommentDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT DISTINCT cmt.* FROM comment cmt, community com WHERE cmt.com_num = ? ORDER BY cmt.cmt_repRoot ASC, cmt.cmt_repStep ASC;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_com_num));
			rs = pstmt.executeQuery();
			
			


			while (rs.next()) {
				int com_num = rs.getInt("cmt.com_num");
				int cmt_num = rs.getInt("cmt.cmt_num");
				String m_id = rs.getString("cmt.m_id");
				String cmt_content = rs.getString("cmt.cmt_content");
				String cmt_writeday = rs.getString("cmt.cmt_writeday");
				int cmt_repRoot = rs.getInt("cmt.cmt_repRoot");
				int cmt_repStep = rs.getInt("cmt.cmt_repStep");
				int cmt_repIndent = rs.getInt("cmt.cmt_repIndent");
				String cmt_ip = rs.getString("cmt_ip");


				CommentDTO data = new CommentDTO();
				data.setCom_num(com_num);
				data.setCmt_num(cmt_num);
				data.setM_id(m_id);
				data.setCmt_content(cmt_content);
				data.setCmt_writeday(cmt_writeday);
				data.setCmt_repRoot(cmt_repRoot);
				data.setCmt_repStep(cmt_repStep);
				data.setCmt_repIndent(cmt_repIndent);
				data.setCmt_ip(cmt_ip);

				cmtList.add(data);
			}
			
	
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return cmtList;
	}
	
	
	// ========================= 커뮤니티 게시글 댓글 카운트 =========================
	public int comCmtTotalCount(String _com_num) {
		int cmtCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT count(*) FROM comment WHERE com_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_com_num));
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				cmtCount = rs.getInt("count(*)");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return cmtCount;
	}

	// ========================= 커뮤니티 게시글 댓글 리스트 페이징 =========================
	public PageTO comCmtListPage(int curPage, String _com_num) {
		PageTO to = new PageTO();
		int comCmtTotalCount = comCmtTotalCount(_com_num);
		
		ArrayList<CommentDTO> cmtList = new ArrayList<CommentDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT DISTINCT cmt.* FROM comment cmt, community com WHERE cmt.com_num = ? ORDER BY cmt.cmt_repRoot ASC, cmt.cmt_repStep ASC;";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setInt(1, Integer.parseInt(_com_num));
			rs = pstmt.executeQuery();
			
			int perPage = to.getPerPage();
			int skip = (curPage - 1) * perPage;
			if (skip > 0) {
				rs.absolute(skip);
			}


			for (int i = 0; i < perPage && rs.next(); i++) {
				int com_num = rs.getInt("cmt.com_num");
				int cmt_num = rs.getInt("cmt.cmt_num");
				String m_id = rs.getString("cmt.m_id");
				String cmt_content = rs.getString("cmt.cmt_content");
				String cmt_writeday = rs.getString("cmt.cmt_writeday");
				int cmt_repRoot = rs.getInt("cmt.cmt_repRoot");
				int cmt_repStep = rs.getInt("cmt.cmt_repStep");
				int cmt_repIndent = rs.getInt("cmt.cmt_repIndent");
				String cmt_ip = rs.getString("cmt_ip");

				CommentDTO data = new CommentDTO();
				data.setCom_num(com_num);
				data.setCmt_num(cmt_num);
				data.setM_id(m_id);
				data.setCmt_content(cmt_content);
				data.setCmt_writeday(cmt_writeday);
				data.setCmt_repRoot(cmt_repRoot);
				data.setCmt_repStep(cmt_repStep);
				data.setCmt_repIndent(cmt_repIndent);
				data.setCmt_ip(cmt_ip);

				cmtList.add(data);
			}
			
			to.setCommentList(cmtList);
			to.setTotalCount(comCmtTotalCount);
			to.setCurPage(curPage);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return to;
	}

	// ========================= 커뮤니티 게시글 댓글 루트 증가 =========================
	public int comCmtCurrval(String _com_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int currval = 0;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT max(cmt_repRoot) FROM comment WHERE com_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_com_num));
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				if (rs.getString("max(cmt_repRoot)") != null) {
					currval = rs.getInt("max(cmt_repRoot)");
					currval = currval + 1;
					
				} else {
					currval = 0;
				}
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return currval;
	}

	// ========================= 커뮤니티 게시글 댓글 쓰기 =========================
	public void comCmtWrite(String _com_num, String _m_id, String _cmt_content, String _cmt_ip) {
		int currval = comCmtCurrval(_com_num);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "INSERT INTO comment (m_id, cmt_content, cmt_ip, com_num, cmt_repRoot, cmt_repStep, cmt_repIndent) VALUES (?, ?, ?, ?, "+ currval +", 0, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _m_id);
			pstmt.setString(2, _cmt_content);
			pstmt.setString(3, _cmt_ip);
			pstmt.setInt(4, Integer.parseInt(_com_num));
			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	// ========================= 커뮤니티 게시글 댓글 삭제 =========================
	public void comCmtDelete(String _cmt_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=dataFactory.getConnection();
			String sql = "DELETE FROM comment WHERE cmt_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_cmt_num));
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
