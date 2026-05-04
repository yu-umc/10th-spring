package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String createUser(

    ){
        Member member = Member.builder()
                .name("test")
                .build();
        memberRepository.save(member);
        return "OK";
    }

    @Transactional
    public String deleteUser(

    ){
        memberRepository.deleteByName("test");
        return "OK";
    }

    //Query Parameter
    public String singleParameter(
            String singleParameter
    ){
        return singleParameter;
    }

    //Request Body
    public MemberResDTO.RequestBody requestBody(
            MemberReqDTO.RequestBody dte
    ){
        return MemberConverter.toRequestBody(dto.stringTest(), dto.longTest());
    }

    public MemberResDTO.GetInfo getInfo(
            MemberReqDTO.GetInfo dto
    ){
        Long member Id = dto.id();
        Member member = memberRepository.findBy(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }
}
