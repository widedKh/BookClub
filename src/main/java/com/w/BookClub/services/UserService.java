package com.w.BookClub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.w.BookClub.repositories.UserRepository;
import com.w.BookClub.models.LoginUser;
import com.w.BookClub.models.User;


@Service
public class UserService {
    
	@Autowired
	UserRepository userRepository;
	
	public User register(User newUser, BindingResult result) {
        // TO-DO: Additional validations!
    	Optional<User> potentialUser= userRepository.findByEmail(newUser.getEmail());
    	if(potentialUser.isPresent()) {
    		result.rejectValue("email", "registerError", "Email is taken!");
    	} 
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    		 result.rejectValue("password", "registerError", "Password does not match!");
    	}
    	if(result.hasErrors()) {
    		return null;
    	}
    	else {
    		String hashdPW = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashdPW);
			return userRepository.save(newUser);
    	}
    	
        
    }
    public User login(LoginUser newLoginObject, BindingResult result) {
        // TO-DO: Additional validations!
    	Optional<User> potentialUser= userRepository.findByEmail(newLoginObject.getEmail());
    	if(!potentialUser.isPresent()) {
			result.rejectValue("email", "loginErrors", "Email is not found");
    	}
    	else {
			User user = potentialUser.get();
			// Check password
			if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword() )) {
				result.rejectValue("password", "loginErrors", "Invalid Password");
			}
			if(result.hasErrors()) {
				return null;
			}else {
				return user;
			}
		}
        return null;
    }
    
	//   READ ONE user
    public User findUser(Long id) {
    	Optional<User> user = userRepository.findById(id);
    	if(user.isPresent()) {
    		return user.get();
    	} else {
    		return null;
    	}
}
   
}

