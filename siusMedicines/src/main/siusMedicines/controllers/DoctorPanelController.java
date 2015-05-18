package siusMedicines.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import siusMedicines.model.Medicine;
import siusMedicines.service.MedicineService;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("doctor")
public class DoctorPanelController {
	
	public static MedicineService medicineService = new MedicineService();
	
	@RequestMapping(value = "/panel", method = RequestMethod.GET) 
	public ModelAndView preparePanel(ModelAndView modelAndView) {
		return modelAndView;
	}
	
	@RequestMapping(value = "/medicines", method = RequestMethod.GET)
	public ModelAndView prepareMedicinesPanel(ModelAndView modelAndView) {
		modelAndView.addObject("medicines", Lists.newArrayList(new Medicine("Medicine1"), new Medicine("OtherMedicine")));
		return modelAndView;
	}
	
	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public ModelAndView preparePatientsPanel(ModelAndView modelAndView) {
		return modelAndView;
	}
	
	@RequestMapping(value = "/medicines/add", method = RequestMethod.GET)
	public ModelAndView prepareMedicinesAddPanel(ModelAndView modelAndView) {
		modelAndView.addObject("medicine", new Medicine());
		return modelAndView;
	}
	
	@RequestMapping(value = "/medicines/add", method = RequestMethod.POST)
	public ModelAndView medicinesAdd(@ModelAttribute("medicine") Medicine medicine, BindingResult bindingResult) {
		System.out.println(medicine);
		ModelAndView modelAndView = new ModelAndView("/doctor/medicines");
		return prepareMedicinesPanel(modelAndView);
	}

}
