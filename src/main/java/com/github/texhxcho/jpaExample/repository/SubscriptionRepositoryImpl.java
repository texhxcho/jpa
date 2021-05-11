package com.github.texhxcho.jpaExample.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.github.texhxcho.jpaExample.dto.SubscriptionDto;
import com.github.texhxcho.jpaExample.dto.SubscriptionStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.github.texhxcho.jpaExample.entity.QSubscription.subscription;
import static com.github.texhxcho.jpaExample.entity.QUser.user;


@RequiredArgsConstructor
@Slf4j
public class SubscriptionRepositoryImpl implements SubscriptionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<SubscriptionDto> search(Integer offset, Integer limit,
        String searchText, LocalDateTime fromDate, LocalDateTime toDate) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(subscription.subscriptionStatus.eq(SubscriptionStatus.ACTIVATE));

        if (searchText != null && !searchText.isEmpty()) {
            booleanBuilder.and(user.userName.contains(searchText));
        }

        booleanBuilder.and(subscription.startDateTime.between(fromDate, toDate));

        final QBean<SubscriptionDto> fields = Projections.fields(SubscriptionDto.class,
            user.userName.as("userName"),
            subscription.subscriptionId.as("subscriptionId"),
            subscription.user.userId.as("userId"),
            subscription.price.as("price"),
            subscription.subscriptionStatus.as("subscriptionStatus"),
            subscription.startDateTime.as("startDateTime"),
            subscription.endDateTime.as("endDateTime"),
            subscription.createDate.as("createDate")
        );

        final Expression[] groupByExpressions = {user.userName, subscription.subscriptionId, subscription.user.userId,
            subscription.price, subscription.subscriptionStatus, subscription.startDateTime,
            subscription.endDateTime, subscription.createDate};

        return queryFactory.select(fields).from(subscription)
            .innerJoin(user).on(subscription.user.userId.eq(user.userId))
            .where(booleanBuilder)
            .groupBy(groupByExpressions)
            .offset(offset)
            .limit(limit)
            .fetch();

    }

}
