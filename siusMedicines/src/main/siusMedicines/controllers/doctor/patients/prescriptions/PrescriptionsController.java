package siusMedicines.controllers.doctor.patients.prescriptions;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import siusMedicines.model.Medicine;
import siusMedicines.model.Patient;
import siusMedicines.model.Prescription;
import siusMedicines.service.PatientService;
import siusMedicines.service.PrescriptionService;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("doctor/patients/prescriptions")
public class PrescriptionsController {
	
	private PrescriptionService prescriptionService = new PrescriptionService();
	
	private PatientService patientService = new PatientService();
	
	private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView preparePrescriptions(@RequestParam(value = "patient_id", required = true) String patientId, ModelAndView modelAndView) {
		
		Patient patient = patientService.findById(Long.parseLong(patientId));
		//Collection<Prescription> prescriptions = Lists.newArrayList(new Prescription(0l, null, null, Collections.EMPTY_SET, new Medicine("Lek1", Collections.EMPTY_SET)),
		//		new Prescription(0l, null, null, Collections.EMPTY_SET, new Medicine("Lek2", Collections.EMPTY_SET)));
		Collection<Prescription> prescriptions = patient.getPrescriptions();
		Collection<PrescriptionHolder> holders = preparePrescriptions(prescriptions);
		modelAndView.addObject("prescriptions", holders);
		return modelAndView;
	}
	
	private List<PrescriptionHolder> preparePrescriptions(Collection<Prescription> prescriptions) {
		List<PrescriptionHolder> holders = new LinkedList<PrescriptionHolder>();
		
		for (Prescription p : prescriptions) {
			PrescriptionHolder h = new PrescriptionHolder();
			h.setPrescription(p);
			h.setPortionsLeft(0);
			h.setTotalPortionsNumber(10);
			h.setNextPortion(format.format(new Date(System.currentTimeMillis())));
			holders.add(h);
		}
		
		return holders;
	}
}