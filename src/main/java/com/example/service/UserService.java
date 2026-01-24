package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	public UserDTO addUser(UserDTO userDTO) {
		User savedUser = repo.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
		
		return UserMapper.INSTANCE.mapUserToUserDTO(savedUser);
	}
	
    public ResponseEntity<UserDTO> fetchUserDetailsById(Integer userId) {
        Optional<User> fetchedUser =  repo.findById(userId);
        if(fetchedUser.isPresent())
            return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(fetchedUser.get()), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

   }

}
