package com.project.metakakao.controller;

import com.project.metakakao.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
//@Autowired대신
@Controller
public class HomeController {
    private final KakaoService kakaoService;
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());

        //getKakaoLogin함수 실행, authorizatio코드 요청
        return "main";//루트 url 에서 main.jsp 리턴
    }
}