package com.project.metakakao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizNo;

    @Column(nullable = false)
    private String title;


    private String q1;
    private String a1;
    private String q2;
    private String a2;
    private String q3;
    private String a3;
    private String q4;
    private String a4;
    private String q5;
    private String a5;
    private String q6;
    private String a6;
    private String q7;
    private String a7;
    private String q8;
    private String a8;
    private String q9;
    private String a9;
    private String q10;
    private String a10;





    private LocalDateTime regDate;

    private LocalDateTime modDate;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Member.class)
//    @JoinColumn(name = "HOST_ID", updatable = false)
//    @JsonBackReference
//    private Member host; // 질문 작성자 -> 본인

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member host; // 퀴즈 작성자 -> 본인


}
