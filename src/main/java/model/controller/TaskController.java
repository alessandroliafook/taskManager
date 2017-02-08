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
	public ResponseEntity<List<Task>> getAllTasks() {

		List<Task> tasks = bdController.getAllTasks();
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/task/{listId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Task>> getAllTasks(@PathVariable Long listId) {

		List<Task> tasks = bdController.getAllTasks(listId);
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/task/{listId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> saveTask(@PathVariable Long listId, @RequestBody Task task) {

		Task savedTask = bdController.saveTask(listId, task);

		return (savedTask != null) ? new ResponseEntity<Task>(savedTask, HttpStatus.OK) :
									 new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/task")
	public ResponseEntity<Task> saveTask(@RequestBody Task task) {

		Task savedTask = bdController.saveTask(task);

		return new ResponseEntity<Task>(savedTask, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/task/{listId}/{taskId}")
	public ResponseEntity<Task> removeTask(@PathVariable Long listId, @PathVariable Long taskId) {
		boolean isRemoved = bdController.removeTask(listId, taskId);

		return (isRemoved) ? new ResponseEntity<Task>(HttpStatus.OK) :
							 new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
	}
}
