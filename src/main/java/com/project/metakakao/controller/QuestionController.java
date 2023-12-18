package com.project.metakakao.controller;

import com.project.metakakao.dto.QuestionDTO;
import com.project.metakakao.service.AnswerService;
import com.project.metakakao.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService; // QuestionService는 의존성 주입 필요
    @Autowired
    private AnswerService answerService;
    @PostMapping("/question/ask")
    public String submitQuestionForm(@ModelAttribute QuestionDTO questionDTO) {
        questionService.saveQuestion(questionDTO);
        System.out.println("Question생성");
        return "redirect:/member/2";
    }
    @PostMapping("/question/delete")
    public String deleteQuestion(@RequestParam("questionId") Long questionId) {
        questionService.deleteQuestion(questionId);
        System.out.println("Question 삭제됨");
        return "redirect:/member/2"; // 삭제 후 리디렉션할 페이지 (멤버 프로필 페이지 등)
    }
    @PostMapping("/question/answer")
    public String submitAnswer(@RequestParam("questionId") Long questionId,
                               @RequestParam("answerContent") String answerContent) {
        answerService.saveAnswer(questionId, answerContent);
        return "redirect:/member/2"; // 답변 제출 후 리디렉션할 페이지
    }
}
