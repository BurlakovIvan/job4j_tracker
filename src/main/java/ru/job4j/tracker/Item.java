package ru.job4j.tracker;

import lombok.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode(exclude = "dateTime")
public class Item implements Comparable<Item> {

    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
    private int id;
    private String name;
    private LocalDateTime dateTime = LocalDateTime.now();

    public Item(String name) {
        this.name = name;
    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(String name, LocalDateTime created) {
        this.name = name;
        this.dateTime = created;
    }

    @Override
    public int compareTo(Item another) {
        return this.name.compareTo(another.getName());
    }
}