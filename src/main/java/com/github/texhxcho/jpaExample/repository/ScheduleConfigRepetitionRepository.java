package com.github.texhxcho.jpaExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import com.github.texhxcho.jpaExample.entity.ScheduleConfig;
import com.github.texhxcho.jpaExample.entity.ScheduleConfigRepetition;

public interface ScheduleConfigRepetitionRepository extends JpaRepository<ScheduleConfigRepetition, Long> {

    @Transactional
    void deleteAllByScheduleConfig(@NonNull ScheduleConfig scheduleConfig);
}
