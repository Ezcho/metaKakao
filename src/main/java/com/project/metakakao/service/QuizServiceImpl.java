package com.project.metakakao.service;

import com.project.metakakao.dto.CreateQuizDTO;
import com.project.metakakao.dto.KakaoDTO;
import com.project.metakakao.dto.MemberDTO;
import com.project.metakakao.entity.Member;
import com.project.metakakao.entity.MemberRole;
import com.project.metakakao.entity.Quiz;
import com.project.metakakao.repository.MemberRepository;
import com.project.metakakao.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class QuizServiceImpl implements QuizService{

    private final ModelMapper modelMapper;
    private final QuizRepository quizRepository;
    private final MemberRepository memberRepository;


    public Quiz findQuizById(String mid, Long quizNo) {
        Quiz quiz = quizRepository.findByMid(mid, quizNo);
        return quiz;
    }

    public List<Quiz> findAllQuizById(String mid) {
        List<Quiz> quizList = quizRepository.findAllByMid(mid);
        return quizList;
    }

    public void register(CreateQuizDTO createQuizDTO) {
        Member member = memberRepository.findByEmail(createQuizDTO.getHostId()).get();
        Quiz quiz = Quiz.builder()
                .title(createQuizDTO.getTitle())
                .q1(createQuizDTO.getQ1())
                .a1(createQuizDTO.getA1())
                .q2(createQuizDTO.getQ2())
                .a2(createQuizDTO.getA2())
                .q3(createQuizDTO.getQ3())
                .a3(createQuizDTO.getA3())
                .q4(createQuizDTO.getQ4())
                .a4(createQuizDTO.getA4())
                .q5(createQuizDTO.getQ5())
                .a5(createQuizDTO.getA5())
                .q6(createQuizDTO.getQ6())
                .a6(createQuizDTO.getA6())
                .q7(createQuizDTO.getQ7())
                .a7(createQuizDTO.getA7())
                .q8(createQuizDTO.getQ8())
                .a8(createQuizDTO.getA8())
                .q9(createQuizDTO.getQ9())
                .a9(createQuizDTO.getA9())
                .q10(createQuizDTO.getQ10())
                .a10(createQuizDTO.getA10())
                .host(member)
                .regDate(createQuizDTO.getRegDate())
                .build();
        quizRepository.save(quiz);
    }
}
