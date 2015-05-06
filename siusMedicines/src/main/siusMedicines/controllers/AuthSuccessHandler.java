package siusMedicines.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class AuthSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	
	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        // Get the role of logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();
        String prefix = "";
        
        if (role.contains("DOCTOR")) {
        	prefix = "/doctor";
        } else if (role.contains("PATIENT")) {
        	prefix = "/patient";
        } else {
        	return "/error/403";
        }
        
        String targetUrl = prefix + "/panel";

        return targetUrl;
    }

}
