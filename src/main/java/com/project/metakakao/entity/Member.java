package com.project.metakakao.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //auto indexing
    private String mpw; //1111으로 통일(나중에 Oauth말고, 시큐리티나 jwt구현할때 사용)
    private String email;   //이메일
    private String nickname;    //사용자명
    private String refreshToken;    //refreshToken은 dB에 저장
    private String profileImgUrl;   //profileImgUrl

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Question> questionList; // 받은 질문 목록

    private Long numRejected; // 거절 질문 개수


}
