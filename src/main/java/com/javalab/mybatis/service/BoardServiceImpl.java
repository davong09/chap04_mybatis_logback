package com.javalab.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalab.mybatis.mapper.BoardMapperInterface;
import com.javalab.mybatis.vo.BoardVo;

/**
 * 게시물 서비스 클래스
 * 
 * @Service : 나는 서비스 레이어의 스프링 빈 역할을 할 수 있도록 빈으로 생성해라.라는 표시. - root-context.xml 빈
 *          환경설정 파일에 패키지의 위치가 지정되어 있다.
 *          <context:component-scan base-package="com.javalab.mybatis.service">
 */
@Service
public class BoardServiceImpl implements BoardService {

	// 매퍼 인터페이스 의존성 주입
	// 실제로는 매퍼XML을 감싸는 대리인(프로시) 객체가 주입된다.
	@Autowired
	private BoardMapperInterface boardMapper;

	// 게시물 내용 보기
	@Override
	public BoardVo getBoard(int bno) {
		BoardVo boardVo = boardMapper.getBoard(bno);
		return boardVo;
	}

	@Override
	public List<BoardVo> getBoardList() {
		return boardMapper.getBoardList();
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		return boardMapper.insertBoard(boardVo);
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		return boardMapper.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int bno) {
		return boardMapper.deleteBoard(bno);
	}

}
