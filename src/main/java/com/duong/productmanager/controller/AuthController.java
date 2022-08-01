package com.duong.productmanager.controller;

import com.duong.productmanager.entity.AppRole;
import com.duong.productmanager.entity.AppUser;
import com.duong.productmanager.entity.ERole;
import com.duong.productmanager.entity.ProfileUser;
import com.duong.productmanager.entity.dto.UserPrincipal;
import com.duong.productmanager.payload.request.LoginRequest;
import com.duong.productmanager.payload.request.SignupRequest;
import com.duong.productmanager.payload.response.JwtResponse;
import com.duong.productmanager.payload.response.MessageResponse;
import com.duong.productmanager.service.jwt.JwtService;
import com.duong.productmanager.service.profileusers.IProfileUsersService;
import com.duong.productmanager.service.role.IRoleService;
import com.duong.productmanager.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IProfileUsersService profileUsersService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser (@Valid @RequestBody SignupRequest signupRequest) {
        if(userService.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        if (userService.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already use"));
        }
        AppUser user = new AppUser(signupRequest.getUsername(), signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()));
        String strRole = signupRequest.getRole();
        Set<AppRole> roleSet = new HashSet<>();

        if (strRole == null) {
            AppRole userRole = roleService.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roleSet.add(userRole);
        } else if (strRole.equals("admin")) {
            AppRole userRole = roleService.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roleSet.add(userRole);
        } else if (strRole.equals("moderator")) {
            AppRole userRole = roleService.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roleSet.add(userRole);
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse( "This role doesn't exits!"));
        }
        user.setRoles(roleSet);
        userService.save(user);

        ProfileUser profileUser = new ProfileUser(user);
        profileUsersService.save(profileUser);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        Optional<AppUser> currentUser = userService.findByUsername(loginRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.get().getId(), userDetails.getUsername(), currentUser.get().getUsername(), userDetails.getEmail() ,userDetails.getAuthorities()));

    }
}
