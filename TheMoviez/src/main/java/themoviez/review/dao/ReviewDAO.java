package themoviez.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import themoviez.entity.PageTO;
import themoviez.review.entity.ReviewDTO;

public class ReviewDAO {

	DataSource dataFactory;

	public ReviewDAO() {

		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql8");
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}

	// ========================= 리뷰 리스트 =========================
	public ArrayList<ReviewDTO> reviewList(String _link) {
		ArrayList<ReviewDTO> revList = new ArrayList<ReviewDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT DISTINCT rev.* FROM review rev, movie mov WHERE rev.link = ? ORDER BY rev.rev_num DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _link);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int rev_num = rs.getInt("rev.rev_num");
				String m_id = rs.getString("rev.m_id");
				String rev_content = rs.getString("rev.rev_content");
				String rev_writeday = rs.getString("rev.rev_writeday");
				int rev_like = rs.getInt("rev.rev_like");
				String rev_ip = rs.getString("rev.rev_ip");
				String link = rs.getString("link");

				ReviewDTO data = new ReviewDTO();
				data.setRev_num(rev_num);
				data.setM_id(m_id);
				data.setRev_writeday(rev_writeday);
				data.setRev_content(rev_content);
				data.setRev_like(rev_like);
				data.setRev_ip(rev_ip);
				data.setLink(link);

				revList.add(data);

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

		return revList;

	}

	// ========================= 리뷰 리스트 카운트 =========================
	public int revTotalCount(String _link) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT count(*) FROM review WHERE link = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _link);
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

	// ========================= 리뷰 리스트 페이징 =========================
	public PageTO revListPage(int curPage, String _link) {
		PageTO to = new PageTO();
		int totalCount = revTotalCount(_link);

		ArrayList<ReviewDTO> revList = new ArrayList<ReviewDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT DISTINCT rev.* FROM review rev, movie mov WHERE rev.link = ? ORDER BY rev.rev_num DESC";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, _link);
			rs = pstmt.executeQuery();

			int perPage = to.getPerPage();
			int skip = (curPage - 1) * perPage;
			if (skip > 0) {
				rs.absolute(skip);
			}

			for (int i = 0; i < perPage && rs.next(); i++) {

				int rev_num = rs.getInt("rev.rev_num");

				String m_id = rs.getString("rev.m_id");
				String rev_content = rs.getString("rev.rev_content");
				String rev_writeday = rs.getString("rev.rev_writeday");
				int rev_like = rs.getInt("rev.rev_like");
				String rev_ip = rs.getString("rev.rev_ip");
				String link = rs.getString("rev.link");

				ReviewDTO data = new ReviewDTO();
				data.setRev_num(rev_num);

				data.setM_id(m_id);
				data.setRev_writeday(rev_writeday);
				data.setRev_content(rev_content);
				data.setRev_like(rev_like);
				data.setRev_ip(rev_ip);
				data.setLink(link);

				revList.add(data);

			}

			to.setReviewList(revList);
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

	// ========================= 리뷰 쓰기 =========================
	public void revWrite(String _link, String _m_id, String _rev_content, String _rev_ip) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "INSERT INTO review (m_id, rev_content, rev_ip, link) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _m_id);
			pstmt.setString(2, _rev_content);
			pstmt.setString(3, _rev_ip);
			pstmt.setString(4, _link);
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

	// ========================= 리뷰 삭제 =========================
	public void revDelete(String _rev_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "DELETE FROM review WHERE rev_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_rev_num));
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
