package si.tasksBd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.tasks.SubTask;

/**
 * Created by Alessandro Fook on 01/02/2017.
 */
@Repository
public interface SubTaskBd extends JpaRepository<SubTask, Long>{
}
