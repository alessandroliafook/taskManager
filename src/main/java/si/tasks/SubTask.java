package si.tasks;

import javax.persistence.*;

/**
 * Created by Alessandro Fook on 06/02/2017.
 */

@Entity
public class SubTask {

	@Column
	private String name;

	@Column
	private boolean isDone;

	@Column
	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public SubTask(){
		setDone(false);
	}

	public SubTask(String name, String description){
		setName(name);
		setDescription(description);
		setDone(false);
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
