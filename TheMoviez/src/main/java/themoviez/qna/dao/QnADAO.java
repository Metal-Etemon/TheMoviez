package themoviez.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import themoviez.entity.PageTO;
import themoviez.qna.entity.QnADTO;

public class QnADAO {

	DataSource dataFactory;

	public QnADAO() {

		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql8");
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}

	// ========================= Q&A 게시글 전체 컬럼 카운트 =========================
	public int totalCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT count(*) FROM qna";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
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
			} catch (Exception e) {

			}
		}

		return count;
	}

	// ========================= 페이징 구현 =========================
	public PageTO page(int curPage) {
		PageTO to = new PageTO();
		int totalCount = totalCount();

		ArrayList<QnADTO> list = new ArrayList<QnADTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT * FROM qna ORDER BY qna_repRoot DESC, qna_repStep ASC";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();

			int perPage = to.getPerPage();
			int skip = (curPage - 1) * perPage;
			if (skip > 0) {
				rs.absolute(skip);
			}

			for (int i = 0; i < perPage && rs.next(); i++) {
				int qna_num = rs.getInt("qna_num");
				String qna_title = rs.getString("qna_title");
				String m_id = rs.getString("m_id");
				String qna_passwd = rs.getString("qna_passwd");
				String qna_content = rs.getString("qna_content");
				String qna_writeday = rs.getString("qna_writeday");
				int qna_readnum = rs.getInt("qna_readnum");
				String qna_ip = rs.getString("qna_ip");
				int qna_repRoot = rs.getInt("qna_repRoot");
				int qna_repStep = rs.getInt("qna_repStep");
				int qna_repIndent = rs.getInt("qna_repIndent");

				QnADTO data = new QnADTO();
				data.setQna_num(qna_num);
				data.setQna_title(qna_title);
				data.setM_id(m_id);
				data.setQna_passwd(qna_passwd);
				data.setQna_content(qna_content);
				data.setQna_writeday(qna_writeday);
				data.setQna_readnum(qna_readnum);
				data.setQna_ip(qna_ip);
				data.setQna_repRoot(qna_repRoot);
				data.setQna_repStep(qna_repStep);
				data.setQna_repIndent(qna_repIndent);

				list.add(data);
			}

			to.setQnaList(list);
			to.setTotalCount(totalCount);
			to.setCurPage(curPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {

			}
		}

		return to;

	}

	// ========================= QnA 게시글 루트 증가 =========================
	public int currval() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int currval = 0;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT max(qna_repRoot) FROM qna";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				if (rs.getString("max(qna_repRoot)") != null) {
					currval = rs.getInt("max(qna_repRoot)");
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

	// ========================= Q&A 게시글 쓰기 =========================
	public void write(String _qna_title, String _m_id, String _qna_passwd, String _qna_content, String _qna_ip) {
		int currval = currval();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "INSERT INTO qna(qna_title, m_id, qna_passwd, qna_content, qna_ip, qna_repRoot, qna_repStep, qna_repIndent) VALUES (?, ?, ?, ?, ?, '"
					+ currval + "', 0, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _qna_title);
			pstmt.setString(2, _m_id);
			pstmt.setString(3, _qna_passwd);
			pstmt.setString(4, _qna_content);
			pstmt.setString(5, _qna_ip);
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

	// ========================= Q&A 게시글 조회수 증가 =========================
	public void readCount(String _qna_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "UPDATE qna SET qna_readnum = qna_readnum + 1 WHERE qna_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_qna_num));
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

	// ========================= Q&A 게시글 보기 =========================
	public QnADTO content(String _qna_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnADTO data = new QnADTO();

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT * FROM qna WHERE qna_num= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_qna_num));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int qna_num = rs.getInt("qna_num");
				String qna_title = rs.getString("qna_title");
				String m_id = rs.getString("m_id");
				String qna_passwd = rs.getString("qna_passwd");
				String qna_content = rs.getString("qna_content");
				String qna_writeday = rs.getString("qna_writeday");
				int qna_readnum = rs.getInt("qna_readnum");
				String qna_ip = rs.getString("qna_ip");
				int qna_repRoot = rs.getInt("qna_repRoot");
				int qna_repStep = rs.getInt("qna_repStep");
				int qna_repIndent = rs.getInt("qna_repIndent");

				data.setQna_num(qna_num);
				data.setQna_title(qna_title);
				data.setM_id(m_id);
				data.setQna_passwd(qna_passwd);
				data.setQna_content(qna_content);
				data.setQna_writeday(qna_writeday);
				data.setQna_readnum(qna_readnum);
				data.setQna_ip(qna_ip);
				data.setQna_repRoot(qna_repRoot);
				data.setQna_repStep(qna_repStep);
				data.setQna_repIndent(qna_repIndent);
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

	// ========================= Q&A 게시글 수정 =========================
	public void edit(String _qna_num, String _qna_title, String _m_id, String _qna_passwd, String _qna_content) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "UPDATE qna SET qna_title = ?, qna_content = ? WHERE qna_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _qna_title);
			pstmt.setString(2, _qna_content);
			pstmt.setInt(3, Integer.parseInt(_qna_num));
			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {

			}
		}

	}

	// ========================= Q&A 게시글 삭제 =========================
	public void delete(String _qna_num) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "DELETE FROM qna WHERE qna_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_qna_num));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {

			}
		}
	}

	// ========================= Q&A 답변 게시글 repStep 증가 =========================
	public void makeReply(String _root, String _step) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "UPDATE qna SET qna_repStep = qna_repStep + 1 WHERE qna_repRoot = ? AND qna_repStep > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_root));
			pstmt.setInt(2, Integer.parseInt(_step));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {

			}
		}
	}

	// ========================= Q&A 답변 게시글 작성 =========================
	public void reply(String _qna_num, String _qna_title, String _m_id, String _qna_passwd, String _qna_content,
			String _qna_ip, String _qna_repRoot, String _qna_repStep, String _qna_repIndent) {

		makeReply(_qna_repRoot, _qna_repStep);

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "INSERT INTO qna(qna_title, m_id, qna_passwd, qna_content, qna_ip, qna_repRoot, qna_repStep, qna_repIndent) VALUES (?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _qna_title);
			pstmt.setString(2, _m_id);
			pstmt.setString(3, _qna_passwd);
			pstmt.setString(4, _qna_content);
			pstmt.setString(5, _qna_ip);
			pstmt.setInt(6, Integer.parseInt(_qna_repRoot));
			pstmt.setInt(7, Integer.parseInt(_qna_repStep) + 1);
			pstmt.setInt(8, Integer.parseInt(_qna_repIndent) + 1);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {

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
				String sql = "SELECT count(*) FROM qna";

				if (_method.equals("qna_title")) {
					sql += " WHERE qna_title LIKE ? ORDER BY qna_repRoot DESC, qna_repStep ASC";
				} else if (_method.equals("qna_content")) {
					sql += " WHERE qna_content LIKE ? ORDER BY qna_repRoot DESC, qna_repStep ASC";
				} else {
					sql += " WHERE m_id LIKE ? ORDER BY qna_repRoot DESC, qna_repStep ASC";
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

			ArrayList<QnADTO> list = new ArrayList<QnADTO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = dataFactory.getConnection();
				
				String sql = "SELECT * FROM qna";

				if (_method.equals("qna_title")) {
					sql += " WHERE qna_title LIKE ? ORDER BY qna_repRoot DESC, qna_repStep ASC";
				} else if (_method.equals("qna_content")) {
					sql += " WHERE qna_content LIKE ? ORDER BY qna_repRoot DESC, qna_repStep ASC";
				} else {
					sql += " WHERE m_id LIKE ? ORDER BY qna_repRoot DESC, qna_repStep ASC";
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
					int qna_num = rs.getInt("qna_num");
					String qna_title = rs.getString("qna_title");
					String m_id = rs.getString("m_id");
					String qna_passwd = rs.getString("qna_passwd");
					String qna_content = rs.getString("qna_content");
					String qna_writeday = rs.getString("qna_writeday");
					int qna_readnum = rs.getInt("qna_readnum");
					String qna_ip = rs.getString("qna_ip");
					int qna_repRoot = rs.getInt("qna_repRoot");
					int qna_repStep = rs.getInt("qna_repStep");
					int qna_repIndent = rs.getInt("qna_repIndent");

					QnADTO data = new QnADTO();
					data.setQna_num(qna_num);
					data.setQna_title(qna_title);
					data.setM_id(m_id);
					data.setQna_passwd(qna_passwd);
					data.setQna_content(qna_content);
					data.setQna_writeday(qna_writeday);
					data.setQna_readnum(qna_readnum);
					data.setQna_ip(qna_ip);
					data.setQna_repRoot(qna_repRoot);
					data.setQna_repStep(qna_repStep);
					data.setQna_repIndent(qna_repIndent);

					list.add(data);
				}

				to.setQnaList(list);
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
