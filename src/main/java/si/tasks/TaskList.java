package si.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alessandro Fook on 01/02/2017.
 */
public class TaskList {

	private List<Task> tasks;
	private String listName;

	public TaskList(String name){
		setName(name);
		setTasks(new ArrayList<>());
	}

	public void addTask(String taskName){
		tasks.add(new Task(taskName, getListName()));
	}

	public void removeTask(Task task){
		tasks.remove(task);
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getListName() {
		return listName;
	}

	public void setName(String name) {
		this.listName = name;
	}
}
