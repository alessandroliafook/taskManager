package model.controller;

import model.taskComparator.taskNameComparator;
import model.taskComparator.taskPriorityComparator;
import model.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import model.tasks.TaskList;

import java.util.Collections;
import java.util.List;

/**
 * Created by Alessandro Fook on 07/02/2017.
 */
public class TaskListController {

	@Autowired
	private BdController bdController;

	public TaskListController(){}

	@RequestMapping(method = RequestMethod.GET, value = "/taskList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskList>> getAllTaskLists() {

		List<TaskList> taskLists = bdController.getAllTaskLists();

		return new ResponseEntity<List<TaskList>>(taskLists, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/taskList/{id}/{priority}")
	public ResponseEntity<TaskList> getTaskList(@PathVariable Long id, @PathVariable String priority) {

		TaskList taskList = bdController.getTaskList(id);
		if (taskList != null) {
			selectOrder(taskList.getTasks(), priority);
			return new ResponseEntity<TaskList>(taskList, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "taskList", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskList> saveList(@RequestBody TaskList taskList) {

		TaskList savedList = bdController.saveTaskList(taskList);

		return new ResponseEntity<TaskList>(savedList, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "taskList/{id}")
	public ResponseEntity<TaskList> removeTaskList(@PathVariable Long id) {

		return getTaskListResponseEntity(bdController.removeTaskList(id));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "taskList")
	public ResponseEntity<TaskList> removeAllTasksLists() {

		bdController.removeAllTaskLists();

		return new ResponseEntity<TaskList>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "taskList/{id}/{listName}")
	public ResponseEntity<TaskList> editListName(@PathVariable Long id, @PathVariable String listName){

		TaskList taskList = bdController.editListName(id, listName);

		return (taskList != null) ? new ResponseEntity<TaskList>(taskList, HttpStatus.OK) :
									new ResponseEntity<TaskList>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "taskList/tasks/{id}")
	public ResponseEntity<TaskList> clearTaskList(@PathVariable Long id) {

		return getTaskListResponseEntity(bdController.clearTaskList(id));
	}

	// auxiliar methods

	private ResponseEntity<TaskList> getTaskListResponseEntity(boolean expression) {
		return (expression) ? new ResponseEntity<TaskList>(HttpStatus.OK) :
							  new ResponseEntity<TaskList>(HttpStatus.NOT_FOUND);
	}

	private List<Task> selectOrder(List<Task> taskList, String order) {

		switch (order.toLowerCase()) {
			case "name":
				Collections.sort(taskList, new taskNameComparator());
			case"priority":
				Collections.sort(taskList, new taskPriorityComparator());
		}
		return taskList;
	}
}
