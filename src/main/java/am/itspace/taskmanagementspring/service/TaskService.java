package am.itspace.taskmanagementspring.service;

import am.itspace.taskmanagementspring.entity.Role;
import am.itspace.taskmanagementspring.entity.Task;
import am.itspace.taskmanagementspring.entity.User;
import am.itspace.taskmanagementspring.repository.TaskRepository;
import am.itspace.taskmanagementspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public void saveNewTask(Task task) {
        if (task.getUser() != null && task.getUser().getId() == 0) {
            task.setUser(null);
        }
        taskRepository.save(task);
    }

    public Page<Task> findTasksByUserRole(User user, Pageable pageable) {
        return user.getRole() == Role.USER ? taskRepository.findAllByUser_id(user.getId(), pageable) :
                taskRepository.findAll(pageable);
    }

    public void changeTaskUser(int userId, int taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        Optional<User> userOptional = userRepository.findById(userId);
        if (taskOptional.isPresent() && userOptional.isPresent()) {
            Task task = taskOptional.get();
            User user = userOptional.get();
            if (task.getUser() != user) {
                task.setUser(user);
                taskRepository.save(task);
            }
        } else if (taskOptional.isPresent() && userId == 0) {
            taskOptional.get().setUser(null);
            taskRepository.save(taskOptional.get());
        }
    }
}
