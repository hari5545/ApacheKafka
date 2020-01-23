package com.example.demo.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserDto;

@RestController
@RequestMapping(path ="/consumer")
public class UserResources {
	List<UserDto> userDto=new ArrayList<>();
	List<String> messages=new ArrayList<>();
	
	@GetMapping("/consumeJsonMessage")
	public List<UserDto> consumeJsonMessage() {
		return userDto;
	}
	@GetMapping("/consumeStringMessage")
	public List<String> consumeStringMessage() {
		return messages;
	}
	
	@KafkaListener(groupId = "group-1", topics = "user-stream",containerFactory ="kafkaListenerContainerFactory")
	public List<String> consumeStringMessage(String data){
		messages.add(data);
		return messages;
	}
	
	// it consumes the data from the kafka broker 

	@KafkaListener(groupId = "group-2", topics = "user-stream", containerFactory = "userKafkaListenerContainerFactory")
	public List<UserDto> getUserTopic(UserDto user) {
		userDto.add(user);
		return userDto;
	}
}
