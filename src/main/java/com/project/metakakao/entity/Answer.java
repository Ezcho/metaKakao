package com.project.metakakao.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ano;

    private String content;

    private LocalDateTime regDate;

    @OneToOne
    @JoinColumn(name = "QNO", referencedColumnName = "qno")
    private Question question; // 질문과의 관계
}

