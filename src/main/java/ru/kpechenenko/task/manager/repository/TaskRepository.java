package ru.kpechenenko.task.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpechenenko.task.manager.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
