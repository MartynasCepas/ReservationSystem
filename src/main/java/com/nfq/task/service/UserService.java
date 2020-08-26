package com.nfq.task.service;

import com.nfq.task.domain.User;
import com.nfq.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder auth;

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManagerBuilder auth) {
        this.userRepository = userRepository;
        this.auth = auth;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(
                    "No user found with username: "+ email);
        }

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        try {
            auth.inMemoryAuthentication().withUser(user.getEmail()).password(user.getPassword()).roles(user.getUserRole().name());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  new org.springframework.security.core.userdetails.User
                (user.getEmail(),
                        user.getPassword().toLowerCase(), enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked,
                            user.getAuthorities());
    }

    public void signUpUser(User user) {

        userRepository.save(user);
    }


}
