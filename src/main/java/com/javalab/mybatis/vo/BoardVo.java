package com.javalab.mybatis.vo;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



/**
 * 게시물 도메인 클래스
 * - Vo 클래스, 도메인 클래스, DTO 클래스, 모델 클래스 4가지 이름
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class BoardVo{
	private int bno;			// 게시물번호	
	private String title;		// 게시물 제목
	private String content;		// 게시물 내용
	private String memberId;	// 게시물 작성자ID
	private int hitNo;			// 조회수
	private Date regDate ;		// 게시물 작성일자
	private String fileName;  	// 첨부 파일 이름 추가[추가됨]

	// 페이징 관련 속성(필드, 멤버변수)
	private String pageNum = "1";		// 요청 페이지번호(기본값을 1)
	private Integer listCount = 10;		// 한 페이지에 보여줄 게시물갯수
	private Integer pagerPerBlock = 10;	// 한 화면에 보여질 페이지 번호 갯수(페이지 블럭)
	// 계층형 답변 게시판을 위한 속성
	private int replyGroup;		// 게시물 그룹
	private int replyOrder;		// 그룹내 정렬순서
	private int replyIndent;	// 들여쓰기
	
	// 필요에 의해서 만든 생성자[게시물 등록]
	public BoardVo(String title, String content, String memberId, String fileName) {
		this.title = title;
		this.content = content;
		this.memberId = memberId;
		this.fileName = fileName;
	}	
	// 필요에 의해서 만든 생성자
	public BoardVo(int bno, String title, String content, String memberId) {
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.memberId = memberId;
	}	
	// 필요에 의해서 만든 생성자
	public BoardVo(int bno, String title, String content, String memberId, String fileName) {
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.memberId = memberId;
		this.fileName = fileName;
	}	
	public BoardVo(int bno, String title, String content) {
		this.bno = bno;
		this.title = title;
		this.content = content;
	}
}
