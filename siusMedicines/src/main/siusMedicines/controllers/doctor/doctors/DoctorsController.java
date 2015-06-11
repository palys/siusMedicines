package siusMedicines.controllers.doctor.doctors;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import siusMedicines.model.Doctor;
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
	
	@RequestMapping(value = "/add", method = RequestMethod.GET) 
	public ModelAndView prepareAddingPanel(ModelAndView modelAndView,
			@RequestParam(value = "doctor_id", required = false) String doctorId) {
		if (doctorId == null) {
			modelAndView.addObject("doctor", new Doctor());
		} else {
			modelAndView.addObject("doctor", doctorService.findById(Long.parseLong(doctorId)));
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addDoctor(@ModelAttribute("doctor") Doctor doctor,
			BindingResult bindingResult) {
		
		System.out.println(doctor);
		System.out.println(doctor.getUser());
		
		if (doctor.getId() != null) {
			doctorService.update(doctor);
		} else {
			doctor.getUser().setEnabled(true);
			doctor.getUser().setUserRole("ROLE_DOCTOR");
			doctorService.persist(doctor);
		}
		return "redirect:/doctor/doctors";
	}

}
