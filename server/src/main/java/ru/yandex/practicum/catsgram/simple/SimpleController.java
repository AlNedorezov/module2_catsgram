package ru.yandex.practicum.catsgram.simple;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/home")
    public String homePage() {
        return "Котограм";
    }
}