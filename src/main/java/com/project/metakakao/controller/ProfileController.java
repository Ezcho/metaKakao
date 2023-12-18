package com.project.metakakao.controller;

import com.project.metakakao.dto.KakaoDTO;
import com.project.metakakao.dto.KakaoFriendDTO;
import com.project.metakakao.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profile")
@Log4j2
@RequiredArgsConstructor
public class ProfileController {

    private final KakaoService kakaoService;
    @GetMapping
    public String profile(HttpServletRequest request, Model model) throws Exception {

        String accessToken = (String) request.getSession().getAttribute("accessToken");
        KakaoDTO kakaoDTO = kakaoService.getUserInfoWithTokenPub(accessToken);
        model.addAttribute("user", kakaoDTO);

        System.out.print(kakaoService.getFriendListWithToken(accessToken));
        return "/profile/profile";
    }
}
