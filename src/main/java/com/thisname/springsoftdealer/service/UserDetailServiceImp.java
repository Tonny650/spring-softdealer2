package com.thisname.springsoftdealer.service;

import com.thisname.springsoftdealer.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    HttpSession session;

    private Logger log = LoggerFactory.getLogger(UserDetailServiceImp.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.findByEmail(username);
        log.info("UserName");

        if(optionalUser.isPresent()){
            session.setAttribute("idUser", optionalUser.get().getId());
            User usuario = optionalUser.get();
            return org.springframework.security.core.userdetails.User
                    .builder().username(usuario.getUsername())
                    .password(usuario.getPassword())
                    .roles(usuario.getType()).build();
        }else {
             throw new UsernameNotFoundException("Usuario no encontrado");
        }

    }
}
