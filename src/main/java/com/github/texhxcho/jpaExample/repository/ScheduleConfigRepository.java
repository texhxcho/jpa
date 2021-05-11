package com.github.texhxcho.jpaExample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.texhxcho.jpaExample.entity.ScheduleConfig;
import com.github.texhxcho.jpaExample.entity.Subscription;

@Repository
public interface ScheduleConfigRepository extends JpaRepository<ScheduleConfig, Long>,
    ScheduleConfigRepositoryCustom {

    ScheduleConfig findTopBySubscriptionOrderByCreateDateTime(Subscription subscription);

    Long countScheduleConfigByEnabledIsTrueAndSubscription(Subscription subscription);

    List<ScheduleConfig> findAllByEnabledIsTrueAndSubscriptionOrderByCreateDateTimeDesc(
        Subscription subscription);

    void deleteAllBySubscription(Subscription subscription);

    List<ScheduleConfig> findByEnabledIsTrueAndSubscription(Subscription subscription);

}
