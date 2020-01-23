package com.example.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/producer")
public class UserResources {
	private static final String TOPIC="user-stream"; 
	@Autowired
	private UserService userService;
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;


	@GetMapping(path = "/message")
	public String sendMessage() {
		kafkaTemplate.send(TOPIC, "welcome");
		return "DataHasBeenPublish";
	}

	//save the user data into db and procuce the response entity in kafka cluster
	//and return entityResponse to the client
	@PostMapping(path ="/save",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> saveUser(@RequestBody UserDto userDto){

		boolean falg=userService.saveUser(userDto);
		kafkaTemplate.send(TOPIC, userDto);
		if(falg) {
			ResponseEntity<UserResponse> resp=ResponseEntity.status(HttpStatus.OK).body(new UserResponse(200,"your data saved sucessfully", userDto));

			return resp;
		}else {
			ResponseEntity<UserResponse> resp=ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse(404,"your data is not stored in db", userDto)); 

			return resp;
		}
	}

	//get user data based on the primary key and produce the Response entity in kafka cluster 
	// and return Response entity along with user data to the client

	@GetMapping(path ="/getUser/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserResponse> getUser(@PathVariable("id") int id){
		UserDto userDto = null;

		userDto=userService.getUser(id);
		kafkaTemplate.send(TOPIC, userDto);
		if(userDto!=null) {
			ResponseEntity<UserResponse> userResponse=ResponseEntity.status(HttpStatus.OK).body(new UserResponse(200, "your data is", userDto));

			return userResponse;
		}else {
			ResponseEntity<UserResponse> userResponse = ResponseEntity.status(HttpStatus.NO_CONTENT).body(new UserResponse(204, "primary key is not valid ",userDto));	

			return userResponse;
		}
	}
}
