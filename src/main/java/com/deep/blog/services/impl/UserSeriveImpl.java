package com.deep.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deep.blog.entities.User;
import com.deep.blog.exceptions.ResourceNotFoundException;
import com.deep.blog.payloads.UserDto;
import com.deep.blog.repositories.UserRepo;
import com.deep.blog.services.UserService;


@Service
public class UserSeriveImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
	    User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
//	    if(user == null) {
//	    	throw new ResourceNotFoundException("User", "id", userId);
//	    }
	    user.setName(userDto.getName());
	    user.setEmail(userDto.getEmail());
	    user.setAbout(userDto.getAbout());
	    user.setPassword(userDto.getPassword());
	    User updatedUser = this.userRepo.save(user);
	    return this.userToDto(updatedUser);
	}
	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = this.userRepo.findAll();
		List<UserDto> allUserDtos = allUsers.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return allUserDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);
	}
	
	
//	ModelMapper used here for converting user to userDto and vice-versa.
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}
	
	

}
