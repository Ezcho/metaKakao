package com.project.metakakao.service;

import com.project.metakakao.dto.CreateQuizDTO;
import com.project.metakakao.dto.KakaoDTO;
import com.project.metakakao.dto.MemberDTO;
import com.project.metakakao.dto.QuestionDTO;
import com.project.metakakao.entity.Quiz;

import java.util.List;

public interface QuizService {


    void register(CreateQuizDTO createQuizDTO);

    Quiz findQuizById(String mid, Long quizNo);

    List<Quiz> findAllQuizById(String mid);
}
