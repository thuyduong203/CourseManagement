package com.liam.be.infrastructure.config.security;

import com.liam.be.entity.Account;
import com.liam.be.entity.User;
import com.liam.be.repository.AccountRepository;
import com.liam.be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByCode(username).orElse(null);
        if (user != null) {
            return new CustomUserDetails(user.getAccount(), user);
        }
        throw new UsernameNotFoundException(username);
    }
}
