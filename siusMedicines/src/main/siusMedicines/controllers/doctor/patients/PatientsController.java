package siusMedicines.controllers.doctor.patients;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import siusMedicines.model.Patient;
import siusMedicines.service.PatientService;
import siusMedicines.service.UserService;

@Controller
@RequestMapping("doctor/patients")
public class PatientsController {
	
	public static PatientService patientService = new PatientService();
	
	public static UserService userService = new UserService();

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView preparePatientsPanel(ModelAndView modelAndView) {
		modelAndView.addObject("patients", patientService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView preparePatientsAddPanel(@RequestParam(value = "patient_id", required = false) String patientId, ModelAndView modelAndView) {
		if (patientId == null) {
			modelAndView.addObject("patient", new Patient());
		} else {
			modelAndView.addObject("patient", patientService.findById(Long.parseLong(patientId)));
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String patientsAdd(@ModelAttribute("patient") Patient patient, BindingResult bindingResult) {
		if (userService.findById(patient.getUser().getUsername()) != null) {
			patientService.update(patient);
		} else {
			patient.getUser().setEnabled(true);
			patient.getUser().setUserRole("ROLE_PATIENT");
			patientService.persist(patient);
			ModelAndView modelAndView = new ModelAndView("/doctor/patients");
			modelAndView.addObject("msg", "Patient successfully added.");
		}
		return "redirect:/doctor/patients";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String patientRemove(@RequestParam(value = "patient_id", required = true) Long id,
			ModelAndView model) {
		
		patientService.delete(id);
		ModelAndView modelAndView = new ModelAndView("/doctor/patients");
		modelAndView.addObject("msg", "Patients removed");
		return "redirect:/doctor/patients";
	}
	
}
