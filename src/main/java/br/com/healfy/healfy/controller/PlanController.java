package br.com.healfy.healfy.controller;

import br.com.healfy.healfy.model.MealPlanModel;
import br.com.healfy.healfy.service.MealPlanService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mealPlans")

public class PlanController {

    @Autowired
    private MealPlanService planService;

    @PostMapping
    public ResponseEntity<Object> createPlan (@Valid @RequestBody MealPlanModel plan){
        try{
            MealPlanModel planModel = planService.createPlan(plan);
            return new ResponseEntity<>(planModel, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public List<MealPlanModel> readPlans(){
        return planService.readAllPlans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPlan(@PathVariable Long id ){
        try{
         MealPlanModel planModel =  planService.readPlanById(id);
           return new ResponseEntity<>(planModel, HttpStatus.OK);

        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePlan(@PathVariable Long id, @Valid @RequestBody MealPlanModel plan) {
        try {
           MealPlanModel planModel =  planService.updatePlan(id, plan);
            return new ResponseEntity<>(planModel, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Object> deletePlan(@PathVariable Long id){
        try{
             planService.deletePlanById(id);
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch(EntityNotFoundException e){
             return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
