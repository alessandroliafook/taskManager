package si.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import si.taskBd.TaskBd;
import si.tasks.Task;

/**
 * Created by Alessandro Fook on 01/02/2017.
 */
@Controller
public class IndexController {

	@Autowired
	private TaskBd taskBd;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getIndex() {

		ModelAndView model = new ModelAndView();
		model.setViewName("index");

		return model;
	}

	@RequestMapping(value = "/task", method = RequestMethod.POST)
	public @ResponseBody Task saveTask(){
		Task task = new Task("Acordar", "Diaria");
		return taskBd.save(task); // salva o objeto e retorna um objeto q deve ser usado no sistema
	}

	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public @ResponseBody Task getTask(){
		return taskBd.findAll().get(0); // retorna uma lista de tasks
	}
}
