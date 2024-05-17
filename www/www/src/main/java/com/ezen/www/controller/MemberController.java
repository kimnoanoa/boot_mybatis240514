package com.ezen.www.controller;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {

    private final MemberService msv;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public void join(){}

    @PostMapping("/register")
    public String register(MemberVO mvo){
        log.info(">>> mvo >>>{}",mvo);
        mvo.setPwd(passwordEncoder.encode(mvo.getPwd()));
        int isOk = msv.insert(mvo);
        return "/index";
    }

    @GetMapping("/login")
    public void login(){}

    @GetMapping("/list")
    public void list(Model m){
        m.addAttribute("list", msv.getList());

    }


    @GetMapping("/modify")
    public void modify(){}

    @PostMapping("/modify")
    public String userModify(MemberVO mvo, HttpServletRequest request, HttpServletResponse response, Principal principal){
        String email = principal.getName();
        mvo.setEmail(email);

        if(mvo.getPwd() == null || mvo.getPwd().length() == 0) {
            msv.updateName(mvo);
        }else {
            String setPwd = passwordEncoder.encode(mvo.getPwd()); //암호화된 비밀번호 만드는 코드 bcencoder
            mvo.setPwd(setPwd);
            msv.updatePwd(mvo);
        }

        logout(request, response);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(Principal principal){
        String email = principal.getName();

        msv.authDelete(email);
        msv.delete(email);

        return "redirect:/member/logout";
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, authentication);
    }





}
