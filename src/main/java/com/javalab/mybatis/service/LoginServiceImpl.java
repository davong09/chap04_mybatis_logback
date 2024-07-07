package com.javalab.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalab.mybatis.mapper.LoginMapperInterface;
import com.javalab.mybatis.vo.MemberVo;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
    private LoginMapperInterface loginMapper;

    @Override
    public MemberVo login(MemberVo memberVo) {
        return loginMapper.login(memberVo);
    }
}
