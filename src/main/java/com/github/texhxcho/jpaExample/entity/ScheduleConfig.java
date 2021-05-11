package com.github.texhxcho.jpaExample.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class ScheduleConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleConfigId;

    @ManyToOne
    @JoinColumn(name = "subscription_id", foreignKey = @ForeignKey(name = "FK_SCHEDULE_CONFIG_SUBSCRIPTION_ID"))
    private Subscription subscription;

    /**
     * 일정 시간 yyyyMMddHHmm
     */
    @Column(length = 12, columnDefinition = "varchar(12) DEFAULT NULL comment '일정 시작시간 yyyyMMddHHmm'")
    @Getter
    private String scheduleDate;

    /**
     * 반복요일.
     */
    @Column(columnDefinition = "varchar(64) DEFAULT NULL comment '요일  MON, TUE, WED, THU, FRI, SAT, SUN'")
    @Getter
    private String repeatDayOfWeek;

    /**
     * 반복알림시간.
     */
    @Column(columnDefinition = "varchar(128) DEFAULT NULL comment '반복 일정 시간  0800, 1200, 1800'")
    @Getter
    private String repeatHourAndMinute;

    /**
     * 일정 내용
     */
    @Column(columnDefinition = "varchar(2048) DEFAULT NULL comment '일정내용'", length = 2048)
    @Getter
    private String scheduleContent;

    @Column(columnDefinition = "bit(1) DEFAULT TRUE comment '활성화여부 : true, false'")
    @Getter
    @Builder.Default
    private boolean enabled = true;

    @CreatedDate
    @Column(columnDefinition = "datetime DEFAULT NULL comment '생성일'")
    @Builder.Default
    @Getter
    private LocalDateTime createDateTime = LocalDateTime.now();

}
