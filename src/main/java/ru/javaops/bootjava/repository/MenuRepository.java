package ru.javaops.bootjava.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Meal;

import java.util.List;

@Transactional(readOnly = true)
@Tag(name = "Menu Controller")
public interface MenuRepository extends BaseRepository<Meal> {
    @Query("SELECT m FROM Meal m WHERE m.id=:id AND m.restaurant.id=:restaurant_id")
    Meal getMeal(@Param("restaurant_id") int restaurantId, @Param("id") int id);

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restaurant_id")
    List<Meal> getMenu(@Param("restaurant_id") int restaurantId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.restaurant.id=:restaurant_id AND m.id=:id")
    int delete(@Param("restaurant_id") int restaurantId, @Param("id") int id);
}
