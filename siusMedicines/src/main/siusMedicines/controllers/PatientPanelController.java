package siusMedicines.controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import siusMedicines.model.Portion;
import siusMedicines.model.Prescription;
import siusMedicines.service.UserService;

@Controller
@RequestMapping("patient")
public class PatientPanelController {
	
	int ELEMENTS_TO_DISPLAY = 4;
	UserService userService = new UserService();
	
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
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/historical", method = RequestMethod.GET)
	public ModelAndView prepareHistoricalPanel(ModelAndView modelAndView, Principal user) {
		List<List<Portion>> portions = evaluate(user);
		List<Portion> historical = getPortions(portions.get(0),portions.get(0).size());
		modelAndView.addObject("historical_portions", historical);
		modelAndView.addObject("historical_portions_count", historical.size());

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
						unchecked.add(portion);
						historical.add(portion);
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
				return o1.getTakeTime().compareTo(o2.getTakeTime());
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
	
	private List<Portion> getPortions(List<Portion> list, int number) {
		return list.subList(0, (list.size() > number)? number : list.size());
	}

}
