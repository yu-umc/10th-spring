package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
//@RequestMapping("/api")


public class MemberController {

    private final MemberService memberService;

    //아무것도 받지 않은 경우
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    //쿼리 파라메터
    @PostMapping("/query-parameter")
    public String exception(
            @RequestParam String queryParameter
    ){
        return memberService.singleParameter(queryParameter);
    }

    //Request Body
    @PostMapping("/request-body")
    public MemberResDTO.RequestBody requestBody(
            @RequestBody MemberReqDTO.RequestBody dto
    ){
        return memberService.requestBody(dto);
    }

    //Path Variable
    @PostMapping("/{pathVariable}")
    public String pathVariable(
            @PathVariable String pathVariable
    ){
        return memberService.singleParameter(pathVariable);
    }

    //Header
    @PostMapping("/header")
    public String header(
            @RequestHeader("test") String test
    ){
        return memberService.singleParameter(test);
    }

    //마이페이지
    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestBody MemberReqDTO.getInfo dto
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));

    }


    @GetMapping("/v2/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(

    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(member));
    }
}
