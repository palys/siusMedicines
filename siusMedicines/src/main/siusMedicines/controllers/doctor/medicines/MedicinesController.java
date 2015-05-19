package siusMedicines.controllers.doctor.medicines;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import siusMedicines.model.Medicine;
import siusMedicines.service.MedicineService;

@Controller
@RequestMapping("doctor/medicines")
public class MedicinesController {
	
	public static MedicineService medicineService = new MedicineService();

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView prepareMedicinesPanel(ModelAndView modelAndView) {
		modelAndView.addObject("medicines", medicineService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView prepareMedicinesAddPanel(ModelAndView modelAndView) {
		modelAndView.addObject("medicine", new Medicine());
		return modelAndView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String medicinesAdd(@ModelAttribute("medicine") Medicine medicine, BindingResult bindingResult) {
		medicineService.persist(medicine);
		ModelAndView modelAndView = new ModelAndView("/doctor/medicines");
		modelAndView.addObject("msg", "Medicine successfully added.");
		return "redirect:/doctor/medicines";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String medicinesRemove(@RequestParam(value = "medicine_name", required = true) String medicineName,
			ModelAndView model) {
		
		medicineService.delete(medicineName);
		ModelAndView modelAndView = new ModelAndView("/doctor/medicines");
		modelAndView.addObject("msg", "Medicine removed");
		return "redirect:/doctor/medicines";
	}
}
