package es.demobet.users.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.demobet.users.model.dto.request.RegisterRequest;
import es.demobet.users.model.entity.User;
import es.demobet.users.repository.UserRepository;
import es.demobet.users.service.UserService;
import es.demobet.users.utils.EndpointConstants;
import es.demobet.users.utils.ObjectMapperUtils;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RestTemplate restTemplate;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RestTemplate restTemplate) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.restTemplate = restTemplate;
	}

	@Override
	public User create(RegisterRequest registerRequest) {
		User user = ObjectMapperUtils.map(registerRequest, User.class);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        
        User createdUser = userRepository.save(user);
        
        if (createdUser != null) {
        	// Invoke wallet micro-service to init user wallet.
            restTemplate.postForEntity(EndpointConstants.WALLET_INIT_URL, null, null, createdUser.getId());
        }

        return userRepository.save(user);
	}
	
}
