package siusMedicines.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("login")
public class LoginConrtoller {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView prepareLoginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			ModelAndView modelAndView) {
		
		if (error != null) {
			modelAndView.addObject("error", "Invalid username or password!");
		}
		
		if (logout != null) {
			modelAndView.addObject("msg", "You've been logged out successfully.");
		}
		
		return modelAndView;
	}

}
