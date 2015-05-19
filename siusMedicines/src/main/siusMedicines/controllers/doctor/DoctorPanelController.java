package siusMedicines.controllers.doctor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("doctor")
public class DoctorPanelController {
	
	@RequestMapping(value = "/panel", method = RequestMethod.GET) 
	public ModelAndView preparePanel(ModelAndView modelAndView) {
		return modelAndView;
	}
	
	

}
