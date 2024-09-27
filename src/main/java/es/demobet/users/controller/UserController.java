package es.demobet.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.demobet.users.model.dto.UserDto;
import es.demobet.users.model.dto.request.RegisterRequest;
import es.demobet.users.model.entity.User;
import es.demobet.users.service.UserService;
import es.demobet.users.utils.ObjectMapperUtils;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
    public ResponseEntity<UserDto> create(@RequestBody RegisterRequest registerRequest) {
        User registeredUser = userService.create(registerRequest);

        return ResponseEntity.ok(ObjectMapperUtils.map(registeredUser, UserDto.class));
    }
	
}
