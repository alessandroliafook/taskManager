package model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import model.tasks.Task;

import java.util.List;

/**
 * Created by Alessandro Fook on 07/02/2017.
 */
public class TaskController {

	@Autowired
	private BdController bdController;

	public TaskController() {}

	@RequestMapping(method = RequestMethod.GET, value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Task> getAllTasks() {
		return bdController.getAllTasks();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/task/{listId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Task> getAllTasks(@PathVariable Long listId) {
		return bdController.getAllTasks(listId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/task/{listId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Task saveTask(@PathVariable Long listId, @RequestBody Task task) {
		return bdController.saveTask(listId, task);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/task")
	public Task saveTask(@RequestBody Task task) {
		return bdController.saveTask(task);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/task/{listId}/{taskId}")
	public void removeTask(@PathVariable Long listId, @PathVariable Long taskId) {
		bdController.removeTask(listId, taskId);
	}
}
