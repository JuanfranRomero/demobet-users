package es.demobet.users.service;

import es.demobet.users.model.dto.request.RegisterRequest;
import es.demobet.users.model.entity.User;

public interface UserService {

	public User create(RegisterRequest registerRequest);
	
}
