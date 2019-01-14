package pl.coderslab.finishingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finishingproject.dto.DishDto;
import pl.coderslab.finishingproject.services.DishService;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/dish")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService){
        this.dishService = dishService;
    }


    @PostMapping
    public Result post(@RequestBody DishDto dishDto)
    {
        if(dishDto!=null){
            return Result.ok(dishService.save(dishDto));
        } else {
            return Result.error("Error");
        }
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Result get(@PathVariable Long id){
        if(dishService.find(id)!=null ){
            return  Result.ok(dishService.find(id));
        }else {
            return  Result.error("Error");
        }
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Result getAll() {
        return Result.ok(dishService.getAll());
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Result put(@RequestBody DishDto dishDto, @PathVariable Long id){
        if (dishDto != null) {
            return Result.ok(dishService.update(dishDto,id));
        }else {
            return Result.error("Error");
        }
    }
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Result delete(@PathVariable Long id){
        if(dishService.find(id)!=null ){
            return  Result.ok(dishService.remove(id));
        }else {
            return  Result.error("Error");
        }
    }

}
