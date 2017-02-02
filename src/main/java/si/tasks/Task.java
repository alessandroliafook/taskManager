package si.tasks;

import javax.persistence.*;

/**
 * Created by Alessandro Fook on 01/02/2017.
 */
@Entity
public class Task {

	@Column
	private String name;

	@Column
	private boolean isDone;

	@Column
	private String taskListName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public Task(String name, String taskListName){
		setName(name);
		setTaskListName(taskListName);
		setDone(false);
	}

	public Task(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean done) {
		isDone = done;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTaskListName() {
		return taskListName;
	}

	public void setTaskListName(String taskListName) {
		this.taskListName = taskListName;
	}
}
