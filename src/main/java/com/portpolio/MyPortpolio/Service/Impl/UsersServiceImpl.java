package com.portpolio.MyPortpolio.Service.Impl;

import com.portpolio.MyPortpolio.Entity.Users;
import com.portpolio.MyPortpolio.Repo.UsersRepo;
import com.portpolio.MyPortpolio.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepo usersRepo;

    public UsersServiceImpl(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public void saveUser(Users users) {
        usersRepo.save(users);
    }


    @Override
    public void deleteUserById() {

    }

    @Override
    public List<Users> deleteUser() {
        return List.of();
    }

    @Override
    public List<Users> updateUser() {
        return List.of();
    }


}
