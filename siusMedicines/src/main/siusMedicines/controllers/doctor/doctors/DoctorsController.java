package siusMedicines.controllers.doctor.doctors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import siusMedicines.service.DoctorService;

@Controller
@RequestMapping("doctor/doctors")
public class DoctorsController {
	
	private DoctorService doctorService = new DoctorService();
	
	@RequestMapping(method = RequestMethod.GET) 
	public ModelAndView preparePanel(ModelAndView modelAndView) {
		modelAndView.addObject("doctors", doctorService.findAll());
		return modelAndView;
	}

}
