package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.dto.AuthReqDTO;
import com.example.umc10th.domain.user.dto.AuthResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserMissionRepository;
import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Long findIdByEmail(String email) {
        return userRepository.findByEmail(email) // 1. Optional<User> 상자를 받아옵니다.
                // 2. 상자를 열어보고 만약 유저가 없다면(null이면) 예외를 시원하게 터트립니다!
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 유저가 존재하지 않습니다: " + email))
                // 3. 유저가 안전하게 잘 들어있다면 고유 ID를 쏙 꺼내서 리턴합니다.
                .getId();
    }

    public UserResDTO.Pagination<UserResDTO.UserMissionDetailDTO> getMissionsByUserID(
            Long userId,
            MissionStatus status,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sortInfo);

        Page<UserMission> userMissionList = userMissionRepository.findAllByUserIdAndStatus(userId,status,pageRequest);

        // 미션들 응답 DTO로 포장하기
        return UserConverter.toPagination(
                userMissionList.map(UserConverter::toUserMissionDetailDTO).toList(),
                userMissionList.getNumber(),
                userMissionList.getSize()
        );
    }

    public User joinUser(AuthReqDTO.Join request){

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User newUser = User.builder()
                .name(request.getName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .email(request.getEmail())
                .password(encodedPassword)
                .nickname(request.getNickname())
                .build();

        return userRepository.save(newUser);
    }

    @Transactional
    public AuthResDTO.LoginResult loginUser(AuthReqDTO.Login request) {
        // 이메일로 유저가 존재하는지 확인
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        // 저장된 비밀번호가 일치하는지 검증
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        AuthMember authMember = new AuthMember(user);

        // 이메일을 기반으로 JWT 토큰 생성
        String token = jwtUtil.createAccessToken(authMember);

        // 4. 발급된 토큰을 응답 DTO에 예쁘게 포장해서 리턴!
        return AuthResDTO.LoginResult.builder()
                .token(token)
                .build();
    }
}
