package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.repository.UserMissionRepository;
import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMissionRepository userMissionRepository;

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

}
