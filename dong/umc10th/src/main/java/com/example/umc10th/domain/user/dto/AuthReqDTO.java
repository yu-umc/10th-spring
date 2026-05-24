package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.user.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class AuthReqDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class join{

        @NotBlank(message = "이름은 필수 입력 항목입니다.")
        String name;

        @NotBlank(message = "이메일은 필수 입력 항목입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email;

        @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
        @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
        String password;

        @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
        @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
        String nickname;

        @NotNull(message = "성별은 필수 항목입니다.")
        Gender gender;

        @NotNull(message = "생년월일은 필수 항목입니다.")
        LocalDate birth;
    }
}
