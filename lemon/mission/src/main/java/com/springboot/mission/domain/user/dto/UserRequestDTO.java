package com.springboot.mission.domain.user.dto;

import com.springboot.mission.domain.user.entity.User;
import lombok.Builder;

import java.time.LocalDate;

public class UserRequestDTO {

    @Builder
    public record JoinUser(
            String mail,        // 소셜 고유 식별자/이메일
            String name,        // 소셜 프로필 이름
            LocalDate birthday, // 생년월일
            String gender,      // "male", "female" 등
            String address,
            String socialType   // "KAKAO" 등
    ) {
        /**
         * DTO -> Entity 변환 로직 (Static Factory Method 스타일)
         */
        public User toEntity() {
            return User.builder()
                    .mail(this.mail)           // 소셜 식별값 매핑
                    .name(this.name)
                    .birth(this.birthday)
                    .address(this.address)
                    .gender(mapGender(this.gender))
                    .social(this.socialType)   // 소셜 플랫폼 종류 저장
                    .nickname("사용자")         // 기본값
                    .isDeleted(false)
                    .build();
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
}
