package siusMedicines.controllers.doctor.doctors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("doctor/doctors")
public class DoctorsController {
	
	@RequestMapping(method = RequestMethod.GET) 
	public ModelAndView preparePanel(ModelAndView modelAndView) {
		return modelAndView;
	}

}
