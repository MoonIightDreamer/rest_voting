package ru.javaops.bootjava.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.bootjava.model.Meal;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.MenuRepository;
import ru.javaops.bootjava.to.MealTo;
import ru.javaops.bootjava.util.MealsUtil;
import ru.javaops.bootjava.util.validation.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.javaops.bootjava.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.bootjava.util.validation.ValidationUtil.checkNew;

@Slf4j
@RestController
@RequestMapping(value = AbstractAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuController extends AbstractAdminController {

    @Autowired
    protected MenuRepository menuRepository;

    @GetMapping("/{restaurantId}/menu")
    public List<Meal> get(@PathVariable int restaurantId) {
        log.info("get menu of restaurant {}", restaurantId);
        return menuRepository.getMenu(restaurantId);
    }

    @GetMapping("/{restaurantId}/menu/{mealId}")
    public Meal get(@PathVariable int restaurantId, @PathVariable int mealId) {
        log.info("get meal {} of restaurant {}", mealId, restaurantId);
        return menuRepository.getMeal(restaurantId, mealId);
    }

    @DeleteMapping("/{restaurantId}/menu/{mealId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int mealId) {
        log.info("delete meal {} of restaurant {}", mealId, restaurantId);
        menuRepository.delete(restaurantId, mealId);
    }

    @PostMapping("/{restaurantId}/menu/")
    public ResponseEntity<Meal> create(@PathVariable int restaurantId,
                       @Valid @RequestBody MealTo mealTo) {
        log.info("create meal for restaurant {} menu", restaurantId);
        checkNew(mealTo);
        Restaurant restaurant = repository.findById(restaurantId).orElse(null);
        Assert.notNull(restaurant, "Restaurant entity must not be null");
        Meal created = menuRepository.save(MealsUtil.createNewFromTo(mealTo, restaurant));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}/menu/{mealId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody MealTo mealTo,
                       @PathVariable int restaurantId, @PathVariable int mealId) {
        log.info("update meal with id {}", mealId);
        assureIdConsistent(mealTo, mealId);
        Meal meal = menuRepository.findById(mealId).orElse(null);
        Assert.notNull(meal, "Meal entity must not be null");
        menuRepository.save(MealsUtil.updateFromTo(meal, mealTo));
    }
}
