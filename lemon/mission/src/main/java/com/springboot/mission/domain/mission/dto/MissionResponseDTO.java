package com.springboot.mission.domain.mission.dto;

import com.springboot.mission.domain.review.dto.ReviewResponseDTO;
import lombok.Builder;

import java.util.List;

public class MissionResponseDTO {
    @Builder
    public record GetMissionInfo(
            Long mission_id,
            String store_name,
            String category,
            String mission_content,
            Boolean my_mission
    ){}

    @Builder
    public record MissionListResponse(
            List<GetMissionInfo> content,
            Integer total_mission,
            Integer page_offset
    ) {}

    @Builder
    public record MyMissionInfo(
            Long mission_id,
            String store_name,
            String mission_content,
            Integer score,
            Boolean state
    ){}

    @Builder
    public record MyMissionListResponse(
            List<MyMissionInfo> content,
            Integer total_mission,
            Integer page_offset
    ) {}
}
