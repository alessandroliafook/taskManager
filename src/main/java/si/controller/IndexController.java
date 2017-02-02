package si.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alessandro Fook on 01/02/2017.
 */
@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getIndex() {

		ModelAndView model = new ModelAndView();
		model.setViewName("index");

		return model;
	}

}
