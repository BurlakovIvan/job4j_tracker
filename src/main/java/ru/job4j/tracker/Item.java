package ru.job4j.tracker;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import ru.job4j.toone.User;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Table(name = "items")
@EqualsAndHashCode(exclude = "created")
public class Item implements Comparable<Item> {

    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDateTime created = LocalDateTime.now();
    @ManyToMany
    @JoinTable(
            name = "participates",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> participates;

    public Item(String name) {
        this.name = name;
    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(int id, String name, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    public Item(String name, LocalDateTime created) {
        this.name = name;
        this.created = created;
    }

    @Override
    public int compareTo(Item another) {
        return this.name.compareTo(another.getName());
    }
}