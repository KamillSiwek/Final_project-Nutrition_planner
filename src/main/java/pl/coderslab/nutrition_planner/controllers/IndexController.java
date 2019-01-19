package pl.coderslab.nutrition_planner.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.nutrition_planner.exceptions.NotFoundException;

@Controller
public class IndexController {

    @RequestMapping({"","/","/index"})
    public String getIndex(){
        return "index";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "/user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/errors/access-denied";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("errors/404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
