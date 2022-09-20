package com.thisname.springsoftdealer.service;

import com.thisname.springsoftdealer.model.User;
import com.thisname.springsoftdealer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp  implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(Integer id) {

        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
