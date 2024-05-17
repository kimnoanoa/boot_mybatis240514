package com.ezen.www.service;

import com.ezen.www.domain.MemberVO;

import java.util.List;

public interface MemberService {
    int insert(MemberVO mvo);

    List<MemberVO> getList();

    void updateName(MemberVO mvo);

    void updatePwd(MemberVO mvo);

    void authDelete(String email);

    void delete(String email);

    void updateLastLogin(String email);
}
