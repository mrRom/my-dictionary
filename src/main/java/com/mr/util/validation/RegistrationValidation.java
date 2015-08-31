package com.mr.util.validation;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.mr.user.bo.UserBo;
import com.mr.user.model.DUser;

@Service
public class RegistrationValidation {
	@Autowired
	UserBo userBo;
	
	public boolean supports(Class<?> clazz) {
	    return DUser.class.isAssignableFrom(clazz);
	  }

	  public void validate(Object target, Errors errors) {
		DUser user = (DUser) target;
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
	        "NotEmpty.registration.userName",
	        "User Name must not be Empty.");
	    String userName = user.getUserName();
	    if ((userName.length()) > 50) {
	      errors.rejectValue("userName",
	          "lengthOfUser.registration.userName",
	          "User Name must not more than 50 characters.");
	    }
	    if (!(userBo.loadUserByUsername(user.getUserName())==null)){
	    	errors.rejectValue("userName",
			          "UserExist.registration.user",
			          "User with this name is already exist. Please, choose another name.");
	    }
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", 
	    		"NotEmpty.registration.password", "Password must not be Empty.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userConfirmPassword", 
	    		"NotEmpty.registration.confirmPassword", "Confirm Password must not be Empty.");
	    if (user.getUserPassword().length() < 4) {
		      errors.rejectValue("userPassword",
		          "Size.registration.password",
		          "Password must be between 4 to 20 characters.");
		}
	    if (!(user.getUserPassword()).equals(user
	        .getUserConfirmPassword())) {
	      errors.rejectValue("userPassword",
	          "matchingPassword.registration.userPassword",
	          "Password and Confirm Password Not match.");
	    }
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userEmail", 
	    		"NotEmpty.registration.email", "Email must not be Empty.");
	    String email_pattern = 
    			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	    if (!(Pattern.compile(email_pattern).matcher(user.getUserEmail()).matches())) {
		      errors.rejectValue("userEmail",
		          "Wrongpattern.registration.email",
		          "Please, enter correct email address.");
		    }
	  }
	}

