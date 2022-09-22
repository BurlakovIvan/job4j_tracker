package ru.job4j.lombok;

public class LombokUsage {
    public static void main(String[] args) {
        var permission = Permission.of()
                .id(1)
                .name("ADMIN")
                .rules("create")
                .rules("update")
                .rules("read")
                .rules("delete")
                .build();
        System.out.println(permission);
    }
}
