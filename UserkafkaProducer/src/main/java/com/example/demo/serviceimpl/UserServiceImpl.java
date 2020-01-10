package com.example.demo.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean saveUser(UserDto userDto) {
		User user=new User();
		boolean flag=true;
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		if(flag) {
			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public UserDto getUser(int id) {
		Optional<User> user=userRepository.findById(id);
		UserDto userDto=new UserDto(user.get().getUserName(), user.get().getPassword());
		return userDto;
	}


}
