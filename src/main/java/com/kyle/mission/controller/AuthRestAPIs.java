package com.kyle.mission.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

import com.kyle.mission.message.request.LoginForm;
import com.kyle.mission.message.request.SignUpForm;
import com.kyle.mission.message.response.JwtResponse;
import com.kyle.mission.model.Role;
import com.kyle.mission.model.RoleName;
import com.kyle.mission.model.User;
import com.kyle.mission.repository.RoleRepository;
import com.kyle.mission.repository.UserRepository;
import com.kyle.mission.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = {"1. 회원 정보 API"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @ApiOperation(value = "토큰 생성", notes = "회원의 토큰을 생성한다.")
    @PostMapping("/v1/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody @ApiParam(value = "로그인정보", required = true) LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @ApiOperation(value = "토큰 갱신", notes = "회원의 토큰을 갱신한다.")
    @PostMapping("/v1/refreshToken")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> refreshToken(@ApiParam(value = "토큰정보", required = true) Authentication authentication) {

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @ApiOperation(value = "회원 가입", notes = "회원을 가입한다.")
    @PostMapping("/v1/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody @ApiParam(value = "회원가입정보", required = true) SignUpForm signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
        	switch(role) {
	    		case "admin":
	    			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	    			roles.add(adminRole);
	    			
	    			break;
	    		case "pm":
	            	Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	            	roles.add(pmRole);
	            	
	    			break;
	    		default:
	        		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	        		roles.add(userRole);        			
        	}
        });
        
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok().body("User registered successfully!");
    }
}