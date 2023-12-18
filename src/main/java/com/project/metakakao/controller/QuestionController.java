package com.project.metakakao.controller;

import com.project.metakakao.dto.QuestionDTO;
import com.project.metakakao.service.AnswerService;
import com.project.metakakao.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService; // QuestionService는 의존성 주입 필요
    @Autowired
    private AnswerService answerService;
    @PostMapping("/question/ask")
    public String submitQuestionForm(@ModelAttribute QuestionDTO questionDTO, HttpServletRequest request) {
        questionService.saveQuestion(questionDTO);
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/member/2");
    }

    @PostMapping("/question/delete")
    public String deleteQuestion(@RequestParam("questionId") Long questionId, HttpServletRequest request) {
        questionService.deleteQuestion(questionId);
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/member/2");
    }

    @PostMapping("/question/answer")
    public String submitAnswer(@RequestParam("questionId") Long questionId,
                               @RequestParam("answerContent") String answerContent,
                               HttpServletRequest request) {
        answerService.saveAnswer(questionId, answerContent);
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/member/2");
    }
}
