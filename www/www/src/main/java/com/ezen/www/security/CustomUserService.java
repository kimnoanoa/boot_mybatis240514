package com.ezen.www.security;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.repository.MemberMapper;
import com.ezen.www.service.MemberService;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Principal;
import java.util.List;

@Slf4j
public class CustomUserService implements UserDetailsService {

    private MemberService msv;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO loginMvo = memberMapper.selectEmail(username);
        loginMvo.setAuthList(memberMapper.selectAuths(username));
//        if(loginMvo != null){
//            String email = username;
//            msv.updateLastLogin(email);
//        }
        //UserDetails return
        return new AuthMember(loginMvo);
    }
}
