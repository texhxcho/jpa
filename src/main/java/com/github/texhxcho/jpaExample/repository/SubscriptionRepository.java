package com.github.texhxcho.jpaExample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.texhxcho.jpaExample.dto.SubscriptionStatus;
import com.github.texhxcho.jpaExample.entity.Subscription;
import com.github.texhxcho.jpaExample.entity.User;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>, SubscriptionRepositoryCustom {

    Subscription findByUserAndSubscriptionStatus(User user, SubscriptionStatus subscriptionStatus);

    List<Subscription> findBySubscriptionStatus(SubscriptionStatus subscriptionStatus);

    Long countBySubscriptionStatus(SubscriptionStatus activate);
}
