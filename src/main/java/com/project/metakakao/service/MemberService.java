package com.project.metakakao.service;

import com.project.metakakao.entity.Member;
import com.project.metakakao.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member getMemberById(int memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        return member.orElseThrow(() ->
                new RuntimeException("해당 id의 유저페이지를 찾을 수 없습니다. id: " + memberId));
    }
}


