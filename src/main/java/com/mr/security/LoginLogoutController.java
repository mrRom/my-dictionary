/**
 * 
 */
package com.mr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mr.user.bo.UserBo;
import com.mr.user.model.DUser;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
public class LoginLogoutController {
	/**
	 * Handles and retrieves the login JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@Autowired
	MessageSource messageSource;
	@Autowired
	private UserBo userBo;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error,
								@RequestParam(value="registered", required=false) boolean registered,
								@RequestParam(value="uId", required=false) Integer uId,
								@RequestParam(value="p", required=false) String pass,
								ModelMap model) {
		// Add an error message to the model if login is unsuccessful
		// The 'error' parameter is set to true based on the when the authentication has failed. 
		
		//for the registration confirmation
		if (registered == true) {
			DUser user = userBo.loadUserById(uId);
			if(!user.equals(null)){
				if (user.getUserPassword().equals(pass)){
					user.setAccess(2);
					userBo.update(user);
				}
			}
		}
		//if error
		if (error == true) {
			// Assign an error message
			String message = messageSource.getMessage("label.loginpassworderror", null, LocaleContextHolder.getLocale());
			model.put("error", message);
		} else {
			model.put("error", "");
		}
		
		// This will resolve to /WEB-INF/jsp/loginpage.jsp
		return "loginpage";
	}
	
	/**
	 * Handles and retrieves the denied JSP page. This is shown whenever a regular user
	 * tries to access an admin only page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
 	public String getDeniedPage() {		
		// This will resolve to /WEB-INF/jsp/deniedpage.jsp
		return "deniedpage";
	}
}