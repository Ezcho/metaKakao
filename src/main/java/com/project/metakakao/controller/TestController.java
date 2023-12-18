package com.project.metakakao.controller;

import com.project.metakakao.dto.KakaoFriendDTO;
import com.project.metakakao.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class TestController {
    private final KakaoService kakaoService;

    @GetMapping("/list")
    public List<KakaoFriendDTO> getList(HttpServletRequest request) throws ParseException {
        String accessToken = (String) request.getSession().getAttribute("accessToken");
        return kakaoService.getFriendListWithToken(accessToken);
    }

    //InsufficientScopeException 처리
    @ExceptionHandler(KakaoService.InsufficientScopeException.class)
    public ModelAndView handleInsufficientScopeException(KakaoService.InsufficientScopeException e) {
        //추가 동의 URL로 리다이렉션
        ModelAndView modelAndView = new ModelAndView("redirect:" + e.getConsentUrl());
        return modelAndView;
    }
}
