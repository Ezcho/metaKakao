package com.project.metakakao.controller;

import com.project.metakakao.dto.KakaoDTO;
import com.project.metakakao.entity.Member;
import com.project.metakakao.entity.Question;
import com.project.metakakao.service.KakaoService;
import com.project.metakakao.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private KakaoService kakaoService;
    @GetMapping("/member/{memberId}")
    public String getMemberProfile(@PathVariable int memberId, HttpServletRequest request, Model model) throws Exception {
        System.out.println("getMemberProfile");
        Member member = memberService.getMemberById(memberId); // Member ID를 사용하여 사용자 정보 조회
        HttpSession session = request.getSession();
        String accessToken = (String) session.getAttribute("accessToken");
        KakaoDTO kakaoDTO = kakaoService.getUserInfoWithTokenPub(accessToken);
        List<Question> questionList = member.getQuestionList();
        System.out.print("QuestionList: "+questionList);
        model.addAttribute("user", member); //페이지 주인
        model.addAttribute("kakaoDTO", kakaoDTO);   //현재 접속한 유저
        model.addAttribute("questionList", questionList);   //페이지 주인의록질문목
        return "question/question";
    }
}
