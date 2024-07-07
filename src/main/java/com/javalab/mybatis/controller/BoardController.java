package com.javalab.mybatis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalab.mybatis.service.BoardService;
import com.javalab.mybatis.vo.BoardVo;
import com.javalab.mybatis.vo.MemberVo;

import lombok.extern.slf4j.Slf4j;

/**
 * Board 컨트롤러 레이어
 */
@Controller
@Slf4j
public class BoardController {

	// Board 서비스 레이어 의존성 주입
	@Autowired
	private BoardService boardService;

	// 게시물 내용 보기 메소드
	// @RequestParam("bno") : 클라이언트에서 ?bno=1 과 같이 전달되는 파라미터를 추출한다.
	@RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
	public String getBoard(@RequestParam("bno") int bno, Model model) {

		log.info("BoardController getBoard");

		BoardVo boardVo = boardService.getBoard(bno);
		model.addAttribute("boardVo", boardVo);
		return "board/boardDetail"; // jsp 이름
	}

	// 게시물 목록 보기 메소드
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String getBoardList(Model model) {
		log.info("여기는 getBoardList 메소드");
		List<BoardVo> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		return "board/boardList"; // jsp 이름
	}

	/**
	 * 게시물 등록폼 제공 메소드
	 * 
	 * @ModelAttribute : 사용자의 입력에 오류가 있을 경우 기존의 내용을 그대로 화면으로 다시 전달해준다.
	 */
	@RequestMapping(value = "/boardInsertForm", method = RequestMethod.GET)
	public String insertBoardForm(Model model) {
		model.addAttribute(new BoardVo());
		return "board/boardInsertForm"; // 게시물 등록폼으로 이동
	}

	/**
	 * 게시물 등록 메소드
	 * 
	 * @ModelAttribute : 사용자의 입력에 오류가 있을 경우 기존의 내용을 그대로 화면으로 다시 전달해준다.
	 */
	@RequestMapping(value = "/insertBoard", method = RequestMethod.POST)
	public String insertBoard(@ModelAttribute BoardVo boardVo,
											HttpSession session,
											HttpServletRequest request,
											Model model) {

		// 로그인 여부 확인
		MemberVo memberVO = (MemberVo) session.getAttribute("memberVo");
		if (memberVO == null) {
			return "redirect:/login";
		}
		boardVo.setMemberId(memberVO.getMemberId());

		log.info("boardVo : " + boardVo);

		boardService.insertBoard(boardVo);
		return "redirect:/boardList"; // 목록으로 이동
	}

	/**
	 * 게시물 수정폼 제공 메소드
	 * 
	 * @ModelAttribute : 사용자의 입력에 오류가 있을 경우 기존의 내용을 그대로 화면으로 다시 전달해준다.
	 */
	@RequestMapping(value = "/boardUpdateForm", method = RequestMethod.GET)
	public String boardUpdateForm(@RequestParam("bno") int bno, Model model) {
		log.info("여기는 boardUpdateForm 메소드");
		model.addAttribute(new BoardVo());
		return "board/boardUpdateForm"; // 게시물 수정폼으로 이동
	}

	// 게시물 수정 메소드
	@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
	public String updateBoard(@ModelAttribute BoardVo boardVo,
									HttpSession session,
									HttpServletRequest request,
									Model model) {
		log.info("여기는 updateBoard 메소드");

		// 로그인 여부 확인
		MemberVo memberVO = (MemberVo) session.getAttribute("memberVo");
		if (memberVO == null) {
			return "redirect:/login";
		}
		boardVo.setMemberId(memberVO.getMemberId());

		log.info("boardVo : " + boardVo);

		boardService.updateBoard(boardVo);
		return "redirect:/boardList"; // 목록으로 이동
	}

	// 게시물 삭제 메소드
	@RequestMapping(value = "/deleteBoard", method = RequestMethod.POST)
	public String deleteBoard(@RequestParam("bno") int bno) {
		boardService.deleteBoard(bno);
		return "redirect:/boardList"; // 목록으로 이동
	}
}
