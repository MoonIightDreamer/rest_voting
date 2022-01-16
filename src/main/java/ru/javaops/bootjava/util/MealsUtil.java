package ru.javaops.bootjava.util;

import lombok.experimental.UtilityClass;
import ru.javaops.bootjava.model.Meal;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.to.MealTo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class MealsUtil {

    public Map<Integer, List<Meal>> createMenus(List<Meal> meals) {
        return meals.stream()
                .collect(Collectors.groupingBy(meal -> meal.getRestaurant().getId()));
    }

    public List<MealTo> getTos(Collection<Meal> meals) {
        return meals.stream()
                .map(MealsUtil::createTo)
                .toList();
    }

    public Meal createNewFromTo(MealTo mealTo, Restaurant restaurant) {
        return new Meal(null, mealTo.getName(), mealTo.getPrice(), restaurant, LocalDateTime.now());
    }

    public Meal updateFromTo(Meal meal, MealTo mealTo) {
        meal.setName(mealTo.getName());
        meal.setPrice(mealTo.getPrice());
        return meal;
    }

    public MealTo createTo(Meal meal) {
        return new MealTo(meal.getId(), meal.getName(), meal.getPrice());
    }
}
