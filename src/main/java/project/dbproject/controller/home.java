package project.dbproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


public class homeController {
    public String home() {
        return "index";
    }
}
