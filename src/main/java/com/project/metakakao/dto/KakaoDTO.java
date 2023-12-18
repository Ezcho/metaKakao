package com.project.metakakao.dto;

import lombok.*;

@Builder
@Data

public class KakaoDTO {
    private String email;
    private String nickname;
    private String profileImgUrl;
}