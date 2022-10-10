package ru.kpechenenko.task.manager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tag")
@Table(name = "tags")
@NoArgsConstructor
@Getter
@Setter
public final class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "tag")
    List<Task> tasks = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }

    public void addTask(Task addedTask) {
        addedTask.setTag(this);
        this.tasks.add(addedTask);
    }

    public void removeTask(Task removedTask) {
        this.tasks.remove(removedTask);
        removedTask.setTag(null);
    }
}
