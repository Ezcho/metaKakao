package com.project.metakakao.controller;


import com.project.metakakao.dto.CreateQuizDTO;
import com.project.metakakao.dto.KakaoDTO;
import com.project.metakakao.dto.MemberDTO;
import com.project.metakakao.entity.Member;
import com.project.metakakao.entity.Quiz;
import com.project.metakakao.service.KakaoService;
import com.project.metakakao.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/quiz")
@Log4j2
@RequiredArgsConstructor
public class QuizController extends HttpServlet {

    private final QuizService quizService;
    private final KakaoService kakaoService;
    @GetMapping("/game/{mid}/{qno}")
    public String quizGame(@PathVariable("mid") String mid, @PathVariable("qno") Long quizNo, Model model) {
        // mid == mid인 회원의 quizNo == qNo인 quiz를 조회
        Quiz quiz = quizService.findQuizById(mid, quizNo);
        String q1 = quiz.getQ1();
        model.addAttribute("q1", q1);
        String a1 = quiz.getA1();
        model.addAttribute("a1", a1);
        String q2 = quiz.getQ2();
        model.addAttribute("q2", q2);
        String a2 = quiz.getA2();
        model.addAttribute("a2", a2);
        String q3 = quiz.getQ3();
        model.addAttribute("q3", q3);
        String a3 = quiz.getA3();
        model.addAttribute("a3", a3);
        String q4 = quiz.getQ4();
        model.addAttribute("q4", q4);
        String a4 = quiz.getA4();
        model.addAttribute("a4", a4);
        String q5 = quiz.getQ5();
        model.addAttribute("q5", q5);
        String a5 = quiz.getA5();
        model.addAttribute("a5", a5);
        String q6 = quiz.getQ6();
        model.addAttribute("q6", q6);
        String a6 = quiz.getA6();
        model.addAttribute("a6", a6);
        String q7 = quiz.getQ7();
        model.addAttribute("q7", q7);
        String a7 = quiz.getA7();
        model.addAttribute("a7", a7);
        String q8 = quiz.getQ8();
        model.addAttribute("q8", q8);
        String a8 = quiz.getA8();
        model.addAttribute("a8", a8);
        String q9 = quiz.getQ9();
        model.addAttribute("q9", q9);
        String a9 = quiz.getA9();
        model.addAttribute("a9", a9);
        String q10 = quiz.getQ10();
        model.addAttribute("q10", q10);
        String a10 = quiz.getA10();
        model.addAttribute("a10", a10);

        return "/quiz/quiz";
    }

    @GetMapping
    public String newquiz() {
        return "/quiz/newquiz";
    }

    @GetMapping("/myquiz")
    public String myquiz() {
        return "quiz/myquiz";
    }

    // 전체 조회
    @GetMapping("/{mid}/list")
    public String list(@PathVariable("mid") String mid, Model model) {
        List<Quiz> quizList = quizService.findAllQuizById(mid);
        List<String> titles = new ArrayList<>();
        List<LocalDateTime> regDates = new ArrayList<>();
        model.addAttribute("mid", mid);
        for (Quiz quiz : quizList) {
            titles.add(quiz.getTitle());
            regDates.add(quiz.getRegDate());
        }

        model.addAttribute("titles", titles);
        model.addAttribute("regDates", regDates);

        return "quiz/quizlist";
    }

    // 단건 조회

    // 퀴즈 생성
    @PostMapping("/make")
    public ResponseEntity makeQuiz(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quizTitle = request.getParameter("quizTitle");
        log.info("quizTitle: " + quizTitle);
        List<String> questions = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            questions.add(request.getParameter("question" + i));
            answers.add(request.getParameter("answer"+i));
            log.info("questions: " + request.getParameter("question" + i));
            log.info("answers: " + request.getParameter("answer"+i));
        }
        String accessToken = (String) request.getSession().getAttribute("accessToken");
        log.info("accessToken: " + accessToken);
        try {
            KakaoDTO memberDTO = kakaoService.getUserInfoWithTokenPub(accessToken); //null
//            Member member = Member.builder()
//                    .mid(String.valueOf(kakaoDTO.getId()))
//                    .email(kakaoDTO.getEmail())
//                    .nickname(kakaoDTO.getNickname())
//                    .build();
            log.info("회원 id: " + memberDTO.getEmail());
            log.info("회원 이메일: " + memberDTO.getEmail());
            log.info("회원 닉네임: " + memberDTO.getNickname());

            CreateQuizDTO createQuizDTO = CreateQuizDTO.builder()
                    .title(quizTitle)
                    .q1(questions.get(0))
                    .a1(answers.get(0))
                    .q2(questions.get(1))
                    .a2(answers.get(1))
                    .q3(questions.get(2))
                    .a3(answers.get(2))
                    .q4(questions.get(3))
                    .a4(answers.get(3))
                    .q5(questions.get(4))
                    .a5(answers.get(4))
                    .q6(questions.get(5))
                    .a6(answers.get(5))
                    .q7(questions.get(6))
                    .a7(answers.get(6))
                    .q8(questions.get(7))
                    .a8(answers.get(7))
                    .q9(questions.get(8))
                    .a9(answers.get(8))
                    .q10(questions.get(9))
                    .a10(answers.get(9))
                    .hostId(memberDTO.getEmail())
                    .regDate(LocalDateTime.now())
                    .build();
            log.info("받아온 질문 1: " + questions.get(0));
            //db에 저장
            quizService.register(createQuizDTO);
            String mid = memberDTO.getEmail();
            response.sendRedirect("/quiz/" + mid +"/list");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 수정

    // 삭제
}
