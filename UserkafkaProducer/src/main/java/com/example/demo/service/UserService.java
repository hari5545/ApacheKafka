package com.example.demo.service;

import com.example.demo.dto.UserDto;

public interface UserService {
    boolean saveUser(UserDto userDto);
    UserDto getUser(int id);
}
