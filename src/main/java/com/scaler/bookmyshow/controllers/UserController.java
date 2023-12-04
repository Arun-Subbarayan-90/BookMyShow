package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dto.ResponseStatus;
import com.scaler.bookmyshow.dto.UserSignupRequestDTO;
import com.scaler.bookmyshow.dto.UserSignupResponseDTO;
import com.scaler.bookmyshow.models.User;
import com.scaler.bookmyshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    public UserSignupResponseDTO Signup(UserSignupRequestDTO userSignupRequestDTO){
        User user;
        UserSignupResponseDTO userSignupResponseDTO = new UserSignupResponseDTO();
        try{
            user = userService.Signup(userSignupRequestDTO.getEmail(),userSignupRequestDTO.getPassword());
        }
        catch (Exception e){
            userSignupResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
            return userSignupResponseDTO;
        }
        userSignupResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        userSignupResponseDTO.setUserId(user.getId());
        return userSignupResponseDTO;
    }
}
