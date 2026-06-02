package com.springboot.mission.domain.user.service;

import com.springboot.mission.domain.user.code.MemberErrorCode;
import com.springboot.mission.domain.user.dto.UserRequestDTO;
import com.springboot.mission.domain.user.dto.UserResponseDTO;
import com.springboot.mission.domain.user.entity.User;
import com.springboot.mission.domain.user.exception.UserException;
import com.springboot.mission.domain.user.repository.UserRepository;
import com.springboot.mission.global.apiPayload.code.GeneralErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // BCrypt 인코더 주입

    /*
     * 시큐리티 기반 일반 폼 회원가입
     */
    @Transactional
    public UserResponseDTO.JoinResponse join(UserRequestDTO.JoinUser request) {

        // 1. 로그인용 이메일 중복 가입 확인
        if (userRepository.existsByEmail(request.email())) {
            throw new UserException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        // 2. 비밀번호 평문을 BCrypt 알고리즘으로 암호화 (자동 솔팅 포함)
        String encodedPassword = passwordEncoder.encode(request.password());

        // 3. DTO -> Entity 변환 시 암호화된 패스워드 전달
        User user = request.toEntity(encodedPassword);

        // 4. DB 저장
        User savedUser = userRepository.save(user);

        // 5. 결과 반환
        return UserResponseDTO.JoinResponse.from(savedUser);
    }

    /**
     * 마이페이지에 필요한 사용자 정보를 조회합니다.
     */
    public UserResponseDTO.MyPageInfo getMyPageInfo(Long userId) {
        // 1. 사용자 존재 여부 확인 및 조회 (오타 수정: MemberEc -> UserException)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(GeneralErrorCode.NOT_FOUND));

        // 2. DTO 변환 후 반환 (MyPageInfo.from 메서드 활용)
        return UserResponseDTO.MyPageInfo.from(user);
    }
}
