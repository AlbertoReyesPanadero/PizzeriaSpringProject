package com.alberto.pizzerias.controller;

import com.alberto.pizzerias.persistence.entities.PizzaEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pizzeria")
public class PizzeriaController {

    @GetMapping("/buy/{id}")
    public ResponseEntity<String> buy(@RequestHeader("X-Username") String username, @RequestHeader("X-Authorities") String authorities, @PathVariable("id") String id){

        if (!authorities.contains("READ")) {
            return ResponseEntity.ok("No tienes permisos");
        }

        return ResponseEntity.ok("¡Has comprado una pizza!");
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestHeader("X-Username") String username, @RequestHeader("X-Authorities") String authorities, @RequestBody PizzaEntity pizza) {

        if (!authorities.contains("ROLE_ADMIN")) {
            return ResponseEntity.ok("No tienes permisos");
        }



        return ResponseEntity.ok("holi, toy asegurao B)");
    }



}
