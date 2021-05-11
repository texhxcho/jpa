package com.github.texhxcho.jpaExample.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(indexes = {@Index(columnList = "hour,minute,day,month,year", name = "IDX_REPETITION")})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@ToString
public class ScheduleConfigRepetition {

    public static final int INTEGER_MEANING_ANY_VALUE = -1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_config_id", foreignKey = @ForeignKey(name = "FK_SCHEDULE_CONFIG_REPETITION_SCHEDULE_CONFIG_ID"))
    private ScheduleConfig scheduleConfig;

    @Column(columnDefinition = "INT(4) NOT NULL DEFAULT -1")
    private int year;

    @Column(columnDefinition = "INT(2) NOT NULL DEFAULT -1")
    private int month;

    @Column(columnDefinition = "INT(3) NOT NULL DEFAULT -1")
    private int day;

    @Column(columnDefinition = "INT(2) NOT NULL DEFAULT -1")
    private int hour;

    @Column(columnDefinition = "INT(2) NOT NULL DEFAULT -1")
    private int minute;

    @CreatedDate
    @Builder.Default
    private LocalDateTime createdDateTime = LocalDateTime.now();
}
