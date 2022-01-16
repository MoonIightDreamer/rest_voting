package ru.javaops.bootjava.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {

    @NotNull
    LocalDateTime dateTime;

    public RestaurantTo(Integer id, String name, LocalDateTime dateTime) {
        super(id, name);
        this.dateTime = dateTime;
    }
}
