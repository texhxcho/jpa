package com.github.texhxcho.jpaExample.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SubscriptionDto {

    private Long subscriptionId;
    private String userId;
    private String userName;
    private String scheduleContent;
    private boolean scheduleEnabled;
    private LocalDateTime initialBuyDateTime;
    private LocalDateTime createDate;
}
