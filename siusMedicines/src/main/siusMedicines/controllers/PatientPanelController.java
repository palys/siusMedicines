package siusMedicines.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("patient")
public class PatientPanelController {
	
	@RequestMapping(value = "/panel", method = RequestMethod.GET)
	public ModelAndView preparePanel(ModelAndView modelAndView) {
		return modelAndView;
	}

}
