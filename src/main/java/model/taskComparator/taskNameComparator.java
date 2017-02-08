package model.taskComparator;

import model.tasks.Task;

import java.util.Comparator;

/**
 * Created by Alessandro Fook on 08/02/2017.
 */
public class taskNameComparator implements Comparator<Task> {

	@Override
	public int compare(Task o1, Task o2) {
		return o1.getName().compareTo(o2.getName());
	}
}
