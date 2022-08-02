package com.duong.productmanager.controller;

import com.duong.productmanager.entity.AppUser;
import com.duong.productmanager.entity.ProfileUser;
import com.duong.productmanager.payload.request.ChangePasswordForm;
import com.duong.productmanager.payload.request.ProfileUserRequest;
import com.duong.productmanager.payload.request.ResetPasswordForm;
import com.duong.productmanager.payload.response.MessageResponse;
import com.duong.productmanager.service.profileusers.IProfileUsersService;
import com.duong.productmanager.service.profileusers.ProfileUsersService;
import com.duong.productmanager.service.user.IUserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/{username}")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody ProfileUserRequest profileUserRequest, @PathVariable String username, BindingResult result) {
        Optional<ProfileUser> currentProfile = profileUsersService.findByAppUser_Username(username);
        if (!currentProfile.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("user not exits!"));
        }
        String address = profileUserRequest.getAddress();
        String phone = profileUserRequest.getPhone();
        String dateOfBirth = profileUserRequest.getDateOfBirth();
        if (result.hasErrors()) {
            logger.info(result.toString());

            return ResponseEntity.badRequest().body(new MessageResponse(result.toString()));
        } else {
            currentProfile.get().setAddress(address);
            currentProfile.get().setPhone(phone);
            currentProfile.get().setDateOfBirth(dateOfBirth);
            profileUsersService.save(currentProfile.get());
            Gson gson = new Gson();
            String jsonProfile = gson.toJson(currentProfile);
            logger.info("==========>>>>>" + jsonProfile);
            return ResponseEntity.ok(new MessageResponse("Successfully saved"));
        }
    }

    @PostMapping("/changePassword/{username}")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordForm changePasswordForm, @PathVariable String username, BindingResult result) {
        Optional<AppUser> currentUser = userService.findByUsername(username);
        if(!currentUser.isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse("user not exits"));
        }
        String oldPassword = changePasswordForm.getOldPassword();
        String password = changePasswordForm.getPassword();
        String confirmPassword = changePasswordForm.getConfirmPassword();
        if(!passwordEncoder.matches(oldPassword, currentUser.get().getPassword())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Your old password is wrong!"));
        }
        if (result.hasErrors()) {
            logger.info("Error");
            return ResponseEntity.badRequest().body(new MessageResponse("Password invalid"));
        }
        if (!password.equals(confirmPassword)) {
            logger.info("Password not match!");
            return ResponseEntity.badRequest().body(new MessageResponse("Password not match"));
        } else {
            currentUser.get().setPassword(passwordEncoder.encode(password));
            userService.save(currentUser.get());
            return ResponseEntity.ok(new MessageResponse("Password changed successfully"));
        }
    }
    
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword (@Valid @RequestBody ResetPasswordForm resetPasswordForm) throws Exception {
        String newPassword = resetPasswordForm.getPassword();
        String confirmPassword = resetPasswordForm.getConfirmPassword();
        String username = resetPasswordForm.getUsername();
        Iterable<AppUser> users = userService.findAll();
        Optional<AppUser> user = userService.findByUsername(username);
        logger.info("LIST USER" + users.iterator().next());
        Iterator listUser = users.iterator();
        try {
            while (listUser.hasNext()){
                if(user.get().equals(listUser.next())) {
                    if(newPassword.equals(confirmPassword)) {
                        user.get().setPassword(passwordEncoder.encode(newPassword));
                        return ResponseEntity.ok("Password saved");
                    } else {
                        return ResponseEntity.badRequest().body("Password not match");
                    }
                }
            }
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(new MessageResponse("user not found"));
        }
        return null;
    }
}
