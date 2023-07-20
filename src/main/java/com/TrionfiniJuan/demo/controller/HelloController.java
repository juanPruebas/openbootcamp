package com.TrionfiniJuan.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {


    /**
     *  localhost:8080/api/saludo
     * @return "Hola"
     */
    @Value("${app.message}")
    private String messageSaludo;


    @GetMapping("/saludo")
    public ResponseEntity<String> saludar(){
        return ResponseEntity.ok("Hola " + messageSaludo);
    }


}
