package com.javalab.mybatis.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalab.mybatis.service.LoginService;
import com.javalab.mybatis.vo.MemberVo;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    // 로그인 폼 제공
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
    	model.addAttribute(new MemberVo());
        return "login/loginForm"; // loginForm.jsp로 이동
    }

    // 로그인 처리
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute MemberVo memberVo, HttpSession session, Model model) {
        MemberVo LoginMemberVo = loginService.login(memberVo);
        if (LoginMemberVo != null) {
            session.setAttribute("memberVo", LoginMemberVo);
            return "redirect:/boardList"; // 게시물 목록 페이지로 리다이렉트
        } else {
            model.addAttribute("error", "아이디와 비밀번호를 확인하세요");
            return "member/loginForm"; // loginForm.jsp로 이동
        }
    }
    
    // 로그아웃 처리
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        // 세션에서 memberVo라는 이름으로 저장된 객체 삭제
        session.removeAttribute("memberVo");
        // 세션 전체를 무효화
        session.invalidate();
        // 로그인 페이지로 리다이렉트
        return "redirect:/login";
    }
}
