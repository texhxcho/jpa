package com.github.texhxcho.jpaExample.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.github.texhxcho.jpaExample.dto.SubscriptionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(indexes = {@Index(columnList = "createDate", name = "IDX_CREATE_DATE"),
    @Index(columnList = "subscriptionStatus", name = "IDX_SUBSCRIPTION_STATUS")
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_SUBSCRIPTION_USER_ID"))
    private User user;

    @Column(columnDefinition = "INT comment '이용권 가격'")
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(32) comment '이용권 상태'")
    private SubscriptionStatus subscriptionStatus;

    @Column(columnDefinition = "datetime comment '이용권 시작일시'")
    private LocalDateTime startDateTime;

    @Column(columnDefinition = "datetime comment '이용권 종료일시'")
    private LocalDateTime endDateTime;

    @CreatedDate
    @Column(columnDefinition = "datetime DEFAULT NULL comment '생성일'")
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(columnDefinition = "datetime DEFAULT NULL comment '수정일'")
    private LocalDateTime updateDate;
}
