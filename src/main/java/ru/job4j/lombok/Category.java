package ru.job4j.lombok;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {
    @NonNull
    @EqualsAndHashCode.Include
    @Getter
    private Integer id;
    @Getter
    @Setter
    private String name;
}
