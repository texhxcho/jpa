package com.github.texhxcho.jpaExample.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.github.texhxcho.jpaExample.dto.SubscriptionDto;

public interface SubscriptionRepositoryCustom {

    List<SubscriptionDto> search(Integer offset, Integer limit,
        String searchText, LocalDateTime fromDate, LocalDateTime toDate);
}
