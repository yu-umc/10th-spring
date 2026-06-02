package com.springboot.mission.domain.user.dto;

import com.springboot.mission.domain.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import java.time.LocalDate;

public class UserRequestDTO {

    @Builder
    public record JoinUser(
            @NotBlank(message = "소셜 고유 식별자는 필수입니다.")
            String mail,

            @NotBlank(message = "로그인용 이메일은 필수 입력 항목입니다.")
            @Email(message = "올바른 이메일 형식이 아닙니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
            @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$",
                    message = "비밀번호는 8~16자 영문 대소문자, 숫자, 특수문자를 모두 포함해야 합니다.")
            String password,

            @NotBlank(message = "이름은 필수 입력 항목입니다.")
            String name,

            @NotNull(message = "생년월일은 필수 입력 항목입니다.")
            LocalDate birthday,

            @NotBlank(message = "성별은 필수 입력 항목입니다.")
            String gender,

            @NotBlank(message = "주소는 필수 입력 항목입니다.")
            String address,

            @NotBlank(message = "소셜 타입은 필수 입력 항목입니다.")
            String socialType
    ) {
        /**
         * 서비스 단에서 암호화된 비밀번호를 주입받아 엔티티로 변환합니다.
         */
        public User toEntity(String encodedPassword) {
            return User.builder()
                    .mail(this.mail)
                    .email(this.email)            // 추가된 일반 로그인 이메일
                    .password(encodedPassword)   // BCrypt로 암호화된 비밀번호 매핑
                    .name(this.name)
                    .birth(this.birthday)
                    .address(this.address)
                    .gender(mapGender(this.gender))
                    .social(this.socialType)
                    .nickname("사용자")
                    .isDeleted(false)
                    .build();
        }
    }

        private static Integer mapGender(String gender) {
            if (gender == null) return 0;
            return switch (gender.toLowerCase()) {
                case "male" -> 1;
                case "female" -> 2;
                default -> 0;
            };
        }
}
