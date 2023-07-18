package com.TrionfiniJuan.demo.controller;

import com.TrionfiniJuan.demo.entities.Laptop;
import com.TrionfiniJuan.demo.repository.LaptopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class LaptopController {
    private final LaptopRepository laptopRepository;

    private LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    // Ejercicios sesiones 7, 8 y 9
    @GetMapping("/laptops")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    @GetMapping("/laptops/{id}")
    public ResponseEntity<Laptop> findOneById( @PathVariable Long id ){

        Optional<Laptop> laptop_DB = laptopRepository.findById( id );

        return laptop_DB.map(laptop -> new ResponseEntity<>(laptop, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/laptops")
    public ResponseEntity<Laptop> create( @RequestBody Laptop laptop ){

        if( laptop.getId() != null){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        } else {
            return new ResponseEntity<>( laptopRepository.save(laptop), HttpStatus.OK );
        }

    }


    @PutMapping("/laptops")
    public ResponseEntity<Laptop> update( @RequestBody Laptop laptop ) {

        Optional<Laptop> laptop_DB = laptopRepository.findById( laptop.getId() );

        if( laptop_DB.isPresent() ){
            return new ResponseEntity<>( laptopRepository.save( laptop ), HttpStatus.CREATED );
        } else {
            return new ResponseEntity<>( null, HttpStatus.NOT_FOUND );
        }

    }
    @DeleteMapping("/laptops/{id}")
    public ResponseEntity<Laptop> delete( @PathVariable Long id ) {

        Optional<Laptop> laptop_DB = laptopRepository.findById( id );

        if( laptop_DB.isPresent() ){
            laptopRepository.deleteById( id );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        } else {
            return new ResponseEntity<>( null, HttpStatus.NOT_FOUND );
        }
    }

    @DeleteMapping("/laptops")
    public ResponseEntity<?> deleteAll() {
        laptopRepository.deleteAll();
        return new ResponseEntity<>( null, HttpStatus.NOT_FOUND );
    }




}
