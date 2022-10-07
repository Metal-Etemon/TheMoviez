package themoviez.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import themoviez.entity.PageTO;
import themoviez.member.entity.MemberDTO;

public class MemberDAO {

	DataSource dataFactory;

	public MemberDAO() {

		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql8");
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}

	// ========================= 로그인 체크 =========================
	public int loginCheck(String _m_id, String _m_passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO data = new MemberDTO();
		int logincheck = 0;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT m_id, m_passwd FROM member WHERE m_id = ? AND m_passwd = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _m_id);
			pstmt.setString(2, _m_passwd);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String m_id = rs.getString("m_id");
				String m_passwd = rs.getString("m_passwd");

				if (m_id.equals(_m_id) && m_passwd.equals(_m_passwd)) {
					data.setM_id(m_id);
					data.setM_passwd(m_passwd);
					logincheck = 1;
				}
			}

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

		return logincheck;
	}

	// ========================= 로그인 처리 =========================
	public MemberDTO getForSession(String _m_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO data = new MemberDTO();

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT m_num, m_id, m_passwd FROM member WHERE m_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _m_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int m_num = rs.getInt("m_num");
				String m_id = rs.getString("m_id");
				String m_passwd = rs.getString("m_passwd");

				data.setM_num(m_num);
				data.setM_id(m_id);
				data.setM_passwd(m_passwd);
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
	
	
	
	
	// ========================= 아이디 중복 확인 =========================
	public MemberDTO idCheck(String _m_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO data = new MemberDTO();
		
		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT m_id FROM member WHERE m_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _m_id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String m_id = rs.getString("m_id");
				if (_m_id.equals(m_id)) {
					data.setM_id("0");
				} 
			} else {
				data.setM_id(_m_id);
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
	
	

	// ========================= 회원 가입 입력 =========================
	public void regist(String _m_id, String _m_passwd, String _m_name, String _m_gender, String _m_birth, String _m_tel,
			String _m_zipcode, String _m_addr, String _m_addrdetail, String _m_like, String _m_ip) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "INSERT INTO member (m_id, m_passwd, m_name, m_gender, m_birth, m_tel, m_zipcode, m_addr, m_addrdetail, m_like, m_ip) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _m_id);
			pstmt.setString(2, _m_passwd);
			pstmt.setString(3, _m_name);
			pstmt.setString(4, _m_gender);
			pstmt.setString(5, _m_birth);
			pstmt.setString(6, _m_tel);
			pstmt.setString(7, _m_zipcode);
			pstmt.setString(8, _m_addr);
			pstmt.setString(9, _m_addrdetail);
			pstmt.setString(10, _m_like);
			pstmt.setString(11, _m_ip);
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

	// ========================= 회원 리스트 보기 =========================
	public ArrayList<MemberDTO> memberList() {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT * FROM member ORDER BY m_num DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int m_num = rs.getInt("m_num");
				String m_id = rs.getString("m_id");
				String m_passwd = rs.getString("m_passwd");
				String m_name = rs.getString("m_name");
				String m_gender = rs.getString("m_gender");
				String m_birth = rs.getString("m_birth");
				String m_tel = rs.getString("m_tel");
				String m_zipcode = rs.getString("m_zipcode");
				String m_addr = rs.getString("m_addr");
				String m_addrdetail = rs.getString("m_addrdetail");
				String m_like = rs.getString("m_like");
				String m_date = rs.getString("m_date");
				String m_ip = rs.getString("m_ip");

				MemberDTO data = new MemberDTO();
				data.setM_num(m_num);
				data.setM_id(m_id);
				data.setM_passwd(m_passwd);
				data.setM_name(m_name);
				data.setM_gender(m_gender);
				data.setM_birth(m_birth);
				data.setM_tel(m_tel);
				data.setM_zipcode(m_zipcode);
				data.setM_addr(m_addr);
				data.setM_addrdetail(m_addrdetail);
				data.setM_like(m_like);
				data.setM_date(m_date);
				data.setM_ip(m_ip);

				list.add(data);

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

		return list;
	}

	// ========================= 회원 정보 보기 =========================
	public MemberDTO memberInfo(String _m_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO data = new MemberDTO();

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT * FROM member WHERE m_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_m_num));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int m_num = rs.getInt("m_num");
				String m_id = rs.getString("m_id");
				String m_passwd = rs.getString("m_passwd");
				String m_name = rs.getString("m_name");
				String m_gender = rs.getString("m_gender");
				String m_birth = rs.getString("m_birth");
				String m_tel = rs.getString("m_tel");
				String m_zipcode = rs.getString("m_zipcode");
				String m_addr = rs.getString("m_addr");
				String m_addrdetail = rs.getString("m_addrdetail");
				String m_like = rs.getString("m_like");
				String m_date = rs.getString("m_date");
				String m_ip = rs.getString("m_ip");

				data.setM_num(m_num);
				data.setM_id(m_id);
				data.setM_passwd(m_passwd);
				data.setM_name(m_name);
				data.setM_gender(m_gender);
				data.setM_birth(m_birth);
				data.setM_tel(m_tel);
				data.setM_zipcode(m_zipcode);
				data.setM_addr(m_addr);
				data.setM_addrdetail(m_addrdetail);
				data.setM_like(m_like);
				data.setM_date(m_date);
				data.setM_ip(m_ip);
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

	// ========================= 회원 정보 수정 =========================
	public void memberEdit(String _m_num, String _m_name, String _m_gender, String _m_birth, String _m_tel,
			String _m_zipcode, String _m_addr, String _m_addrdetail, String _m_like) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "UPDATE member SET m_name=?, m_gender=?, m_birth=?, m_tel=?, m_zipcode=?, m_addr=?, m_addrdetail=?, m_like=? WHERE m_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _m_name);
			pstmt.setString(2, _m_gender);
			pstmt.setString(3, _m_birth);
			pstmt.setString(4, _m_tel);
			pstmt.setString(5, _m_zipcode);
			pstmt.setString(6, _m_addr);
			pstmt.setString(7, _m_addrdetail);
			pstmt.setString(8, _m_like);
			pstmt.setInt(9, Integer.parseInt(_m_num));
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

	// ========================= 비밀번호 변경 처리 =========================
	public void memberEditPw(String _m_num, String _m_passwd_new) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "UPDATE member SET m_passwd = ? WHERE m_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _m_passwd_new);
			pstmt.setInt(2, Integer.parseInt(_m_num));
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

	// ========================= 회원 탈퇴 처리 =========================
	public void memberDelete(String _m_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "DELETE FROM member WHERE m_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(_m_num));
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

	// ========================= 회원 검색 =========================
	public ArrayList<MemberDTO> memberSearch(String _method, String _keyword) {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT * FROM member";

			if (_method.equals("m_id")) {
				sql += " WHERE m_id LIKE ?";
			} else {
				sql += " WHERE m_name LIKE ?";
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + _keyword + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int m_num = rs.getInt("m_num");
				String m_id = rs.getString("m_id");
				String m_passwd = rs.getString("m_passwd");
				String m_name = rs.getString("m_name");
				String m_gender = rs.getString("m_gender");
				String m_birth = rs.getString("m_birth");
				String m_tel = rs.getString("m_tel");
				String m_zipcode = rs.getString("m_zipcode");
				String m_addr = rs.getString("m_addr");
				String m_addrdetail = rs.getString("m_addrdetail");
				String m_like = rs.getString("m_like");
				String m_date = rs.getString("m_date");
				String m_ip = rs.getString("m_ip");

				MemberDTO data = new MemberDTO();
				data.setM_num(m_num);
				data.setM_id(m_id);
				data.setM_passwd(m_passwd);
				data.setM_name(m_name);
				data.setM_gender(m_gender);
				data.setM_birth(m_birth);
				data.setM_tel(m_tel);
				data.setM_zipcode(m_zipcode);
				data.setM_addr(m_addr);
				data.setM_addrdetail(m_addrdetail);
				data.setM_like(m_like);
				data.setM_date(m_date);
				data.setM_ip(m_ip);

				list.add(data);
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

		return list;
	}

	
	
	// 페이징 //
	// ========================= 전체 테이블 컬럼 카운트 =========================
	public int totalCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

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

		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT * FROM member ORDER BY m_num DESC";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();

			int perPage = to.getPerPage();
			int skip = (curPage - 1) * perPage;
			if (skip > 0) {
				rs.absolute(skip);
			}

			for (int i = 0; i < perPage && rs.next(); i++) {
				int m_num = rs.getInt("m_num");
				String m_id = rs.getString("m_id");
				String m_passwd = rs.getString("m_passwd");
				String m_name = rs.getString("m_name");
				String m_gender = rs.getString("m_gender");
				String m_birth = rs.getString("m_birth");
				String m_tel = rs.getString("m_tel");
				String m_zipcode = rs.getString("m_zipcode");
				String m_addr = rs.getString("m_addr");
				String m_addrdetail = rs.getString("m_addrdetail");
				String m_like = rs.getString("m_like");
				String m_date = rs.getString("m_date");
				String m_ip = rs.getString("m_ip");

				MemberDTO data = new MemberDTO();
				data.setM_num(m_num);
				data.setM_id(m_id);
				data.setM_passwd(m_passwd);
				data.setM_name(m_name);
				data.setM_gender(m_gender);
				data.setM_birth(m_birth);
				data.setM_tel(m_tel);
				data.setM_zipcode(m_zipcode);
				data.setM_addr(m_addr);
				data.setM_addrdetail(m_addrdetail);
				data.setM_like(m_like);
				data.setM_date(m_date);
				data.setM_ip(m_ip);

				list.add(data);
			}
			
			to.setMemberList(list);
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
	
	
	

	// ========================= 검색 테이블 컬럼 카운트 =========================
	public int searchCount(String _method, String _keyword) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT count(*) FROM member";

			if (_method.equals("m_id")) {
				sql += " WHERE m_id LIKE ?";
			} else {
				sql += " WHERE m_name LIKE ?";
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
	public PageTO searchPage(int curPage, String _method, String _keyword) {
		PageTO to = new PageTO();
		int searchCount = searchCount(_method, _keyword);

		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT * FROM member";

			if (_method.equals("m_id")) {
				sql += " WHERE m_id LIKE ? ORDER BY m_num DESC";
			} else {
				sql += " WHERE m_name LIKE ? ORDER BY m_num DESC";
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
				int m_num = rs.getInt("m_num");
				String m_id = rs.getString("m_id");
				String m_passwd = rs.getString("m_passwd");
				String m_name = rs.getString("m_name");
				String m_gender = rs.getString("m_gender");
				String m_birth = rs.getString("m_birth");
				String m_tel = rs.getString("m_tel");
				String m_zipcode = rs.getString("m_zipcode");
				String m_addr = rs.getString("m_addr");
				String m_addrdetail = rs.getString("m_addrdetail");
				String m_like = rs.getString("m_like");
				String m_date = rs.getString("m_date");
				String m_ip = rs.getString("m_ip");

				MemberDTO data = new MemberDTO();
				data.setM_num(m_num);
				data.setM_id(m_id);
				data.setM_passwd(m_passwd);
				data.setM_name(m_name);
				data.setM_gender(m_gender);
				data.setM_birth(m_birth);
				data.setM_tel(m_tel);
				data.setM_zipcode(m_zipcode);
				data.setM_addr(m_addr);
				data.setM_addrdetail(m_addrdetail);
				data.setM_like(m_like);
				data.setM_date(m_date);
				data.setM_ip(m_ip);

				list.add(data);
			}
			
			to.setMemberList(list);
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
