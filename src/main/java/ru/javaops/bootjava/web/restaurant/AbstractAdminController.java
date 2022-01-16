package ru.javaops.bootjava.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.RestaurantRepository;

@Slf4j
public class AbstractAdminController {
    static final String REST_URL = "/api/admin/restaurants";

    @Autowired
    protected RestaurantRepository repository;

    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }
}
