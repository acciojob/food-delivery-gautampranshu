package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.model.response.UserResponse;
import com.driver.service.impl.UserServiceImpl;
import com.driver.shared.dto.OrderDto;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserServiceImpl us;

	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
		UserDto udto = us.getUserByUserId(id);
		//dto to responsedto
		UserResponse responseDto = UserResponse.builder().userId(udto.getUserId()).email(udto.getEmail()).firstName(udto.getFirstName()).lastName(udto.getLastName()).build();
		return responseDto;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		//convert requestdto to dto
		UserDto udto = UserDto.builder().firstName(userDetails.getFirstName()).lastName(userDetails.getLastName()).email(userDetails.getEmail()).build();
		//dto to responsedto
		UserResponse responseDto = UserResponse.builder().userId(udto.getUserId()).email(udto.getEmail()).firstName(udto.getFirstName()).lastName(udto.getLastName()).build();
		return responseDto;
		//return null;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto udto =  UserDto.builder().firstName(userDetails.getFirstName()).lastName(userDetails.getLastName()).email(userDetails.getEmail()).build();
		udto = us.updateUser(id , udto);
		//dto to responsedto
		UserResponse responseDto = UserResponse.builder().userId(udto.getUserId()).email(udto.getEmail()).firstName(udto.getFirstName()).lastName(udto.getLastName()).build();
		return responseDto;
	}
		//return null;

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{
		us.deleteUser(id);
		return null;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){
		List<UserDto> udto = us.getUsers();
		List<UserResponse> ans = new ArrayList<>();
		int n = udto.size();
		for(int i=0; i<n; i++)
		{
			//dto to responsedto
			UserResponse responseDto = UserResponse.builder().userId(udto.get(i).getUserId()).email(udto.get(i).getEmail()).firstName(udto.get(i).getFirstName()).lastName(udto.get(i).getLastName()).build();
			ans.add(responseDto);
		}
		return ans;
		//return null;
	}
	
}
