package com.github.texhxcho.jpaExample.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.lang.NonNull;

import com.github.texhxcho.jpaExample.dto.ScheduleConfigForDto;

public interface ScheduleConfigRepositoryCustom {

    List<ScheduleConfigForDto> findAllByYearIsInAndMonthIsInAndDayIsInAndHourAndMinuteIsBetween(
        @NonNull Collection<Integer> years,
        @NonNull Collection<Integer> months,
        @NonNull Collection<Integer> days,
        int hour,
        int startMinuteInclusive,
        int endMinuteExclusive
    );
}
