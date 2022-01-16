package ru.javaops.bootjava.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.model.Meal;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.repository.MenuRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.repository.VotingRepository;
import ru.javaops.bootjava.util.MealsUtil;
import ru.javaops.bootjava.web.AuthUser;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Transactional(readOnly = true)
@RequestMapping(value = VotingController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VotingController {
    static final String REST_URL = "/api/user/voting";

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Autowired
    protected MenuRepository menuRepository;

    @Autowired
    protected VotingRepository votingRepository;

    @GetMapping
    public Map<Integer, List<Meal>> getAllMenus() {
        List<Meal> meals = menuRepository.findAll();
        return MealsUtil.createMenus(meals);
    }

    @GetMapping(value = "/{restaurantId}")
    public Vote vote() {
        Vote lastVote = votingRepository.getLastVote(1);
        return lastVote;
    }

}
