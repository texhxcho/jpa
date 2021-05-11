package com.github.texhxcho.jpaExample.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.texhxcho.jpaExample.dto.SubscriptionDto;
import com.github.texhxcho.jpaExample.dto.SubscriptionStatus;
import com.github.texhxcho.jpaExample.entity.ScheduleConfig;
import com.github.texhxcho.jpaExample.entity.Subscription;
import com.github.texhxcho.jpaExample.entity.User;
import com.github.texhxcho.jpaExample.repository.ScheduleConfigRepetitionRepository;
import com.github.texhxcho.jpaExample.repository.ScheduleConfigRepository;
import com.github.texhxcho.jpaExample.repository.SubscriptionRepository;
import com.github.texhxcho.jpaExample.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

//@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
//@Transactional
public class SubscriptionServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private ScheduleConfigRepository scheduleConfigRepository;

    @Autowired
    private ScheduleConfigRepetitionRepository scheduleConfigRepetitionRepository;

    @Before
    public void setUp() {

        User 철수 = createUser("user-id1", "철수", "pw1234");
        Subscription 철수이용권 = createSubscription(철수, LocalDateTime.now(), LocalDateTime.now().plusMonths(12l));

        User 영희 = createUser("user-id2", "영희", "pw5555");
        Subscription 영희이용권 = createSubscription(영희, LocalDateTime.now(), LocalDateTime.now().plusMonths(12l));
    }

    @Test
    public void 이용권_생성_조회() {

        List<SubscriptionDto> resultList = subscriptionRepository
            .search(0, 100, "철", LocalDateTime.now().minusDays(10), LocalDateTime.now().plusMonths(1l));
        for (SubscriptionDto dto : resultList) {
            log.info(dto.toString());
        }
    }

    public static LocalDateTime toLocalDateTime(String yyyyMMddHHmmss) {
        return LocalDateTime
            .parse(yyyyMMddHHmmss, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    private Subscription createSubscription(User user, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return subscriptionRepository.save(Subscription.builder()
            .user(user)
            .createDate(LocalDateTime.now())
            .startDateTime(startDateTime)
            .endDateTime(endDateTime)
            .updateDate(LocalDateTime.now())
            .subscriptionStatus(SubscriptionStatus.ACTIVATE)
            .build());
    }

    private User createUser(String userId, String userName, String userPw) {
        String addedStr = RandomStringUtils.randomAlphanumeric(6);

        User user = User.builder()
            .userId(userId + addedStr)
            .userName(userName + addedStr)
            .userPw(userPw + addedStr)
            .build();

        userRepository.save(user);
        return user;
    }

    @Ignore
    @Test
    public void 일정정보_저장() {

        User 철수 = createUser("user-id1", "철수", "pw1234");
        Subscription 철수이용권 = createSubscription(철수, LocalDateTime.now(), LocalDateTime.now().plusMonths(12l));

        ScheduleConfig scheduleConfig = ScheduleConfig.builder()
            .subscription(철수이용권)
            .scheduleConfigId(1l)
            .repeatDayOfWeek("MON,TUE,WED,THU,FRI,SAT,SUN")
            .repeatHourAndMinute("0900,1200,1800")
            .scheduleContent("물 마실 시간입니다.")
            .enabled(true)
            .build();
        ScheduleConfig savedScheduleConfig = scheduleConfigRepository.save(scheduleConfig);
    }

}