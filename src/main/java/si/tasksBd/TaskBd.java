package si.tasksBd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.tasks.Task;

/**
 * Created by Alessandro Fook on 01/02/2017.
 */
@Repository
public interface TaskBd extends JpaRepository<Task, Long> {}
