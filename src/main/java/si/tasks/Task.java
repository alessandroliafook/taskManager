package si.tasks;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alessandro Fook on 01/02/2017.
 */
@Entity
public class Task extends SubTask{

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SubTask> subTasks;

	public Task(){
		setDone(false);
		setSubTasks(new ArrayList<>());
	}

	public Task(String name, String description){
		setSubTasks(new ArrayList<>());
		setDone(false);
		setName(name);
		setDescription(description);
	}

	public List<SubTask> getSubTasks() {
		return subTasks;
	}

	public void setSubTasks(List<SubTask> subTasks) {
		this.subTasks = subTasks;
	}

	public SubTask getSubTask(long id) {

		for (int i = 0; i < getSubTasks().size(); i++) {

			SubTask subTask = getSubTasks().get(i);
			if (subTask.getId() == id)
				return subTask;

		}
		return null;
	}

	public void addSubTask(SubTask subTask) {
		getSubTasks().add(subTask);
	}

	public void removeSubTask(SubTask subtask) {
		getSubTasks().remove(subtask);
	}

	public void removeSubTask(long id){

		SubTask subTask = getSubTask(id);

		if (subTask != null)
			getSubTasks().remove(subTask);
	}
}
