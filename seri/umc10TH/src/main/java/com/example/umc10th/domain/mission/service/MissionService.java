package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class MissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public Void createMission(
            Long storeId,
            MissionReqDTO.CreateMission dto
    ){
        Store store = storeRepository.findBtId(storeId)
                .orElseThrow() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Mission mission = MissionConverter.toMission(store, dto);

        missionRepository.save(mission);
        return null;
    }

    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ){
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

}
