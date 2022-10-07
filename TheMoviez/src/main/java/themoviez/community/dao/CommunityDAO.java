package themoviez.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import themoviez.community.entity.CommunityDTO;
import themoviez.entity.PageTO;

public class CommunityDAO {

	DataSource dataFactory;

	public CommunityDAO() {

		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql8");
		} catch (NamingException ex) {
			ex.printStackTrace();
		}

	}

	// ========================= 커뮤니티 게시판 전체 컬럼 카운트 =========================
	public int totalCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT count(*) FROM community";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count(*)");
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

		return count;
	}

	// ========================= 페이징 구현 =========================
	public PageTO page(int curPage) {
		PageTO to = new PageTO();
		int totalCount = totalCount();

		ArrayList<CommunityDTO> list = new ArrayList<CommunityDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT * FROM community ORDER BY com_num DESC";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();

			int perPage = to.getPerPage();
			int skip = (curPage - 1) * perPage;
			if (skip > 0) {
				rs.absolute(skip);
			}

			for (int i = 0; i < perPage && rs.next(); i++) {
				int com_num = rs.getInt("com_num");
				String com_title = rs.getString("com_title");
				String m_id = rs.getString("m_id");
				String com_content = rs.getString("com_content");
				String com_writeday = rs.getString("com_writeday");
				int com_readnum = rs.getInt("com_readnum");
				String com_ip = rs.getString("com_ip");

				CommunityDTO data = new CommunityDTO();
				data.setCom_num(com_num);
				data.setCom_title(com_title);
				data.setM_id(m_id);
				data.setCom_content(com_content);
				data.setCom_writeday(com_writeday);
				data.setCom_readnum(com_readnum);
				data.setCom_ip(com_ip);

				list.add(data);
			}

			to.setCommunityList(list);
			to.setTotalCount(totalCount);
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

	// ========================= 커뮤니티 게시판 글쓰기 =========================
	public void write(String _com_title, String _m_id, String _com_content, String _com_ip) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "INSERT INTO community (com_title, m_id, com_content, com_readnum, com_ip) VALUES (?,?,?,0,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _com_title);
			pstmt.setString(2, _m_id);
			pstmt.setString(3, _com_content);
			pstmt.setString(4, _com_ip);
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

	// ========================= 커뮤니티 조회수 증가 =========================
	public void readCount(String _com_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "UPDATE community SET com_readnum = com_readnum + 1 WHERE com_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_com_num));
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

	// ========================= 커뮤니티 게시글 보기 =========================
	public CommunityDTO content(String _com_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommunityDTO data = new CommunityDTO();

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT * FROM community WHERE com_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_com_num));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int com_num = rs.getInt("com_num");
				String com_title = rs.getString("com_title");
				String m_id = rs.getString("m_id");
				String com_content = rs.getString("com_content");
				String com_writeday = rs.getString("com_writeday");
				int com_readnum = rs.getInt("com_readnum");
				String com_ip = rs.getString("com_ip");

				data.setCom_num(com_num);
				data.setCom_title(com_title);
				data.setM_id(m_id);
				data.setCom_content(com_content);
				data.setCom_writeday(com_writeday);
				data.setCom_readnum(com_readnum);
				data.setCom_ip(com_ip);
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

		return data;
	}

	// ========================= 커뮤니티 게시글 삭제 =========================
	public void delete(String _com_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "DELETE FROM community WHERE com_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_com_num));
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

	// ========================= 커뮤니티 게시글 수정 =========================
	public void edit(String _com_num, String _com_title, String _com_content) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "UPDATE community SET com_title = ?, com_content = ? WHERE com_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _com_title);
			pstmt.setString(2, _com_content);
			pstmt.setInt(3, Integer.parseInt(_com_num));
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

	// ========================= 검색 테이블 컬럼 카운트 =========================
	public int searchCount(String _method, String _keyword) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT count(*) FROM community";

			if (_method.equals("com_title")) {
				sql += " WHERE com_title LIKE ? ORDER BY com_num DESC";
			} else if (_method.equals("com_content")) {
				sql += " WHERE com_content LIKE ? ORDER BY com_num DESC";
			} else {
				sql += " WHERE m_id LIKE ? ORDER BY com_num DESC";
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + _keyword + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count(*)");
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

		return count;
	}

	// ========================= 검색 페이징 구현 =========================
	public PageTO search(int curPage, String _method, String _keyword) {
		PageTO to = new PageTO();
		int searchCount = searchCount(_method, _keyword);

		ArrayList<CommunityDTO> list = new ArrayList<CommunityDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT * FROM community";

			if (_method.equals("com_title")) {
				sql += " WHERE com_title LIKE ? ORDER BY com_num DESC";
			} else if (_method.equals("com_content")) {
				sql += " WHERE com_content LIKE ? ORDER BY com_num DESC";
			} else {
				sql += " WHERE m_id LIKE ? ORDER BY com_num DESC";
			}

			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, "%" + _keyword + "%");
			rs = pstmt.executeQuery();

			int perPage = to.getPerPage();
			int skip = (curPage - 1) * perPage;
			if (skip > 0) {
				rs.absolute(skip);
			}

			for (int i = 0; i < perPage && rs.next(); i++) {
				int com_num = rs.getInt("com_num");
				String com_title = rs.getString("com_title");
				String m_id = rs.getString("m_id");
				String com_content = rs.getString("com_content");
				String com_writeday = rs.getString("com_writeday");
				int com_readnum = rs.getInt("com_readnum");
				String com_ip = rs.getString("com_ip");

				CommunityDTO data = new CommunityDTO();
				data.setCom_num(com_num);
				data.setCom_title(com_title);
				data.setM_id(m_id);
				data.setCom_content(com_content);
				data.setCom_writeday(com_writeday);
				data.setCom_readnum(com_readnum);
				data.setCom_ip(com_ip);

				list.add(data);
			}

			to.setCommunityList(list);
			to.setTotalCount(searchCount);
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

}
