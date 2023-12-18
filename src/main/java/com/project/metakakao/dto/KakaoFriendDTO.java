package com.project.metakakao.dto;

import lombok.Data;

@Data
public class KakaoFriendDTO {

    private String id;
    private String uuid;
    private boolean favorite;
    private String profile_nickname;
    private String profile_thumbnail_image;
}