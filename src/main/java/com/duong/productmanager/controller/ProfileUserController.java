package com.duong.productmanager.controller;

import com.duong.productmanager.entity.AppUser;
import com.duong.productmanager.entity.ProfileUser;
import com.duong.productmanager.payload.request.ProfileUserRequest;
import com.duong.productmanager.payload.response.MessageResponse;
import com.duong.productmanager.service.profileusers.IProfileUsersService;
import com.duong.productmanager.service.profileusers.ProfileUsersService;
import com.duong.productmanager.service.user.IUserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/profile")
public class ProfileUserController {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(ProfileUserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IProfileUsersService profileUsersService;

    @PostMapping("/{username}")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody ProfileUserRequest profileUserRequest, @PathVariable String username, BindingResult result) {
        ProfileUser currentProfile = profileUsersService.findByAppUser_Username(username);
        String address = profileUserRequest.getAddress();
        String phone = profileUserRequest.getPhone();
        String dateOfBirth = profileUserRequest.getDateOfBirth();
        if (result.hasErrors()) {
            logger.info(result.toString());

            return ResponseEntity.ok(new MessageResponse(result.toString()));
        } else {
            currentProfile.setAddress(address);
            currentProfile.setPhone(phone);
            currentProfile.setDateOfBirth(dateOfBirth);
            profileUsersService.save(currentProfile);
            Gson gson = new Gson();
            String jsonProfile = gson.toJson(currentProfile);
            logger.info("==========>>>>>" + jsonProfile);
            return ResponseEntity.ok(new MessageResponse("Successfully saved"));
        }
    }
}
