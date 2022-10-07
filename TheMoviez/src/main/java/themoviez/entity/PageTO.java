package themoviez.entity;

import java.util.ArrayList;

import themoviez.comment.entity.CommentDTO;
import themoviez.community.entity.CommunityDTO;
import themoviez.member.entity.MemberDTO;
import themoviez.newsletter.entity.NewsletterDTO;
import themoviez.qna.entity.QnADTO;
import themoviez.review.entity.ReviewDTO;

public class PageTO {

//	ArrayList<NewsletterDTO> newsletterList;
	int curPage;
	int perPage = 10;
	int totalCount;

	ArrayList<MemberDTO> memberList;
	ArrayList<NewsletterDTO> newsletterList;
	ArrayList<CommunityDTO> communityList;
	ArrayList<CommentDTO> commentList;
	ArrayList<QnADTO> qnaList;
	ArrayList<ReviewDTO> reviewList;

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public ArrayList<MemberDTO> getMemberList() {
		return memberList;
	}

	public void setMemberList(ArrayList<MemberDTO> memberList) {
		this.memberList = memberList;
	}

	public ArrayList<NewsletterDTO> getNewsletterList() {
		return newsletterList;
	}

	public void setNewsletterList(ArrayList<NewsletterDTO> newsletterList) {
		this.newsletterList = newsletterList;
	}

	public ArrayList<CommunityDTO> getCommunityList() {
		return communityList;
	}

	public void setCommunityList(ArrayList<CommunityDTO> communityList) {
		this.communityList = communityList;
	}

	public ArrayList<CommentDTO> getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList<CommentDTO> commentList) {
		this.commentList = commentList;
	}

	public ArrayList<QnADTO> getQnaList() {
		return qnaList;
	}

	public void setQnaList(ArrayList<QnADTO> qnaList) {
		this.qnaList = qnaList;
	}

	public ArrayList<ReviewDTO> getReviewList() {
		return reviewList;
	}

	public void setReviewList(ArrayList<ReviewDTO> reviewList) {
		this.reviewList = reviewList;
	}
	
	

}
