package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.OrderDto;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService
{

    @Autowired
    UserRepository ur;
    @Override
    public UserDto createUser(UserDto user) throws Exception {
        //dto to entity
        UserEntity u = UserEntity.builder().userId(user.getUserId()).firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail()).build();
        ur.save(u);
        return user;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
       UserEntity u = ur.findByEmail(email);
       //entity to dto
        UserDto udto = UserDto.builder().userId(u.getUserId()).id(u.getId()).firstName(u.getFirstName()).lastName(u.getLastName()).email(u.getEmail()).build();

        return udto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity u = ur.findByUserId(userId);
        UserDto udto = UserDto.builder().userId(u.getUserId()).id(u.getId()).firstName(u.getFirstName()).lastName(u.getLastName()).email(u.getEmail()).build();
        return udto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        //old user
        UserEntity oldUser = ur.findByUserId(userId);
        //update the old user
        UserEntity newUser = UserEntity.builder().id(oldUser.getId()).userId(user.getUserId()).firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail()).build();
        //save new user
        ur.save(newUser);
        return user;
        //return null;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        Long x = Long.parseLong(userId);
        ur.deleteById(x);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> u = (List<UserEntity>)ur.findAll();
        List<UserDto> ans = new ArrayList<>();
        int n = u.size();
        for(int i = 0; i<n; i++)
        {
            //entity to dto
            UserDto udto = UserDto.builder().userId(u.get(i).getUserId()).id(u.get(i).getId()).firstName(u.get(i).getFirstName()).lastName(u.get(i).getLastName()).email(u.get(i).getEmail()).build();
            ans.add(udto);
        }
        return ans;
       // return null;
    }
}