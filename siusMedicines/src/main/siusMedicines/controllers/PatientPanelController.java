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
	private List<Portion> historicalPortions = new LinkedList<>();
	int historicalPortionsCount;
	private List<Portion> uncheckedPortions = new LinkedList<>();
	int uncheckedPortionsCount;
	private List<Portion> scheduledPortions = new LinkedList<>();
	int scheduledPortionsCount;
	
	@RequestMapping(value = "/panel", method = RequestMethod.GET)
	public ModelAndView preparePanel(ModelAndView modelAndView, Principal user) {
		modelAndView.addObject("patient_name", user.getName());
		evaluate(user);
		modelAndView.addObject("scheduled_portions", getNearestPortions(ELEMENTS_TO_DISPLAY));
		modelAndView.addObject("unchecked_portions", getUncheckedPortions(ELEMENTS_TO_DISPLAY));
		modelAndView.addObject("scheduled_portions_count", scheduledPortionsCount);
		modelAndView.addObject("unchecked_portions_count", uncheckedPortionsCount);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/historical", method = RequestMethod.GET)
	public ModelAndView prepareHistoricalPanel(ModelAndView modelAndView) {
		modelAndView.addObject("historical_portions", getHistoricalPortions());
		modelAndView.addObject("historical_portions_count", historicalPortionsCount);

		return modelAndView;
	}
	
	private void evaluate(Principal user) {
		
		historicalPortions = new LinkedList<>();
		uncheckedPortions = new LinkedList<>();
		scheduledPortions = new LinkedList<>();
		
		List<Portion> portionsToTake = new LinkedList<>();
		List<Portion> historical = new LinkedList<>();
		List<Portion> unchecked_p = new LinkedList<>();
		
		Set<Prescription> prescriptions = userService.findById(user.getName()).getPatients().iterator().next().getPrescriptions();
		for(Prescription p : prescriptions) {
			Set<Portion> portions = p.getPortions();
			for(Portion portion : portions) {
				if(portion.isTaken()) {
					historical.add(portion);
				} else {
					Timestamp t = new Timestamp(System.currentTimeMillis());
					if(portion.getTakeTime().before(t)) {
						unchecked_p.add(portion);
					} else {
						portionsToTake.add(portion);
					}
				}
			}
		}
		
		Collections.sort(portionsToTake, new Comparator<Portion>() {

			@Override
			public int compare(Portion o1, Portion o2) {
				return o1.getTakeTime().compareTo(o2.getTakeTime());
			}
			
		});
		Collections.sort(unchecked_p, new Comparator<Portion>() {

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
	
		System.out.println("h=" + historical.size() + " u=" + unchecked_p.size() + " s=" + portionsToTake.size());
		if(historical.size() != 0) {
			historicalPortions.addAll(historical);
		}
		if(unchecked_p.size() != 0) {
				uncheckedPortions.addAll(unchecked_p);
		}
		if(portionsToTake.size() != 0) {
			scheduledPortions.addAll(portionsToTake);
		}
		
		historicalPortionsCount = historicalPortions.size();
		uncheckedPortionsCount = uncheckedPortions.size();
		scheduledPortionsCount = scheduledPortions.size();
	}
	
	private List<Portion> getNearestPortions(int number) {
		return scheduledPortions.subList(0, (scheduledPortions.size() > number)? number : scheduledPortions.size());
	}
	
	private List<Portion> getHistoricalPortions() {
		return historicalPortions;
	}
	
	private List<Portion> getUncheckedPortions(int number) {
		return uncheckedPortions.subList(0, (uncheckedPortions.size() > number)? number : uncheckedPortions.size());
	}

}
