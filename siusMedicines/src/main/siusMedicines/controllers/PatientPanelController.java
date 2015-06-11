package siusMedicines.controllers;

import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import siusMedicines.mail.MailUtils;
import siusMedicines.model.Doctor;
import siusMedicines.model.Medicine;
import siusMedicines.model.Patient;
import siusMedicines.model.Portion;
import siusMedicines.model.Prescription;
import siusMedicines.service.DoctorService;
import siusMedicines.service.PortionService;
import siusMedicines.service.UserService;

@Controller
@RequestMapping("patient")
public class PatientPanelController {
	
	private static class DataHolder {
		private String value;
		private Long id;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	int ELEMENTS_TO_DISPLAY = 4;
	UserService userService = new UserService();
	PortionService portionService = new PortionService();
	DoctorService doctorService = new DoctorService();
	
	@RequestMapping(value = "/panel", method = RequestMethod.GET)
	public ModelAndView preparePanel(ModelAndView modelAndView, Principal user) {
		modelAndView.addObject("patient_name", user.getName());
		List<List<Portion>> portions = evaluate(user);
		List<Portion> scheduled = getPortions(portions.get(2),ELEMENTS_TO_DISPLAY);
		List<Portion> unchecked = getPortions(portions.get(1),ELEMENTS_TO_DISPLAY);
		modelAndView.addObject("scheduled_portions", scheduled);
		modelAndView.addObject("unchecked_portions", unchecked);
		modelAndView.addObject("scheduled_portions_count", scheduled.size());
		modelAndView.addObject("unchecked_portions_count", unchecked.size());
		modelAndView.addObject("medicine_taken");
		modelAndView.addObject("rejection_reason", new DataHolder());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/panel", method = RequestMethod.POST)
	public ModelAndView handleRejectionReason(Principal user, @ModelAttribute("rejection_reason") DataHolder s, BindingResult bindingResult){
		System.out.println("rejection reason: " + s.getValue());
		System.out.println("portion id " + s.getId());
		Portion portion = portionService.findById(s.getId());
		System.out.println(portion);
		if(s.getValue() == null) {
			portion.setTaken(true);
			portionService.update(portion);
		} else {
			portion.setDeclined(true);
			portion.setDeclineReason(s.getValue());
			portionService.update(portion);
		}
		ModelAndView model = new ModelAndView("/patient/panel");
		return preparePanel(model, user);
	}
	
	@RequestMapping(value = "/historical", method = RequestMethod.GET)
	public ModelAndView prepareHistoricalPanel(ModelAndView modelAndView, Principal user) {
		List<List<Portion>> portions = evaluate(user);
		List<Portion> historical = getPortions(portions.get(0),portions.get(0).size());
		modelAndView.addObject("historical_portions", historical);
		modelAndView.addObject("historical_portions_count", historical.size());

		return modelAndView;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView prepareScheduledPanel(ModelAndView modelAndView, Principal user) {
		List<List<Portion>> portions = evaluate(user);
		List<Portion> scheduled = getPortions(portions.get(2),portions.get(2).size());
		modelAndView.addObject("scheduled_portions", scheduled);
		modelAndView.addObject("scheduled_portions_count", scheduled.size());

		return modelAndView;
	}
	
	@RequestMapping(value = "/personal", method = RequestMethod.GET)
	public ModelAndView preparePersonalDataPanel(ModelAndView modelAndView, Principal user) {
		Patient patient = userService.findById(user.getName()).getPatients().iterator().next();
		modelAndView.addObject("patient", patient);
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public ModelAndView prepareCalendarPanel(ModelAndView modelAndView, Principal user) {
		List<Map<String,?>> list = prepareCalendarData(user);
		Map<String,Integer> dailyCounts = (Map<String,Integer>)list.get(0);
		Map<String,List<Portion>> dailyPortions = (Map<String,List<Portion>>)list.get(1);
		modelAndView.addObject("dailyCounts", dailyCounts);
		modelAndView.addObject("dailyPortions", dailyPortions);
		return modelAndView;
	}
	
	@RequestMapping(value = "/medicines", method = RequestMethod.GET)
	public ModelAndView prepareMedicinesPanel(ModelAndView modelAndView, Principal user) {
		Set<Prescription> prescriptions = userService.findById(user.getName()).getPatients().iterator().next().getPrescriptions();
		List<Medicine> medicines = new LinkedList<>();
		for(Prescription p : prescriptions) {
			medicines.add(p.getMedicine());
		}
		modelAndView.addObject("medicines", medicines);
		modelAndView.addObject("medicines_count", medicines.size());
		return modelAndView;
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView prepareContactPanel(ModelAndView modelAndView, Principal user) {
		List<Doctor> doctors = doctorService.findAll();
		modelAndView.addObject("doctors", doctors);
		modelAndView.addObject("mail", new MailHolder());
		return modelAndView;
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String sendMail(@ModelAttribute("mail") MailHolder mail,
			BindingResult bindingResult, Principal principal) {

		String message = "Message sent from user " + principal.getName() + "\n" +
				mail.getMessage() + "\n\nSiusMadicines";
		MailUtils.sendMail(mail.getAddress(), mail.getSubject(), message);
		
		return "redirect:/patient/panel";
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public ModelAndView prepareSettingsPanel(ModelAndView modelAndView, Principal user) {

		return modelAndView;
	}
	
	private List<List<Portion>> evaluate(Principal user) {
		
		List<Portion> historical = new LinkedList<>();
		List<Portion> unchecked = new LinkedList<>();
		List<Portion> scheduled = new LinkedList<>();
		
		Set<Prescription> prescriptions = userService.findById(user.getName()).getPatients().iterator().next().getPrescriptions();
		for(Prescription p : prescriptions) {
			Set<Portion> portions = p.getPortions();
			for(Portion portion : portions) {
				if(portion.isTaken()) {
					historical.add(portion);
				} else {
					Timestamp t = new Timestamp(System.currentTimeMillis());
					if(portion.getTakeTime().before(t)) {
						if(portion.isDeclined()){
							historical.add(portion);
						} else {
							historical.add(portion);
							unchecked.add(portion);
						}
					} else {
						scheduled.add(portion);
					}
				}
			}
		}
		
		Collections.sort(scheduled, new Comparator<Portion>() {

			@Override
			public int compare(Portion o1, Portion o2) {
				return o1.getTakeTime().compareTo(o2.getTakeTime());
			}
			
		});
		Collections.sort(unchecked, new Comparator<Portion>() {

			@Override
			public int compare(Portion o1, Portion o2) {
				return o2.getTakeTime().compareTo(o1.getTakeTime());
			}
			
		});
		Collections.sort(historical, new Comparator<Portion>() {

			@Override
			public int compare(Portion o1, Portion o2) {
				return o2.getTakeTime().compareTo(o1.getTakeTime());
			}
			
		});
		
		LinkedList<List<Portion>> portions = new LinkedList<>();
		portions.add(historical);
		portions.add(unchecked);
		portions.add(scheduled);
		
		return portions;
	}
	
	private List<Map<String,?>> prepareCalendarData(Principal user){
		List<Map<String,?>> toReturn = new LinkedList<>();
		
		Map<String,Integer> dailyCounts = new HashMap<>();
		Map<String,List<Portion>> dailyPortions= new HashMap<>();
		
		Set<Prescription> prescriptions = userService.findById(user.getName()).getPatients().iterator().next().getPrescriptions();
		for(Prescription p : prescriptions) {
			Set<Portion> portions = p.getPortions();
			for(Portion portion : portions) {
				Timestamp t = portion.getTakeTime();
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(t.getTime());
				String year = Integer.toString(c.get(Calendar.YEAR));
				String month = (c.get(Calendar.MONTH) < 9 ? "0" : "") + Integer.toString(c.get(Calendar.MONTH) + 1);
				String day = (c.get(Calendar.DAY_OF_MONTH) < 10 ? "0" : "") + Integer.toString(c.get(Calendar.DAY_OF_MONTH));
				String date = year + "-" + month + "-" + day;
				
				if(dailyCounts.containsKey(date)){
					dailyCounts.put(date, dailyCounts.get(date) + 1);
				} else {
					dailyCounts.put(date,  1);
				}
				
				if(dailyPortions.containsKey(date)){
					List<Portion> l = dailyPortions.get(date);
					l.add(portion);
					dailyPortions.put(date, l);
				} else {
					List<Portion> l = new LinkedList<>();
					l.add(portion);
					dailyPortions.put(date, l);
				}
			}
		}
		
		toReturn.add(dailyCounts);
		toReturn.add(dailyPortions);
		
		return toReturn;
	}
	
	private List<Portion> getPortions(List<Portion> list, int number) {
		return list.subList(0, (list.size() > number)? number : list.size());
	}

}
