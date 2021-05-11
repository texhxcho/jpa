package com.github.texhxcho.jpaExample.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.lang.NonNull;

import com.github.texhxcho.jpaExample.dto.ScheduleConfigForDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.github.texhxcho.jpaExample.entity.QScheduleConfig.scheduleConfig;
import static com.github.texhxcho.jpaExample.entity.QScheduleConfigRepetition.scheduleConfigRepetition;
import static com.github.texhxcho.jpaExample.entity.QSubscription.subscription;
import static com.github.texhxcho.jpaExample.entity.QUser.user;

@RequiredArgsConstructor
public class ScheduleConfigRepositoryImpl implements ScheduleConfigRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ScheduleConfigForDto> findAllByYearIsInAndMonthIsInAndDayIsInAndHourAndMinuteIsBetween(
        @NonNull Collection<Integer> years,
        @NonNull Collection<Integer> months,
        @NonNull Collection<Integer> days,
        int hour,
        int startMinuteInclusive,
        int endMinuteExclusive
    ) {

        return queryFactory.select(
            Projections.fields(ScheduleConfigForDto.class,
                user.userId.as("userId"),
                scheduleConfig.scheduleConfigId.as("scheduleConfigId"),
                scheduleConfig.scheduleContent.as("scheduleContent"),
                subscription.subscriptionId.as("subscriptionId"),
                scheduleConfigRepetition.hour.as("hour"),
                scheduleConfigRepetition.minute.as("minute")
            )).from(scheduleConfig)
            .innerJoin(scheduleConfigRepetition)
            .on(scheduleConfig.scheduleConfigId.eq(scheduleConfigRepetition.scheduleConfig.scheduleConfigId))
            .innerJoin(subscription)
            .on(scheduleConfig.subscription.subscriptionId.eq(subscription.subscriptionId))
            .innerJoin(user)
            .on(user.userId.eq(subscription.user.userId))
            .where(scheduleConfigRepetition.year.in(years)
                .and(scheduleConfigRepetition.month.in(months))
                .and(scheduleConfigRepetition.day.in(days))
                .and(scheduleConfigRepetition.hour.eq(hour))
                .and(scheduleConfigRepetition.minute.between(startMinuteInclusive, endMinuteExclusive)))
            .fetch();
    }

}
