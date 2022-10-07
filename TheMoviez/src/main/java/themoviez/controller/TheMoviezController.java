package themoviez.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import themoviez.comment.service.ComCommentDeleteCommand;
import themoviez.comment.service.ComCommentListPageCommand;
import themoviez.comment.service.ComCommentWriteCommand;
import themoviez.community.service.CommunityContentCommand;
import themoviez.community.service.CommunityDeleteCommand;
import themoviez.community.service.CommunityEditCommand;
import themoviez.community.service.CommunityListPageCommand;
import themoviez.community.service.CommunityReadnumCommand;
import themoviez.community.service.CommunitySearchCommand;
import themoviez.community.service.CommunityWriteCommand;
import themoviez.member.dao.MemberDAO;
import themoviez.member.entity.MemberDTO;
import themoviez.member.service.MemberDeleteCommand;
import themoviez.member.service.MemberEditCommand;
import themoviez.member.service.MemberEditPwCommand;
import themoviez.member.service.MemberIdCheckCommand;
import themoviez.member.service.MemberInfoCommand;
import themoviez.member.service.MemberListPageCommand;
import themoviez.member.service.MemberRegistCommand;
import themoviez.member.service.MemberSearchCommand;
import themoviez.movie.service.MovieInsertCommand;
import themoviez.movie.service.MovieSearchCommand;
import themoviez.newsletter.service.NewsletterContentCommand;
import themoviez.newsletter.service.NewsletterDeleteCommand;
import themoviez.newsletter.service.NewsletterEditCommand;
import themoviez.newsletter.service.NewsletterListPageCommand;
import themoviez.newsletter.service.NewsletterReadnumCommand;
import themoviez.newsletter.service.NewsletterSearchCommand;
import themoviez.newsletter.service.NewsletterWriteCommand;
import themoviez.qna.service.QnAContentCommand;
import themoviez.qna.service.QnADeleteCommand;
import themoviez.qna.service.QnAEditCommand;
import themoviez.qna.service.QnAListPageCommand;
import themoviez.qna.service.QnAReadnumCommand;
import themoviez.qna.service.QnAReplyCommand;
import themoviez.qna.service.QnASearchCommand;
import themoviez.qna.service.QnAWriteCommand;
import themoviez.review.service.ReviewDeleteCommand;
import themoviez.review.service.ReviewListPageCommand;
import themoviez.review.service.ReviewWriteCommand;
import themoviez.service.TheMoviezCommand;

@WebServlet("*.do")
public class TheMoviezController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String com = requestURI.substring(contextPath.length());

		PrintWriter out = resp.getWriter();

		TheMoviezCommand command = null;
		String nextPage = null;
		HttpSession session = req.getSession();

		String m_id = "";
		String m_passwd = "";

		// 로그인
		if (com.equals("/login/login.do")) {
			m_id = req.getParameter("m_id");
			m_passwd = req.getParameter("m_passwd");
			MemberDAO dao = new MemberDAO();
			int logincheck = dao.loginCheck(m_id, m_passwd);

			if (logincheck == 1) {
				MemberDTO data = dao.getForSession(m_id);
				session.setAttribute("m_id", m_id);
				session.setAttribute("m_passwd", m_passwd);
				session.setAttribute("m_num", data.getM_num());

				nextPage = "/index.jsp";
			} else {
				out.println("<script>alert('아이디와 비밀번호를 확인해 주세요!');history.back();</script>");
			}

		}

		// 세션값 불러오기
		if (session.getAttribute("m_id") != null) {
			m_id = (String) session.getAttribute("m_id");
			m_passwd = (String) session.getAttribute("m_passwd");
		} else {
			m_id = "";
			m_passwd = "";

		}

		// 로그아웃
		if (com.equals("/login/logout.do")) {
			session.invalidate();
			nextPage = "/index.jsp";
		}

		// 회원가입 폼
		if (com.equals("/member/registui.do")) {
			nextPage = "/member/terms.jsp";
		}

		// 아이디 중복 확인
		if (com.equals("/member/idCheck.do")) {
			command = new MemberIdCheckCommand();
			command.execute(req, resp);
			nextPage = "/member/idCheck.jsp";
		}

		// 회원가입 처리
		if (com.equals("/member/regist.do")) {
			command = new MemberRegistCommand();
			command.execute(req, resp);
			nextPage = "/index.jsp";
		}

		// 회원 목록
//		if (com.equals("/member/memberList.do") || com.equals("/member/memberlist.do")) {
//			command = new MemberListCommand();
//			command.execute(req, resp);
//			nextPage = "/member/memberList.jsp";
//		}

		// 회원 목록 페이징
		if (com.equals("/member/memberList.do") || com.equals("/member/memberlist.do")) {
			if (m_id.equals("admin")) {
				command = new MemberListPageCommand();
				command.execute(req, resp);
				nextPage = "/member/memberListPage.jsp";
			} else {
				out.println("<script>alert('관리자 로그인 필요!');location.href='/TheMoviez/'</script>");
			}

		}

		// 회원 목록 검색 페이징
		if (com.equals("/member/memberSearch.do") || com.equals("/member/membersearch.do")) {
			if (m_id.equals("admin")) {
				command = new MemberSearchCommand();
				command.execute(req, resp);
				nextPage = "/member/memberSearchPage.jsp";
			} else {
				out.println("<script>alert('관리자 로그인 필요!');location.href='/TheMoviez/'</script>");
			}
		}

		// 회원 정보
		if (com.equals("/member/memberInfo.do") || com.equals("/member/memberinfo.do")) {
			if (!m_id.equals("")) {
				command = new MemberInfoCommand();
				command.execute(req, resp);
				nextPage = "/member/memberInfo.jsp";
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');location.href='/TheMoviez/'</script>");
			}
		}

		// 회원 정보 수정 폼
		if (com.equals("/member/memberEditUi.do") || com.equals("/member/membereditui.do")) {
			if (!m_id.equals("")) {
				command = new MemberInfoCommand();
				command.execute(req, resp);
				nextPage = "/member/memberEdit.jsp";
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');location.href='/TheMoviez/'</script>");
			}
		}

		// 회원 정보 수정 처리
		if (com.equals("/member/memberEdit.do") || com.equals("/member/memberedit.do")) {
			if (!m_id.equals("")) {
				command = new MemberEditCommand();
				command.execute(req, resp);
				String m_num = req.getParameter("m_num");
				nextPage = "/member/memberInfo.do?m_num=" + m_num;
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');location.href='/TheMoviez/'</script>");
			}
		}

		// 비밀번호 변경 폼
		if (com.equals("/member/memberEditPwUi.do") || com.equals("/member/membereditpwui.do")) {
			if (!m_id.equals("")) {
				command = new MemberInfoCommand();
				command.execute(req, resp);
				nextPage = "/member/memberEditPw.jsp";
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');location.href='/TheMoviez/'</script>");
			}	
		}

		// 비밀번호 변경 처리
		if (com.equals("/member/memberEditPw.do") || com.equals("/member/membereditpw.do")) {
			if (!m_id.equals("")) {
				command = new MemberEditPwCommand();
				command.execute(req, resp);
				String m_num = req.getParameter("m_num");
				nextPage = "/member/memberInfo.do?m_num=" + m_num;
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');location.href='/TheMoviez/'</script>");
			}	
		}

		// 회원 탈퇴 확인 폼
		if (com.equals("/member/memberDeleteUi.do") || com.equals("/member/memberdeleteui.do")) {
			if (!m_id.equals("")) {
				command = new MemberInfoCommand();
				command.execute(req, resp);
				nextPage = "/member/memberDelete.jsp";
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');location.href='/TheMoviez/'</script>");
			}	
		}

		if (com.equals("/member/memberDelete.do") || com.equals("/member/memberdelete.do")) {
			command = new MemberDeleteCommand();
			command.execute(req, resp);
			if (m_id.equals("admin")) {
				nextPage = "memberList.do";
			} else {
				session.invalidate();
				nextPage = "/index.jsp";
			}
		}

		// 영화 검색
		if (com.equals("/movieSearch.do") || com.equals("/login/movieSearch.do") || com.equals("/movie/movieSearch.do")
				|| com.equals("/member/movieSearch.do") || com.equals("/newsletter/movieSearch.do")
				|| com.equals("/community/movieSearch.do") || com.equals("/qna/movieSearch.do")
				|| com.equals("/event/movieSearch.do")) {
			command = new MovieSearchCommand();
			command.execute(req, resp);
			nextPage = "/movie/movieResult.jsp";
		}

		if (com.equals("/movieInsert.do") || com.equals("/login/movieInsert.do") || com.equals("/movie/movieInsert.do")
				|| com.equals("/member/movieInsert.do") || com.equals("/newsletter/movieInsert.do")
				|| com.equals("/community/movieInsert.do") || com.equals("/qna/movieInsert.do")
				|| com.equals("/event/movieInsert.do")) {
			command = new MovieInsertCommand();
			command.execute(req, resp);
			nextPage = "/movie/movieReview.do";
		}

		if (com.equals("/movieReview.do") || com.equals("/login/movieReview.do") || com.equals("/movie/movieReview.do")
				|| com.equals("/member/movieReview.do") || com.equals("/newsletter/movieReview.do")
				|| com.equals("/community/movieReview.do") || com.equals("/qna/movieReview.do")
				|| com.equals("/event/movieReview.do")) {
			command = new ReviewListPageCommand();
			command.execute(req, resp);
			nextPage = "/movie/movieReview.jsp";
		}

		if (com.equals("/revWrite.do") || com.equals("/login/revWrite.do") || com.equals("/movie/revWrite.do")
				|| com.equals("/member/revWrite.do") || com.equals("/newsletter/revWrite.do")
				|| com.equals("/community/revWrite.do") || com.equals("/qna/revWrite.do")
				|| com.equals("/event/revWrite.do")) {
			command = new ReviewWriteCommand();
			command.execute(req, resp);
			String link = req.getParameter("link");
			nextPage = "/movie/movieReview.do?link=" + link;

		}

		if (com.equals("/revDelete.do") || com.equals("/login/revDelete.do") || com.equals("/movie/revDelete.do")
				|| com.equals("/member/revDelete.do") || com.equals("/newsletter/revDelete.do")
				|| com.equals("/community/revDelete.do") || com.equals("/qna/revDelete.do")
				|| com.equals("/event/revDelete.do")) {
			command = new ReviewDeleteCommand();
			command.execute(req, resp);
			String link = req.getParameter("link");
			nextPage = "/movie/movieReview.do?link=" + link;

		}

// ==============================================================================================

		// 커뮤니티 게시판 목록 페이징
		if (com.equals("/community/list.do")) {
			command = new CommunityListPageCommand();
			command.execute(req, resp);
			nextPage = "/community/list.jsp";
		}

		// 커뮤니티 게시판 목록 검색 페이징
		if (com.equals("/community/search.do")) {
			command = new CommunitySearchCommand();
			command.execute(req, resp);
			nextPage = "/community/searchList.jsp";
		}

		// 커뮤니티 게시판 글쓰기
		if (com.equals("/community/writeui.do")) {
			if (!m_id.equals("")) {
				nextPage = "/community/write.jsp";
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');history.back();</script>");
			}
		}
		if (com.equals("/community/write.do")) {
			if (!m_id.equals("")) {
				command = new CommunityWriteCommand();
				command.execute(req, resp);
				nextPage = "/community/list.do";
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');history.back();</script>");
			}
		}

		// 커뮤니티 게시판 글 보기
//		if (com.equals("/community/readnum.do")) {
//			command = new CommunityReadnumCommand();
//			command.execute(req, resp);
//			nextPage = "/community/content.do";
//		}
//		if (com.equals("/community/content.do")) {
//			command = new CommunityContentCommand();
//			command.execute(req, resp);
//			nextPage = "/community/content.jsp";
//		}

		// 커뮤니티 게시판 글 삭제
		if (com.equals("/community/delete.do")) {
			if (!m_id.equals("")) {
				command = new CommunityDeleteCommand();
				command.execute(req, resp);
				nextPage = "/community/list.do";
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');history.back();</script>");
			}
		}

		// 커뮤니티 게시판 글 수정
		if (com.equals("/community/editui.do")) {
			if (!m_id.equals("")) {
				command = new CommunityContentCommand();
				command.execute(req, resp);
				nextPage = "/community/edit.jsp";
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');history.back();</script>");
			}
		}
		if (com.equals("/community/edit.do")) {
			if (!m_id.equals("")) {
				command = new CommunityEditCommand();
				command.execute(req, resp);
				String com_num = req.getParameter("com_num");
				nextPage = "/community/content.do?com_num=" + com_num;
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');history.back();</script>");
			}
		}

		// 커뮤니티 게시판 댓글 리스트
//		if (com.equals("/community/readnum.do")) {
//			command = new CommunityReadnumCommand();
//			command.execute(req, resp);
//			nextPage = "/community/content.do";
//		}
//		if (com.equals("/community/content.do")) {
//			command = new ComCommentListCommand();
//			command.execute(req, resp);
//			nextPage = "/community/contentComment.jsp";
//		}

		// 커뮤니티 게시판 댓글 페이징
		if (com.equals("/community/readnum.do")) {
			command = new CommunityReadnumCommand();
			command.execute(req, resp);
			nextPage = "/community/content.do";
		}
		if (com.equals("/community/content.do")) {
			command = new ComCommentListPageCommand();
			command.execute(req, resp);
			nextPage = "/community/contentComment.jsp";
		}

		// 커뮤니티 게시판 댓글 작성
		if (com.equals("/community/cmtWrite.do")) {
			if (!m_id.equals("")) {
				command = new ComCommentWriteCommand();
				String com_num = req.getParameter("com_num");
				command.execute(req, resp);
				nextPage = "/community/content.do?com_num=" + com_num;
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');history.back();</script>");
			}
		}

		// 커뮤니티 게시판 댓글 삭제
		if (com.equals("/community/cmtDelete.do")) {
			if (!m_id.equals("")) {
				command = new ComCommentDeleteCommand();
				String com_num = req.getParameter("com_num");
				command.execute(req, resp);
				nextPage = "/community/content.do?com_num=" + com_num;
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');history.back();</script>");
			}
		}

// ==============================================================================================

		// Q&A 게시판 목록 페이징
		if (com.equals("/qna/list.do")) {
			command = new QnAListPageCommand();
			command.execute(req, resp);
			nextPage = "/qna/list.jsp";
		}

		// Q&A 게시판 목록 검색 페이징
		if (com.equals("/qna/search.do")) {
			command = new QnASearchCommand();
			command.execute(req, resp);
			nextPage = "/qna/searchList.jsp";
		}

		// Q&A 게시판 글쓰기
		if (com.equals("/qna/writeui.do")) {
			if (!m_id.equals("")) {
				nextPage = "/qna/write.jsp";
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');history.back();</script>");
			}
		}
		if (com.equals("/qna/write.do")) {
			if (!m_id.equals("")) {
				command = new QnAWriteCommand();
				command.execute(req, resp);
				nextPage = "/qna/list.do";
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');history.back();</script>");
			}
		}

		// Q&A 게시판 글 보기
		if (com.equals("/qna/readnum.do")) {
			command = new QnAReadnumCommand();
			command.execute(req, resp);
			nextPage = "/qna/content.do";
		}
		if (com.equals("/qna/content.do")) {
			command = new QnAContentCommand();
			command.execute(req, resp);
			nextPage = "/qna/content.jsp";
		}

		// Q&A 게시판 글 삭제
		if (com.equals("/qna/delete.do")) {
			if (!m_id.equals("")) {
				command = new QnADeleteCommand();
				command.execute(req, resp);
				nextPage = "/qna/list.do";
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');history.back();</script>");
			}
		}

		// Q&A 게시판 글 수정
		if (com.equals("/qna/editui.do")) {
			if (!m_id.equals("")) {
				command = new QnAContentCommand();
				command.execute(req, resp);
				nextPage = "/qna/edit.jsp";
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');history.back();</script>");
			}
		}
		if (com.equals("/qna/edit.do")) {
			if (!m_id.equals("")) {
				command = new QnAEditCommand();
				command.execute(req, resp);
				String com_num = req.getParameter("com_num");
				nextPage = "/qna/content.do?com_num=" + com_num;
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');history.back();</script>");
			}
		}

		// Q&A 게시판 답글 폼
		if (com.equals("/qna/replyui.do")) {
			if (!m_id.equals("")) {
				command = new QnAContentCommand();
				command.execute(req, resp);
				nextPage = "/qna/reply.jsp";
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');history.back();</script>");
			}
		}

		// Q&A 게시판 답글 달기
		if (com.equals("/qna/reply.do")) {
			if (!m_id.equals("")) {
				command = new QnAReplyCommand();
				command.execute(req, resp);
				nextPage = "/qna/list.do";
			} else {
				out.println("<script>alert('로그인 후 이용 가능합니다.');history.back();</script>");
			}
		}

// ==============================================================================================

		// 공지사항 게시판 목록 페이징
		if (com.equals("/newsletter/list.do")) {
			command = new NewsletterListPageCommand();
			command.execute(req, resp);
			nextPage = "/newsletter/list.jsp";
		}

		// 공지사항 게시판 목록 검색 페이징
		if (com.equals("/newsletter/search.do")) {
			command = new NewsletterSearchCommand();
			command.execute(req, resp);
			nextPage = "/newsletter/searchList.jsp";
		}

		// 공지사항 게시판 글쓰기
		if (com.equals("/newsletter/writeui.do")) {
			if (m_id.equals("admin")) {
				nextPage = "/newsletter/write.jsp";
			} else {
				out.println("<script>alert('관리자 로그인 필요!');location.href='/TheMoviez/'</script>");
			}
		}
		if (com.equals("/newsletter/write.do")) {
			if (m_id.equals("admin")) {
				command = new NewsletterWriteCommand();
				command.execute(req, resp);
				nextPage = "/newsletter/list.do";
			} else {
				out.println("<script>alert('관리자 로그인 필요!');location.href='/TheMoviez/'</script>");
			}

		}

		// 공지사항 게시판 글 보기
		if (com.equals("/newsletter/readnum.do")) {
			command = new NewsletterReadnumCommand();
			command.execute(req, resp);
			nextPage = "/newsletter/content.do";
		}
		if (com.equals("/newsletter/content.do")) {
			command = new NewsletterContentCommand();
			command.execute(req, resp);
			nextPage = "/newsletter/content.jsp";
		}

		// 공지사항 게시판 글 삭제
		if (com.equals("/newsletter/delete.do")) {
			if (m_id.equals("admin")) {
				command = new NewsletterDeleteCommand();
				command.execute(req, resp);
				nextPage = "/newsletter/list.do";
			} else {
				out.println("<script>alert('관리자 로그인 필요!');location.href='/TheMoviez/'</script>");
			}

		}

		// 공지사항 게시판 글 수정
		if (com.equals("/newsletter/editui.do")) {
			if (m_id.equals("admin")) {
				command = new NewsletterContentCommand();
				command.execute(req, resp);
				nextPage = "/newsletter/edit.jsp";
			} else {
				out.println("<script>alert('관리자 로그인 필요!');location.href='/TheMoviez/'</script>");
			}

		}
		if (com.equals("/newsletter/edit.do")) {
			if (m_id.equals("admin")) {
				command = new NewsletterEditCommand();
				command.execute(req, resp);
				String news_num = req.getParameter("news_num");
				nextPage = "/newsletter/content.do?com_num=" + news_num;
			} else {
				out.println("<script>alert('관리자 로그인 필요!');location.href='/TheMoviez/'</script>");
			}
		}

// ==============================================================================================

		RequestDispatcher dis = req.getRequestDispatcher(nextPage);
		dis.forward(req, resp);
	}

}
