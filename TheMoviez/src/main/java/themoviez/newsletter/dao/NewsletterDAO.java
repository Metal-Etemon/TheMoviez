package themoviez.newsletter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import themoviez.entity.PageTO;
import themoviez.newsletter.entity.NewsletterDTO;

public class NewsletterDAO {

	DataSource dataFactory;

	public NewsletterDAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql8");
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}

	// ========================= 공지사항 게시판 전체 컬럼 카운트 =========================
	public int totalCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT count(*) FROM newsletter";
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

		ArrayList<NewsletterDTO> list = new ArrayList<NewsletterDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT * FROM newsletter ORDER BY news_num DESC";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();

			int perPage = to.getPerPage();
			int skip = (curPage - 1) * perPage;
			if (skip > 0) {
				rs.absolute(skip);
			}

			for (int i = 0; i < perPage && rs.next(); i++) {
				int news_num = rs.getInt("news_num");
				String news_title = rs.getString("news_title");
				String m_id = rs.getString("m_id");
				String news_content = rs.getString("news_content");
				String news_writeday = rs.getString("news_writeday");
				int news_readnum = rs.getInt("news_readnum");
				String news_ip = rs.getString("news_ip");

				NewsletterDTO data = new NewsletterDTO();
				data.setNews_num(news_num);
				data.setNews_title(news_title);
				data.setM_id(m_id);
				data.setNews_content(news_content);
				data.setNews_writeday(news_writeday);
				data.setNews_readnum(news_readnum);
				data.setNews_ip(news_ip);

				list.add(data);
			}

			to.setNewsletterList(list);
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

	// ========================= 공지사항 게시판 글쓰기 =========================
	public void write(String _news_title, String _m_id, String _news_content, String _news_ip) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "INSERT INTO newsletter (news_title, m_id, news_content, news_readnum, news_ip) VALUES (?,?,?,0,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _news_title);
			pstmt.setString(2, _m_id);
			pstmt.setString(3, _news_content);
			pstmt.setString(4, _news_ip);
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

	// ========================= 공지사항 조회수 증가 =========================
	public void readCount(String _news_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "UPDATE newsletter SET news_readnum = news_readnum + 1 WHERE news_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_news_num));
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

	// ========================= 공지사항 게시글 보기 =========================
	public NewsletterDTO content(String _news_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NewsletterDTO data = new NewsletterDTO();

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT * FROM newsletter WHERE news_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_news_num));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int news_num = rs.getInt("news_num");
				String news_title = rs.getString("news_title");
				String m_id = rs.getString("m_id");
				String news_content = rs.getString("news_content");
				String news_writeday = rs.getString("news_writeday");
				int news_readnum = rs.getInt("news_readnum");
				String news_ip = rs.getString("news_ip");

				data.setNews_num(news_num);
				data.setNews_title(news_title);
				data.setM_id(m_id);
				data.setNews_content(news_content);
				data.setNews_writeday(news_writeday);
				data.setNews_readnum(news_readnum);
				data.setNews_ip(news_ip);
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

	// ========================= 공지사항 게시글 삭제 =========================
	public void delete(String _news_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "DELETE FROM newsletter WHERE news_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_news_num));
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

	// ========================= 공지사항 게시글 수정 =========================
	public void edit(String _news_num, String _news_title, String _news_content) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "UPDATE newsletter SET news_title = ?, news_content = ? WHERE news_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _news_title);
			pstmt.setString(2, _news_content);
			pstmt.setInt(3, Integer.parseInt(_news_num));
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
			String sql = "SELECT count(*) FROM newsletter";

			if (_method.equals("news_title")) {
				sql += " WHERE news_title LIKE ? ORDER BY news_num DESC";
			} else if (_method.equals("news_content")) {
				sql += " WHERE news_content LIKE ? ORDER BY news_num DESC";
			} else {
				sql += " WHERE m_id LIKE ? ORDER BY news_num DESC";
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

		ArrayList<NewsletterDTO> list = new ArrayList<NewsletterDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT * FROM newsletter";

			if (_method.equals("news_title")) {
				sql += " WHERE news_title LIKE ? ORDER BY news_num DESC";
			} else if (_method.equals("news_content")) {
				sql += " WHERE news_content LIKE ? ORDER BY news_num DESC";
			} else {
				sql += " WHERE m_id LIKE ? ORDER BY news_num DESC";
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
				int news_num = rs.getInt("news_num");
				String news_title = rs.getString("news_title");
				String m_id = rs.getString("m_id");
				String news_content = rs.getString("news_content");
				String news_writeday = rs.getString("news_writeday");
				int news_readnum = rs.getInt("news_readnum");
				String news_ip = rs.getString("news_ip");

				NewsletterDTO data = new NewsletterDTO();
				data.setNews_num(news_num);
				data.setNews_title(news_title);
				data.setM_id(m_id);
				data.setNews_content(news_content);
				data.setNews_writeday(news_writeday);
				data.setNews_readnum(news_readnum);
				data.setNews_ip(news_ip);

				list.add(data);
			}

			to.setNewsletterList(list);
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
