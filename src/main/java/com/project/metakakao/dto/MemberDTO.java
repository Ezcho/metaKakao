package com.project.metakakao.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class MemberDTO {
    String mid;
    String email;
    String nickname;
}
