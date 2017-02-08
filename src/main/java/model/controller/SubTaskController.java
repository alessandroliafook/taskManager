package model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import model.tasks.SubTask;

/**
 * Created by Alessandro Fook on 07/02/2017.
 */
public class SubTaskController {

	@Autowired
	private BdController bdController;

	public SubTaskController() {}

	@RequestMapping(method = RequestMethod.GET, value = "/subTask/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SubTask> getSubTasks(@PathVariable Long id) {

		SubTask subTask = bdController.getSubTask(id);

		return new ResponseEntity<SubTask>(subTask, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/subTask/{taskId}", consumes = MediaType
			.APPLICATION_JSON_VALUE)
	public ResponseEntity<SubTask> saveTask(@PathVariable Long taskId, @RequestBody SubTask subTask) {

		SubTask savedSubTask = bdController.saveSubTask(taskId, subTask);

		return (savedSubTask != null) ? new ResponseEntity<SubTask>(savedSubTask, HttpStatus.OK) :
										new ResponseEntity<SubTask>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/subTask")
	public ResponseEntity<SubTask> saveTask(@RequestBody SubTask subTask) {

		SubTask savedSubTask = bdController.saveSubTask(subTask);

		return new ResponseEntity<SubTask>(savedSubTask, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/subTask/{taskId}/{subTaskId}")
	public ResponseEntity<SubTask> removeTask(@PathVariable Long taskId, @PathVariable Long subTaskId) {
		boolean isRemoved = bdController.removeSubTask(taskId, subTaskId);

		return (isRemoved) ? new ResponseEntity<SubTask>(HttpStatus.OK) :
							 new ResponseEntity<SubTask>(HttpStatus.NOT_FOUND);
	}
}
