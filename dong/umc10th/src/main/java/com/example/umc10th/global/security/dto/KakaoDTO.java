package com.example.umc10th.global.security.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KakaoDTO implements OAuthDTO{
    private final String id;
    private final String email;
    private final String name;

    @Override
    public SocialType getSocialType(){
        return SocialType.KAKAO;
    }

    @Override
    public String getSocailUid(){
        return id;
    }

    @Override
    public String getSocialEmail() {
        return email;
    }

    @Override
    public String getName(){
        return name;
    }
}
