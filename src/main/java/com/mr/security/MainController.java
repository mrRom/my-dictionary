package com.mr.security;

import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles and retrieves the common or admin page depending on the URI template.
 * A user must be log-in first he can access these pages.  Only the admins can see
 * the adminpage.
 */
@Controller
public class MainController {
	//Handles and retrieves the index page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage() {

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN"))
            return "redirect:/admin";
        if (roles.contains("ROLE_USER"))
            return "index";
        else{
        	return "index";
        }
	}
    
    //Handles and retrieves the admin page
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage() {
    	
    	return "adminpage";
	}
}
