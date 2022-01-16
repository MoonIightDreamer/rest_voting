package ru.javaops.bootjava.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="meals",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restaurant_id"}, name = "uk_restaurant_meal")})
public class Meal extends NamedEntity {

    @Column(name="price", nullable=false)
    @NotNull
    private long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurant_id", nullable=false)
    @JsonBackReference
    @NotNull
    private Restaurant restaurant;

    @Column(name = "datetime", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dateTime;

    public Meal(Integer id, String name, long price, Restaurant restaurant, LocalDateTime time) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
        this.dateTime = time;
    }

}
