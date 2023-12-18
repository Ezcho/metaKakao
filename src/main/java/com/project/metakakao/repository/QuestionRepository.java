package com.project.metakakao.repository;

import com.project.metakakao.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select distinct q from Question q where q.host.id = :hostID and q.status = :status")
    List<Question> readAllByMid(int hostID, int status);

    @Modifying
    @Query("delete from Question q where q.host.id = :hostID and q.id = :qno")
    void deleteByHostID(int hostID, Long qno);
}
