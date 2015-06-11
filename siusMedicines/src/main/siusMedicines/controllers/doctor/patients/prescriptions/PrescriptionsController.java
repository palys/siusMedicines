package siusMedicines.controllers.doctor.patients.prescriptions;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import siusMedicines.model.Doctor;
import siusMedicines.model.Medicine;
import siusMedicines.model.Patient;
import siusMedicines.model.Portion;
import siusMedicines.model.Prescription;
import siusMedicines.model.User;
import siusMedicines.service.MedicineService;
import siusMedicines.service.PatientService;
import siusMedicines.service.PortionService;
import siusMedicines.service.PrescriptionService;
import siusMedicines.service.UserService;

@Controller
@RequestMapping("doctor/patients/prescriptions")
public class PrescriptionsController {
	
	private PatientService patientService = new PatientService();
	
	private MedicineService medicineService = new MedicineService();
	
	private UserService userService = new UserService();
	
	private PrescriptionService prescriptionService = new PrescriptionService();
	
	private PortionService portionService = new PortionService();
	
	private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
 
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView preparePrescriptions(@RequestParam(value = "patient_id") String patientId, ModelAndView modelAndView) {
		Patient patient = patientService.findById(Long.parseLong(patientId));
		Collection<Prescription> prescriptions = patient.getPrescriptions();
		Collection<PrescriptionHolder> holders = preparePrescriptions(prescriptions);
		modelAndView.addObject("prescriptions", holders);
		modelAndView.addObject("medicine", new Medicine());
		modelAndView.addObject("medicines", medicineService.findAll());
		modelAndView.addObject("patient_id", patientId);
		return modelAndView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String prepareAddPrescriptions(@RequestParam(value = "patient_id") String patientId, @ModelAttribute("medicine") Medicine medicine, BindingResult bindingResult, Principal user) {
		
		Medicine med = medicineService.findById(medicine.getName());
		Prescription prescription = new Prescription();
		prescription.setMedicine(med);
		
		User u = userService.findById(user.getName());
		Doctor d = u.getDoctors().iterator().next();
		prescription.setDoctor(d);
		
		Patient p = patientService.findById(Long.parseLong(patientId));
		prescription.setPatient(p);
		
		prescriptionService.persist(prescription);
		
		return "redirect:/doctor/patients/prescriptions?patient_id=" + patientId;
	}
	
	@RequestMapping(value = "/portions", method = RequestMethod.GET)
	public ModelAndView preparePortionsPage(@RequestParam(value = "patient_id") String patientId,
			@RequestParam(value = "prescription_id") String prescriptionId,
			Principal user, ModelAndView modelAndView) {
		Prescription prescription = prescriptionService.findById(Long.parseLong(prescriptionId));
		modelAndView.addObject("prescription", prescription);
		modelAndView.addObject("portions", preparePortionHolders(prescription.getPortions()));
		return modelAndView;
	}
	
	@RequestMapping(value = "/portions/add", method = RequestMethod.GET)
	public ModelAndView preparePortionsAddPage(@RequestParam(value = "patient_id") String patientId,
			@RequestParam(value = "prescription_id") String prescriptionId,
			Principal user, ModelAndView modelAndView) {
		Prescription prescription = prescriptionService.findById(Long.parseLong(prescriptionId));
		modelAndView.addObject("prescription", prescription);
		modelAndView.addObject("portionWithFrequencyHolder", new PortionWithFrequencyHolder());
		return modelAndView;
	}
	
	@RequestMapping(value = "/portions/add", method = RequestMethod.POST)
	public String portionsAdd(@ModelAttribute("portionWithFrequencyHolder") PortionWithFrequencyHolder p,
			BindingResult bindingResult) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		long startTime = 0;
		
		try {
			startTime = df.parse(p.getFirstPortionDate().replace('T', ' ')).getTime();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Prescription prescription = prescriptionService.findById(Long.parseLong(p.getPrescriptionId()));
		for (long i = 0; i < p.getPortionsNumber(); i++) {


			Portion portion = new Portion();
			portion.setPrescription(prescription);
			portion.setSize(Double.parseDouble(p.getSize()));
			portion.setTaken(false);
			portion.setDeclined(false);
			portion.setDeclineReason("");
			portion.setUnit(p.getUnit());
			portion.setTakeTime(new Timestamp(startTime + (i * 1000 * 60 * 60 * 24)));
			portionService.persist(portion);

		}

		return "redirect:/doctor/patients/prescriptions/portions?patient_id=" +
				p.getPatientId() + "&prescription_id=" + p.getPrescriptionId();
	}
	
	private List<PortionsHolder> preparePortionHolders(Collection<Portion> portions) {
		List<PortionsHolder> holders = new ArrayList<PortionsHolder>();
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		DecimalFormat df = new DecimalFormat("#.##");
		
		for (Portion p : portions) {
			PortionsHolder h = new PortionsHolder();
			h.setId(p.getId());
			h.setUnit(p.getUnit());
			h.setSize(df.format(p.getSize()));
			h.setTakeTime(p.getTakeTime());
			h.setTaken(p.isTaken());
			h.setShouldBeTaken(!p.isTaken() && currentTimestamp.after(p.getTakeTime()));
			h.setShowWarning(!h.isTaken() && h.isShouldBeTaken() && !p.isDeclined());
			h.setDeclined(p.isDeclined());
			h.setReason(p.getDeclineReason());
			
			holders.add(h);
		}
		
		Collections.sort(holders);
		return holders;
	}
	
	private List<PrescriptionHolder> preparePrescriptions(Collection<Prescription> prescriptions) {
		List<PrescriptionHolder> holders = new LinkedList<PrescriptionHolder>();
		
		for (Prescription p : prescriptions) {
			PrescriptionHolder h = new PrescriptionHolder();
			h.setPrescription(p);
			h.setPortionsLeft(p.portionsLeft());
			h.setTotalPortionsNumber(p.totalPortions());
			h.setNextPortion(format.format(p.nextPortionDate()));
			h.setHasUntakenPortions(p.hasUntakenPortions());
			
			holders.add(h);
		}
		
		return holders;
	}
}