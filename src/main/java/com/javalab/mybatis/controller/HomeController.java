package com.javalab.mybatis.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalab.mybatis.service.BoardService;
import com.javalab.mybatis.vo.BoardVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 컨트롤러 레이어
 */
@Controller
@Slf4j
public class HomeController {
	
	// Board 서비스 레이어 의존성 주입
	@Autowired
	private BoardService boardService;
	
	/**
	 * 앱이 처음 구동될 때 호출되는 핸들러(메소드)
	 * - localhost:8080/컨텍스트패스/
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "index";	// jsp 페이지 이름
	}	

}
