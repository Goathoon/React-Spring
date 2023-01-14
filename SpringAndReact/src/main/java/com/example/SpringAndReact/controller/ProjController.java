package com.example.SpringAndReact.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjController {

    @GetMapping("/")
    public Goathoon doApi(){
        Goathoon goathoon = new Goathoon();
        goathoon.setName("Taehoon");
        goathoon.setAnimal("Goat");
        return goathoon;
    }

    static class Goathoon {
        private String name;
        private String animal;

        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }

        public void setAnimal(String animal){
            this.animal = animal;
        }
        public String getAnimal(){
            return animal;
        }

    }
}
