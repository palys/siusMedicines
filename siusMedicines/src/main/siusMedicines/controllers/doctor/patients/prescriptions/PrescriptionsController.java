package siusMedicines.controllers.doctor.patients.prescriptions;

import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import siusMedicines.model.Medicine;
import siusMedicines.model.Prescription;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("doctor/patients/prescriptions")
public class PrescriptionsController {

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView preparePrescriptions(ModelAndView modelAndView) {
		modelAndView.addObject("prescriptions", Lists.newArrayList(new Prescription(0l, null, null, Collections.EMPTY_SET, new Medicine("Lek1", Collections.EMPTY_SET)),
				new Prescription(0l, null, null, Collections.EMPTY_SET, new Medicine("Lek2", Collections.EMPTY_SET))));
		return modelAndView;
	}
}
