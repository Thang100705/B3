package com.example.test.Config;

import com.example.test.models.Users;
import com.example.test.respositories.UserRespo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRespo userRespo;
    @Autowired
    public CustomUserDetailsService(UserRespo userRespo) {
        this.userRespo = userRespo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users customer=userRespo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
        return new User(customer.getUsername(), customer.getPassword(),new ArrayList<>());
//        return  User.builder()
//                .username(customer.getUsername())
//                .password(customer.getPassword())
//                .build();

    }
}
