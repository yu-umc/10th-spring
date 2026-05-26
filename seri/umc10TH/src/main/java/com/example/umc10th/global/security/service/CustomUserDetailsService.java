package com.example.umc10th.global.security.service;

import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CustomUserDetailsService {

    private final MemberRepository memberRepository;

    public UserDetails loadUserByUidAndSocialType(
            SocialType socialType,
            String username
    ) throws UsernameNotFoundException{
        Member member = memberRepository.findBySocialTypeAndSocialUid(SocialType, username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUD));

        return new AuthMember(member);
    }
}
