package ru.job4j.pojo;

import java.time.LocalDateTime;

public class Student {

    private String fullName;
    private String group;
    private LocalDateTime enter;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public LocalDateTime getEnter() {
        return enter;
    }

    public void setEnter(LocalDateTime enter) {
        this.enter = enter;
    }
}
