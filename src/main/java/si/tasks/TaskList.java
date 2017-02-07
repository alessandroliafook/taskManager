package si.tasks;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alessandro Fook on 01/02/2017.
 */

@Entity
public class TaskList {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> tasks;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;

	@Column
	private String listName;

	public TaskList(){
		setName("");
		setTasks(new ArrayList<>());
	}

	public TaskList(String name){
		setName(name);
		setTasks(new ArrayList<>());
	}

	public void addTask(Task task){
		tasks.add(task);
	}

	public void removeTask(Task task){
		tasks.remove(task);
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public String getListName() {
		return listName;
	}


	public long getId() {
		return Id;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public void setName(String name) {
		this.listName = name;
	}

	public void setId(long id) {
		Id = id;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}


	public Task getTask(long taskId) {

		for (int i = 0; i < getTasks().size(); i++) {

			Task task = getTasks().get(i);

			if (task.getId() == taskId)
				return task;
		}

		return null;
	}

	public Task getTask(Task task) {

		int taskIndex = getTasks().indexOf(task);
		return getTasks().get(taskIndex);

	}

	public boolean removeTask(long taskId) {

		Task task = getTask(taskId);
		return getTasks().remove(task);

	}
}
