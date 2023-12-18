package com.project.metakakao.service;

import com.project.metakakao.entity.Answer;
import com.project.metakakao.entity.Question;
import com.project.metakakao.repository.AnswerRepository;
import com.project.metakakao.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository; // QuestionRepository 추가

    public void saveAnswer(Long questionId, String answerContent) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid question ID: " + questionId));

        Answer answer = question.getAnswer();
        if (answer == null) {
            answer = new Answer();
            answer.setQuestion(question);
        }
        answer.setContent(answerContent);
        answer.setRegDate(LocalDateTime.now());

        answerRepository.save(answer);
    }
}
