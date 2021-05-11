package com.github.texhxcho.jpaExample.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.texhxcho.jpaExample.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

    User findByUserId(String userId);
}
