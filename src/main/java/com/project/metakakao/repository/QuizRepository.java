package com.project.metakakao.repository;

import com.project.metakakao.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("select q from Quiz q where q.host.email =:mid and q.quizNo =:quizNo")
    public Quiz findByMid(String mid, Long quizNo);

    @Query("select q from Quiz q where q.host.email =:mid")
    public List<Quiz> findAllByMid(String mid);
}
