package model.taskComparator;

import model.tasks.Task;

import java.util.Comparator;

/**
 * Created by Alessandro Fook on 08/02/2017.
 */
public class taskPriorityComparator  implements Comparator<Task> {
	@Override
	public int compare(Task o1, Task o2) {

		if (o1.getPriority().toLowerCase().equals("alta"))
			return 1;

		else if (o2.getPriority().toLowerCase().equals("alta"))
			return -1;

		else if (o1.getPriority().toLowerCase().equals("media"))
			return 1;

		else if (o2.getPriority().toLowerCase().equals("media"))
			return -1;

		else
			return 1;
	}
}
