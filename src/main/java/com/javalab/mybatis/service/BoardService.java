package com.javalab.mybatis.service;

import java.util.List;

import com.javalab.mybatis.vo.BoardVo;

/**
 * BoardService 인터페이스
 *
 */
public interface BoardService {
	// 게시물 상세 보기 추상메소드
	public BoardVo getBoard(int bno);

	// 게시물 목록 보기 추상메소드
	public List<BoardVo> getBoardList();

	// 게시물 등록 추상메소드
	public int insertBoard(BoardVo boardVo);

	// 게시물 업데이트 메소드
	public int updateBoard(BoardVo boardVo);

	// 게시물 삭제 메소드
	public int deleteBoard(int bno);
}
