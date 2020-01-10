package com.example.demo.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;

@RestController
@RequestMapping(path ="consumer")
public class UserResources {
	User user=null;
	
	@GetMapping("/consumeJsonMessage")
	public User consumeJsonMessage() {
		return user;
	}
  // it consumes the data from the kafka broker 
	
	@KafkaListener(groupId = "user-1", topics = "user", containerFactory = "consumerFactory")
	public List<User> getUserTopic(User user) {
		List<User> l=new ArrayList<>();
		l.add(user);
		return l;
	}
}
