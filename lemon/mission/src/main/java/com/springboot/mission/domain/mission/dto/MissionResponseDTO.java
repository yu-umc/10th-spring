package com.springboot.mission.domain.mission.dto;

import com.springboot.mission.domain.mission.entity.MemberMission;
import com.springboot.mission.domain.mission.entity.Mission;
import com.springboot.mission.domain.store.entity.Store;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionResponseDTO {

    @Builder
    public record GetMissionInfo(
            Long mission_id,
            String store_name,
            String category,
            String mission_content,
            Boolean my_mission
    ){
        public static GetMissionInfo from(Mission mission, Boolean isMyMission) {
            Store store = mission.getStore();
            String categoryName = (store.getCategory() != null)
                    ? store.getCategory().getCategory()
                    : "미지정";

            return GetMissionInfo.builder()
                    .mission_id(mission.getId())
                    .store_name(store.getTitle())
                    .category(categoryName)
                    .mission_content(mission.getContent())
                    .my_mission(isMyMission)
                    .build();
        }
    }

    /**
     * 리스트 필드를 따로 두지 않고,
     * 페이징에 필요한 정보와 변환된 데이터를 수평적으로 나열한 응답 구조입니다.
     */
    @Builder
    public record MissionPageResponse(
            List<GetMissionInfo> missions, // 데이터 목록
            Long total_elements,           // 전체 데이터 개수
            Integer total_pages,           // 전체 페이지 수
            Integer current_page,          // 현재 페이지 번호
            Integer size,                  // 페이지당 데이터 개수
            Boolean is_first,
            Boolean is_last
    ) {
        public static MissionPageResponse from(Page<Mission> missionPage) {
            return MissionPageResponse.builder()
                    .missions(missionPage.stream()
                            .map(m -> GetMissionInfo.from(m, false))
                            .toList())
                    .total_elements(missionPage.getTotalElements())
                    .total_pages(missionPage.getTotalPages())
                    .current_page(missionPage.getNumber())
                    .size(missionPage.getSize())
                    .is_first(missionPage.isFirst())
                    .is_last(missionPage.isLast())
                    .build();
        }
    }

    @Builder
    public record MyMissionInfo(
            Long mission_id,
            String store_name,
            String mission_content,
            Integer score,
            Boolean state
    ) {
        public static MyMissionInfo from(MemberMission mission) {
            return MyMissionInfo.builder()
                    .mission_id(mission.getId())
                    .store_name(mission.getMission().getStore().getTitle())
                    .mission_content(mission.getMission().getContent())
                    .score(mission.getMission().getScore())
                    .state(mission.getIsCompleted())
                    .build();
        }
    }

    /**
     * 마이페이지 전용 페이징 응답
     */
    @Builder
    public record MyMissionPageResponse(
            List<MyMissionInfo> missions,
            Long total_elements,
            Integer total_pages,
            Integer current_page,
            Boolean is_first,
            Boolean is_last
    ) {
        public static MyMissionPageResponse from(Page<MemberMission> myMissionPage) {
            return MyMissionPageResponse.builder()
                    .missions(myMissionPage.stream()
                            .map(MyMissionInfo::from)
                            .toList())
                    .total_elements(myMissionPage.getTotalElements())
                    .total_pages(myMissionPage.getTotalPages())
                    .current_page(myMissionPage.getNumber())
                    .is_first(myMissionPage.isFirst())
                    .is_last(myMissionPage.isLast())
                    .build();
        }
    }
}