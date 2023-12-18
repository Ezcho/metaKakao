package com.project.metakakao.service;

import com.project.metakakao.dto.QuestionDTO;
import com.project.metakakao.entity.Member;
import com.project.metakakao.entity.Question;
import com.project.metakakao.repository.MemberRepository;
import com.project.metakakao.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository; // Repository 의존성 주입
    @Autowired
    private MemberRepository memberRepository;

    public void saveQuestion(QuestionDTO questionDTO) {
        Question question = convertToEntity(questionDTO);
        questionRepository.save(question);
    }

    private Question convertToEntity(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setContent(questionDTO.getContent());
        question.setRegDate(LocalDateTime.now());
        Member host = memberRepository.findById(questionDTO.getHostID())
                .orElseThrow(() -> new RuntimeException("host가 존재하지않습니다."));
        question.setHost(host);
        question.setStatus(0);
        return question;
    }
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
}
