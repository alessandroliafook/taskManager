package model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import model.tasks.SubTask;
import model.tasks.Task;
import model.tasks.TaskList;
import model.tasksBd.SubTaskBd;
import model.tasksBd.TaskBd;
import model.tasksBd.TaskListBd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alessandro Fook on 06/02/2017.
 */

@Component
public class BdController {


	@Autowired
	private SubTaskBd subTaskBd;

	@Autowired
	private TaskBd taskBd;

	@Autowired
	private TaskListBd taskListBd;

	private static BdController bdController;

	private BdController() {}

	public static BdController getBdController(){

		if(bdController == null)
			return new BdController();

		else
			return bdController;
	}

	// methods of TaskList
	public List<TaskList> getAllTaskLists() {
		return taskListBd.findAll();
	}

	public TaskList getTaskList(long id) {
		return taskListBd.findOne(id);
	}

	public TaskList saveTaskList(TaskList taskList) {
		return taskListBd.save(taskList);
	}

	public boolean removeTaskList(long id) {

		if (taskListBd.findOne(id) != null) {

			taskListBd.delete(id);
			return true;
		}
		return false;
	}

	public void removeAllTaskLists(){
		taskListBd.deleteAll();
	}

	public TaskList editListName(long id, String name) {

		TaskList taskList = taskListBd.findOne(id);

		if (taskList != null) {
			taskList.setName(name);
			return taskListBd.save(taskList);
		}

		return null;
	}

	public boolean clearTaskList(long id) {

		TaskList taskList = taskListBd.findOne(id);

		if (taskList != null) {

			taskList.setTasks(new ArrayList<>());
			saveTaskList(taskList);

			return true;
		}
		return false;
	}

	//method of Tasks
	public List<Task> getAllTasks(){
		return taskBd.findAll();
	}

	public List<Task> getAllTasks(long taskListId){
		return taskListBd.getOne(taskListId).getTasks();
	}

	public Task getTask(long taskId) {
		return taskBd.findOne(taskId);
	}

	/**
	 *
	 * @param taskListId - The long that identifies the taskList
	 * @param task - The new task that will be added to the database
	 * @return The new task added to the database
	 */
	public Task saveTask(long taskListId, Task task){

		TaskList taskList = taskListBd.findOne(taskListId);

		if(taskList != null) {

			taskList.addTask(task);
			taskList = saveTaskList(taskList);

			return taskList.getTask(task);
		}
		return null;
	}

	public Task saveTask(Task task) {
		return taskBd.save(task);
	}

	public boolean removeTask(long taskListId, long taskId) {

		TaskList taskList = getTaskList(taskListId);

		if (taskList != null) {
			boolean isRemoved = taskList.removeTask(taskId);

			if (isRemoved) {
				saveTaskList(taskList);
				return true;
			}
		}
		return false;
	}

	// methods of SubTask

	public SubTask getSubTask(long id){
		return subTaskBd.findOne(id);
	}

	public SubTask saveSubTask(long taskId, SubTask subTask) {

		Task task = getTask(taskId);

		if (task != null) {
			task.addSubTask(subTask);
			return saveSubTask(subTask);
		}
		return null;
	}

	public SubTask saveSubTask(SubTask subTask) {
		return subTaskBd.save(subTask);
	}

	public boolean removeSubTask(long taskId, long subTaskId) {

		Task task = taskBd.findOne(taskId);

		if (task != null && task.getSubTask(subTaskId) != null) {
			task.removeSubTask(subTaskId);
			subTaskBd.delete(subTaskId);
			return true;
		}
		return false;
	}
}
