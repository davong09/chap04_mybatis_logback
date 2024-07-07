package com.javalab.mybatis.service;

import com.javalab.mybatis.vo.MemberVo;

public interface LoginService {
	MemberVo login(MemberVo memberVo);
}
