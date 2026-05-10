package com.springboot.mission.domain.user.service;

import com.springboot.mission.domain.user.code.MemberErrorCode;
import com.springboot.mission.domain.user.dto.UserRequestDTO;
import com.springboot.mission.domain.user.dto.UserResponseDTO;
import com.springboot.mission.domain.user.entity.User;
import com.springboot.mission.domain.user.exception.UserException;
import com.springboot.mission.domain.user.repository.UserRepository;
import com.springboot.mission.global.apiPayload.code.GeneralErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public UserResponseDTO.JoinResponse join(UserRequestDTO.JoinUser request) {
        // 1. 중복 가입 확인
        if (userRepository.existsByMail(request.mail())) {
            throw new UserException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        // 2. DTO -> Entity (정적 팩토리 메서드 활용)
        User user = request.toEntity();

        // 3. 저장
        User savedUser = userRepository.save(user);

        // 4. Entity -> DTO 반환
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
