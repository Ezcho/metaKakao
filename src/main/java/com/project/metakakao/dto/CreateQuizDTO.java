package com.project.metakakao.dto;

import com.project.metakakao.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuizDTO {
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
    private String hostId;
    private LocalDateTime regDate;
}
