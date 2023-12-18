package com.project.metakakao.controller;

import com.project.metakakao.entity.Member;
import com.project.metakakao.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("kakao")
public class KakaoController {
    //로그인 페이지가 뜨고, 로그인을 하면 kakao/redirect와 &code=xxx 라는 파라미터를 보내줌,
    private final KakaoService kakaoService;
    //다시 서비스를 호출하고 path가 /redirect인넘을 만나면 컨트롤러 실행
    @GetMapping("/redirect")
    public String callback(HttpServletRequest request) throws Exception {
        //HTTP 통신을 하기에 redirect uri value도 http로 설정,
        System.out.println("auth code: "+ request.getParameter("code"));//authorization 코드 뜬거 한번 출력
        Member member = kakaoService.getKakaoInfo(request.getParameter("code"), request);
        return "redirect:/member/" + member.getId();
        //메시지 엔티티는 사용하길래 해봄
    }
}