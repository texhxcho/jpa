package com.github.texhxcho.jpaExample.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(indexes = {@Index(columnList = "createDate", name = "IDX_CREATE_DATE")})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @Column(length = 128, columnDefinition = "varchar(128) NOT NULL comment '사용자ID'")
    private String userId;

    @Column(length = 128, columnDefinition = "varchar(128) NOT NULL comment '사용자이름'")
    private String userName;

    @Column(columnDefinition = "varchar(128) NOT NULL comment '사용자패스워'")
    private String userPw;

    @Column(columnDefinition = "datetime DEFAULT NULL comment '생성일'")
    private LocalDateTime createDate;

    @Column(columnDefinition = "datetime DEFAULT NULL comment '수정일'")
    private LocalDateTime updateDate;
}
