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
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "tag")
    List<Task> tasks = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }
}