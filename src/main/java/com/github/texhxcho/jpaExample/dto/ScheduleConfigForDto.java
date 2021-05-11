package com.github.texhxcho.jpaExample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ScheduleConfigForDto {

    private String userId;
    private Long scheduleConfigId;
    private Long subscriptionId;
    private String scheduleContent;

}
