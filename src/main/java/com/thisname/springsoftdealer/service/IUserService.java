package com.thisname.springsoftdealer.service;

import com.thisname.springsoftdealer.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> findById(Integer id);
    User save(User user);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
