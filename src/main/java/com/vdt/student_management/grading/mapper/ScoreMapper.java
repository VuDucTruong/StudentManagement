package com.vdt.student_management.grading.mapper;

import com.vdt.student_management.grading.dto.request.UpdateScoreRequest;
import com.vdt.student_management.grading.dto.response.ScoreResponse;
import com.vdt.student_management.grading.model.Score;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ScoreMapper {
  ScoreResponse toScoreResponse(Score score);

  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "enrollment", ignore = true)
  @Mapping(target = "deletedAt", ignore = true)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateScore(UpdateScoreRequest request, @MappingTarget Score score);
}
