package ru.javaops.bootjava.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.to.RestaurantTo;
import ru.javaops.bootjava.util.RestaurantUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.javaops.bootjava.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.bootjava.util.validation.ValidationUtil.checkNew;

@Slf4j
@RestController
@RequestMapping(value=AbstractAdminController.REST_URL, produces= MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController extends AbstractAdminController {

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.findAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@Valid @RequestBody RestaurantTo restaurantTo) {
        log.info("create {}", restaurantTo);
        checkNew(restaurantTo);
        Restaurant created = repository.save(RestaurantUtil.createNewFromTo(restaurantTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody RestaurantTo restaurantTo, @PathVariable int id) {
        log.info("update restaurant with id {}", id);
        assureIdConsistent(restaurantTo, id);
        Restaurant restaurant = get(restaurantTo.getId()).getBody();
        Assert.notNull(restaurant, "Restaurant entity must not be null");
        repository.save(RestaurantUtil.updateFromTo(restaurant, restaurantTo));
    }
}
