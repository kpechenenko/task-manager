package ru.kpechenenko.task.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpechenenko.task.manager.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
