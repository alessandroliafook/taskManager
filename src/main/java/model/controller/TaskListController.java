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
	public List<TaskList> getAllTaskLists() {
		return bdController.getAllTaskLists();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/taskList/{id}/{priority}")
	public TaskList getTaskList(@PathVariable Long id, @PathVariable String priority) {

		TaskList taskList = bdController.getTaskList(id);

		if (taskList != null) {
			selectOrder(taskList.getTasks(), priority);
			return taskList;
		}

		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "taskList", consumes = MediaType.APPLICATION_JSON_VALUE)
	public TaskList saveList(@RequestBody TaskList taskList) {
		return bdController.saveTaskList(taskList);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "taskList/{id}")
	public void removeTaskList(@PathVariable Long id) {
		bdController.removeTaskList(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "taskList")
	public void removeAllTasksLists() {
		bdController.removeAllTaskLists();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "taskList/{id}/{listName}")
	public TaskList editListName(@PathVariable Long id, @PathVariable String listName){
		return bdController.editListName(id, listName);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "taskList/tasks/{id}")
	public void clearTaskList(@PathVariable Long id) {
		bdController.clearTaskList(id);
	}

	// auxiliar methods

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
