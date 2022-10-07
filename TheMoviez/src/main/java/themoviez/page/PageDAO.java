package themoviez.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import themoviez.member.entity.MemberDTO;

public class PageDAO {
	
	DataSource dataFactory;

	public PageDAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql8");
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}
	
		// ========================= 테이블 컬럼 카운트 =========================
		public int totalCount() {
			int count = 0;
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			
			try {
				conn = dataFactory.getConnection();
				String sql = "SELECT count(*) FROM member";
				pstmt = conn.prepareStatement(sql);
		
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					count = rs.getInt("count(*)");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
			return count;
		}
		
	
		// ========================= 페이지 구현 =========================
		public PageVO page(int curPage) {
			PageVO pageVO = new PageVO();
			int totalCount = totalCount();
			int limit = curPage * 10;
//			if ((totalCount % 10) != 0) {
//				curPage = (totalCount / 10) * 10 + 1;
//			} else {
//				curPage = (totalCount / 10) * 10;
//			}
			
			ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			
			try {
				conn = dataFactory.getConnection();
				String sql = "SELECT * FROM member ORDER BY m_num DESC LIMIT ?, 10";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, limit);
				rs = pstmt.executeQuery();
				
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
			return pageVO;
		}
		
		

		
}
