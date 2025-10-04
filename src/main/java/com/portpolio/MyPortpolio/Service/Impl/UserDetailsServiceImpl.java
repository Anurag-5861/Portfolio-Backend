package com.portpolio.MyPortpolio.Service.Impl;

import com.portpolio.MyPortpolio.Entity.Users;
import com.portpolio.MyPortpolio.Repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepo usersRepo;

    public UserDetailsServiceImpl(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = usersRepo.findByEmail(email);
        if(users!=null) {
            return User
                    .withUsername(users.getEmail())
                    .password(users.getPassword())
                    .roles(users.getRoles().toArray(new String[0]))
                    .build();
        }
    throw new UsernameNotFoundException("User not found");
    }
}
