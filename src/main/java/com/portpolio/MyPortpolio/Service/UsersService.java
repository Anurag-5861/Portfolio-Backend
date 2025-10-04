package com.portpolio.MyPortpolio.Service;

import com.portpolio.MyPortpolio.Entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
     void saveUser(Users users);
     void deleteUserById();
     List<Users> deleteUser();
     List<Users> updateUser();
}
