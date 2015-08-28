package com.mr.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mr.user.bo.UserBo;
import com.mr.user.model.DUser;
import com.mr.util.emails.ValidationMailSender;
import com.mr.util.validation.RegistrationValidation;

@RequestMapping("/userRegistration")
@Controller
@SessionAttributes
public class UserController{
	
	 @Autowired
     private RegistrationValidation registrationValidation;
	 @Autowired
	 private PasswordEncoder encoder;
	 @Autowired
	 private JavaMailSender jms;
	 
     public void setRegistrationValidation(
                     RegistrationValidation registrationValidation) {
             this.registrationValidation = registrationValidation;
     }

     // Display the form on the get request
     @RequestMapping(method = RequestMethod.GET)
     public String showRegistration(Model model) {
             model.addAttribute("user", new DUser());
             return "registration";
     }

     @Autowired
     private UserBo userBo;
     // Process the form.
     @RequestMapping(method = RequestMethod.POST)
     public String processRegistration(@ModelAttribute("user")DUser user,
                     BindingResult result) {
             // set custom Validation by user
             registrationValidation.validate(user, result);
             if (result.hasErrors()) {
                     return "registration";
             }
     user.setUserPassword(encoder.encode(user.getUserPassword()));
     user.setAccess(-1);
     userBo.save(user);
     
     //sending e-mail for confirmation of the registration
     int id = user.getUserId();
     String pass = user.getUserPassword();
     String subject = "Confirm your registration!";
     String messageBody = "<a href=\"http://localhost:8080/Dictionary/login?registered=true&uId="+id+"&p="+pass+"\">click here</a>";
     ValidationMailSender vms= new ValidationMailSender(jms, "romanyukmikhail@gmail.com", user.getUserEmail(), subject, messageBody);
     vms.sendConfirmationMail();
     
             return "registrationcomplete";
     }
}

