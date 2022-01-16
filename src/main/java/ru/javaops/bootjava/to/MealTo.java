package ru.javaops.bootjava.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.javaops.bootjava.HasId;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Value
@EqualsAndHashCode(callSuper = true)
public class MealTo extends NamedTo implements HasId {

    @NotNull
    long price;

    public MealTo(Integer id, String name, long price) {
        super(id, name);
        this.price = price;
    }
}
