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
	public SubTask getSubTasks(@PathVariable Long id) {
		return bdController.getSubTask(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/subTask/{taskId}", consumes = MediaType
			.APPLICATION_JSON_VALUE)
	public SubTask saveTask(@PathVariable Long taskId, @RequestBody SubTask subTask) {
		return bdController.saveSubTask(taskId, subTask);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/subTask")
	public SubTask saveTask(@RequestBody SubTask subTask) {
		return bdController.saveSubTask(subTask);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/subTask/{taskId}/{subTaskId}")
	public void removeTask(@PathVariable Long taskId, @PathVariable Long subTaskId) {
		bdController.removeSubTask(taskId, subTaskId);
	}
}
