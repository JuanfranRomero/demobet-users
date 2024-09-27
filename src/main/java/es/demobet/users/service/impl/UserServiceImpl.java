package es.demobet.users.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.demobet.users.model.dto.request.RegisterRequest;
import es.demobet.users.model.entity.User;
import es.demobet.users.repository.UserRepository;
import es.demobet.users.service.UserService;
import es.demobet.users.utils.ObjectMapperUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User create(RegisterRequest registerRequest) {
		User user = ObjectMapperUtils.map(registerRequest, User.class);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        return userRepository.save(user);
	}
	
}
