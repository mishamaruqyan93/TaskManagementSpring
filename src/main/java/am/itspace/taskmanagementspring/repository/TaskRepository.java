package am.itspace.taskmanagementspring.repository;

import am.itspace.taskmanagementspring.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Page<Task> findAllByUser_id(int userId, Pageable pageable);
}
